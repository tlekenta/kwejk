package pl.edu.wat.pze.kwejk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.auth.UserPrincipal;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        System.out.println("loadUserByUsername");
        User user = userRepository.findByUsername(username);

        user = new User();
        user.setUsername("Pioter13099");
        user.setPassword("password");

        return new UserPrincipal(user);
    }
}
