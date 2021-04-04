package ru.akaleganov.cyberforum.jwt;

/**
 * Сервис для работы с jwt токеном.
 */
public interface JwtTokenUtil {

    /**
     * Получить json тела токена.
     *
     * @param token
     *         the токен.
     *
     * @return json из токена.
     */
    String getBodyFromToken(String token);

}
