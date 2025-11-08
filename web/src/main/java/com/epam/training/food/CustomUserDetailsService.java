package com.epam.training.food;

import com.epam.training.food.domain.Customer;
import com.epam.training.food.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<Customer> customerOptional = customerRepository.findByUserName(username);
        Customer customer = customerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println(customer);
        // Assuming every user has a default role of "USER". Modify as necessary for your application.
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        UserDetails user=new User(customer.getUserName(), customer.getPassword(), Collections.singletonList(authority));
        // User is a UserDetails implementation provided by Spring Security.
        System.out.println(user);
        return user;
    }
}
