package springtraining.luuquangbookmanagement.configs.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryConfig {

    @Value("${cloudName}")
    private String name;

    @Value("${cloudSecret}")
    private String secret;

    @Value("${cloudKey}")
    private String key;

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", name,
                "api_key", key,
                "api_secret", secret));
    }
}
