package coderscampus.assignment14.service;

import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveUser (User user){
        return userRepository.saveUser(user);
    }
    public User findById(String id){
        return userRepository.findById(id);
    }
    public User findByName(String name){
        return userRepository.findByName(name);
    }

}
