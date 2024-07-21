package coderscampus.assignment14.service;

import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveUser (User user){
        return userRepository.saveUser(user);
    }
    public Optional<User> getUserById(String id){
        return userRepository.getUserById(id);
    }
    public User findByName(String name){
        return userRepository.findByName(name);
    }

}
