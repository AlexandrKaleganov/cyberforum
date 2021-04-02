package ru.akaleganov.cyberforum.mapper;

import com.owlike.genson.Genson;
import org.junit.jupiter.api.Test;

public class Mapper {

    private String res = "{\n"
            + "  \"exp\": 1617293767,\n"
            + "  \"iat\": 1617290167,\n"
            + "  \"auth_time\": 1617281855,\n"
            + "  \"jti\": \"87f3f79c-e025-4e98-b283-ce5744fa8b7e\",\n"
            + "  \"iss\": \"https://sso-test.dp.nlmk.com/auth/realms/mes-adp\",\n"
            + "  \"aud\": \"account\",\n"
            + "  \"sub\": \"36ed02f8-b628-4683-9cc0-feaa9148e809\",\n"
            + "  \"typ\": \"Bearer\",\n"
            + "  \"azp\": \"nlmk-mes-incoming-certificate-ui\",\n"
            + "  \"nonce\": \"746979e5-05a2-42e8-9168-c26cf6ac11dd\",\n"
            + "  \"session_state\": \"94f58ee4-56cd-435f-acdf-6df0f207f2bd\",\n"
            + "  \"acr\": \"0\",\n"
            + "  \"allowed-origins\": [\n"
            + "    \"*\",\n"
            + "    \"https://nlmk-mes-internal-portal-mes-adp-test.app-test.nlmk.com\",\n"
            + "    \"https://nlmk-mes-internal-portal-adp-dev.app-test.nlmk.com\"\n"
            + "  ],\n"
            + "  \"realm_access\": {\n"
            + "    \"roles\": [\n"
            + "      \"offline_access\",\n"
            + "      \"uma_authorization\",\n"
            + "      \"agc-check-man\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"resource_access\": {\n"
            + "    \"account\": {\n"
            + "      \"roles\": [\n"
            + "        \"manage-account\",\n"
            + "        \"manage-account-links\",\n"
            + "        \"view-profile\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"scope\": \"openid email profile\",\n"
            + "  \"email_verified\": false,\n"
            + "  \"name\": \"Александр Калеганов\",\n"
            + "  \"preferred_username\": \"aonlmk\\\\kaleganov_av\",\n"
            + "  \"given_name\": \"Александр\",\n"
            + "  \"family_name\": \"Калеганов\",\n"
            + "  \"email\": \"kaleganov_av@nlmk.com\"\n"
            + "}";
    @Test
    public void testMap () {
        Genson genson = new Genson();
        User user = genson.deserialize(res, User.class);
        System.out.println(user);
    }
}
