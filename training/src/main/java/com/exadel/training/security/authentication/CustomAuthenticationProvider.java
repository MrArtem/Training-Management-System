package com.exadel.training.security.authentication;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import com.exadel.training.security.attributesMappers.UserAttributesMapper;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAttributesMapper userAttributesMapper;


    @Transactional
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Filter filter = new EqualsFilter("uid", authentication.getName());
        Authentication auth = null;
        if (ldapTemplate.authenticate("", filter.encode(), authentication.getCredentials().toString())) {
            User user = userDAO.getUserByLogin(authentication.getName());
            if (user == null) {
                user = ldapTemplate.search("", filter.encode(), userAttributesMapper).get(0);
                userDAO.save(user);
            }
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
            auth = new CustomAuthentication(authentication.getName(), authentication.getCredentials().toString(),
                    authorities, user.getFirstName(), user.getLastName(), user.getId());
        } else {
            User user = userDAO.getUserByLogin(authentication.getName());
            if (user != null && user.getRole() == User.Role.EX_COACH) {
                if (passwordEncoder.matches(authentication.getCredentials().toString(),
                        user.getUserPassword().getPassword())){
                    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                    authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
                    auth = new CustomAuthentication(authentication.getName(), authentication.getCredentials().toString(),
                            authorities, user.getFirstName(), user.getLastName(), user.getId());
                }
            }
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
