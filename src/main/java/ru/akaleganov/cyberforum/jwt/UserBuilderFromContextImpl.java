package ru.akaleganov.cyberforum.jwt;

import com.owlike.genson.Genson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@inheritDoc}.
 */
@Component
@RequiredArgsConstructor
public class UserBuilderFromContextImpl implements UserBuilderFromContext {

    private final Genson genson;

    private final JwtTokenUtilImpl jwtTokenUtil;

    @Override
    public User getUserFomContext() {
//        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) SecurityContextHolder
//                .getContext().getAuthentication().getDetails();
        return getUserFormJson("simpleKeycloakAccount.getKeycloakSecurityContext().getTokenString()");

    }

    private User getUserFormJson(String jsonUser) {
        return genson.deserialize(jwtTokenUtil.getBodyFromToken(jsonUser), User.class);
    }

}
