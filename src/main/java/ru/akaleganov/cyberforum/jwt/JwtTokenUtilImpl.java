package ru.akaleganov.cyberforum.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.keycloak.common.util.Base64;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Сервис для получения тела токена.
 */
@Component
@Log4j2
public class JwtTokenUtilImpl implements JwtTokenUtil {

    /**
     * Получить json тела токена.
     *
     * @param token
     *         the токен.
     *
     * @return json из токена.
     */
    public String getBodyFromToken(String token) {
        String[] split = token.split("\\.");
        return getJson(split[1]);

    }

    private String getJson(String strEncoded) {
        byte[] decodedBytes = new byte[0];
        try {
            decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

}
