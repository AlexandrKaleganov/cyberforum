package ru.akaleganov.cyberforum.jwt;


/**
 * Билдер для пользователя.
 */
public interface UserBuilderFromContext {

    /**
     * Получение пользователя из контекста.
     *
     * @return {@link User}
     */
    User getUserFomContext();

}
