package springtraining.luuquangbookmanagement.configs.sendMail;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

@Configuration
public class CourierConfig {

    @Value("${COURIER_AUTH_TOKEN}")
    private String courierToken;

    @Value("${COURIER_NOTIFICATION_ID}")
    private String courierNotificationId;

    public void sendMail(String email, String action) {
        Courier.init(courierToken);
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", email);
        message.setTo(to);
        message.setTemplate(courierNotificationId);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("action", action);
        message.setData(data);
        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
