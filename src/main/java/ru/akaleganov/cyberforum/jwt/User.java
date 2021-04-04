package ru.akaleganov.cyberforum.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Текущий пользователь.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;

    private String email;

}
