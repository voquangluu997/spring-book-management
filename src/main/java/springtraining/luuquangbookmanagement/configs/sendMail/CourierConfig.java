package springtraining.luuquangbookmanagement.configs.sendMail;

import lombok.extern.slf4j.Slf4j;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

@Service
@Slf4j
public class CourierConfig {

    @Value("${COURIER_AUTH_TOKEN}")
    private String courierToken;

    @Value("${COURIER_NOTIFICATION_ID}")
    private String courierNotificationId;

    public void sendMail(String email, String action) {
        Courier.init(courierToken);
        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", email);
        SendRequestMessage message = new SendRequestMessage();
        message.setTo(to);
        message.setTemplate(courierNotificationId);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("action", action);
        message.setData(data);
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            log.error("could not send email", e);
            e.printStackTrace();
        }
    }
}
