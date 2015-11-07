package com.exadel.training.security.attributesMappers;

import com.exadel.training.dao.domain.User;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

@Component
public class UserAttributesMapper implements AttributesMapper<User> {

    @Override
    public User mapFromAttributes(Attributes attributes) throws NamingException {
        if (attributes == null) {
            return null;
        }
        String login = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String phone = null;

        if (attributes.get("uid") != null) {
            login = attributes.get("uid").get().toString();
        }
        if (attributes.get("mobile") != null) {
            phone = attributes.get("mobile").get().toString();
        }
        if (attributes.get("mail") != null) {
            email = attributes.get("mail").get().toString();
        }
        if (attributes.get("sn") != null) {
            lastName = attributes.get("sn").get().toString();
        }
        if (attributes.get("givenName") != null) {
            firstName = attributes.get("givenName").get().toString();
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(User.Role.USER);
        return user;
    }
}
