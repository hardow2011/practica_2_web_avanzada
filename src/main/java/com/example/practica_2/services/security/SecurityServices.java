package com.example.practica_2.services.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.practica_2.entities.Role;
import com.example.practica_2.entities.User;
import com.example.practica_2.repository.RoleRepository;
import com.example.practica_2.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class SecurityServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Para encriptar la información.
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        /**
     * Creando el usuario por defecto y su rol.
     */
    public void createAdminUser(){
        System.out.println("Creación del usuario y rol en la base de datos");
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setRolesList(new HashSet<>(Arrays.asList(adminRole)));
        // admin.addToRolesList(adminRole);

        userRepository.save(admin);
    }

	/**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n\n\n"+username);

        User user = userRepository.findByUsername(username);


        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : user.getRolesList()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, grantedAuthorities);
    }
}
