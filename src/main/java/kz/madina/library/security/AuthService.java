package kz.madina.library.security;

import kz.madina.library.model.User;
import kz.madina.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public void create(User newUser) {
        User user = new User.Builder()
                .email(newUser.getEmail())
                .fullName(newUser.getFullName())
                .password(newUser.getPassword())
                .role(newUser.getRole())
                .build();
        userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            if(!user.get().getPassword().equals(password)) {
                return null;
            }
        }else{
            return null;
        }
        return user.get();
    }
}
