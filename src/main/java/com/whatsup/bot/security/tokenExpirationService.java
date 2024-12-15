import com.whatsup.bot.security.securityConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class tokenExpirationService {
    
    @Autowired
    securityConfig config;
    
    public Date getTokenExpirationDate(String token) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = config.expiration_token_uri + "?input_token=" + token + "&access_token=" + config.secret;

        // Realizar la solicitud a la API de Meta
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> data = (Map<String, Object>) response.get("data");

        // Verificar si el token es válido
        boolean isValid = (boolean) data.get("is_valid");
        if (!isValid) {
            throw new Exception("El token proporcionado no es válido.");
        }

        // Extraer la fecha de expiración del token
        long expiresAt = ((Number) data.get("expires_at")).longValue();

        // Convertir el tiempo UNIX a una fecha de Java
        return new Date(expiresAt * 1000);
    }
}
