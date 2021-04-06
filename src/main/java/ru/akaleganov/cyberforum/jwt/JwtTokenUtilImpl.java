package ru.akaleganov.cyberforum.jwt;

import java.util.Base64;

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
        return new String(Base64.getDecoder().decode(strEncoded.replace('-', '+')
                                                               .replace('_', '/')));
    }

}
