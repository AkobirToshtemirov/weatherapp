package com.pdp.weatherapp.config.security;


import com.pdp.weatherapp.entity.Role;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.service.RoleService;
import com.pdp.weatherapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;

    public CustomUserDetailsService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User authUser = userService.findByUsername(username);

        fetchUserRolesAndPermissions(authUser);

        return new CustomeUserDetails(authUser);
    }

    private void fetchUserRolesAndPermissions(User authUser) {
        Long userId = authUser.getId();
        List<Role> roles = roleService.findRolesByUserId(userId);
        authUser.setRoles(roles);
    }
}
