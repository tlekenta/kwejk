package pl.edu.wat.pze.kwejk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.auth.UserPrincipal;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return new UserPrincipal(user);
    }

    public Boolean isUsernameExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getOne(String userName) {
        return userRepository.findByUsername(userName);
    }
}
