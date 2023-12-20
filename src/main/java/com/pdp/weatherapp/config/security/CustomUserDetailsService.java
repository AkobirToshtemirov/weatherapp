package com.pdp.weatherapp.config.security;

import com.pdp.springmvc.dao.RoleDAO;
import com.pdp.springmvc.dao.UserDAO;
import com.pdp.springmvc.entity.Role;
import com.pdp.springmvc.entity.User;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public CustomUserDetailsService(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User authUser = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (authUser.isBlocked()) {
            throw new LockedException("User is blocked");
        }

        fetchUserRolesAndPermissions(authUser);

        return new CustomeUserDetails(authUser);
    }

    private void fetchUserRolesAndPermissions(User authUser) {
        Long userId = authUser.getId();
        List<Role> roles = roleDAO.findAllByUserId(userId);
        authUser.setRoles(roles);
    }
}
