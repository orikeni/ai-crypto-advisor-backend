package org.example.ai_crypto_advisor.user;

import org.example.ai_crypto_advisor.config.TokenConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService ,UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenConfig tokenConfig;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public User addUser(User user) {
        if (this.userRepository.existsById(user.getId())) {
            throw new UsernameNotFoundException("User already exists in the system");
        }
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new UsernameNotFoundException("email already exists in the system");
        }
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User does not exist in the system");
        }
        return this.userRepository.findById(id).get();
    }
}
