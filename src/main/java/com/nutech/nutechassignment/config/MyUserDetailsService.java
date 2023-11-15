package com.nutech.nutechassignment.config;

import com.nutech.nutechassignment.model.MyUserDetails;
import com.nutech.nutechassignment.model.Role;
import com.nutech.nutechassignment.model.User;
import com.nutech.nutechassignment.model.UserRole;
import com.nutech.nutechassignment.repository.UserRepository;
import com.nutech.nutechassignment.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserById(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email : " + email);
        }

        try {
            List<UserRole> userRoles = userRoleRepository.findByUser_Id(email);
            List<Role> roles = new ArrayList<>();

            for (UserRole userRole : userRoles) {
                Role role = new Role();
                role.setId(userRole.getRoleId());
                roles.add(role);
            }

            return new MyUserDetails(user, roles);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
