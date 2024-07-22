package coderscampus.assignment14.repository;

import coderscampus.assignment14.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    HashMap<String, User> users = new HashMap<>();
    public void addUser (User user){
        user.setId(UUID.randomUUID().toString());
        users.put(user.getId(),user);
    }
    public Optional<User> getUserById (String id){
        return Optional.ofNullable(users.get(id));
    }
    public User findByName (String name){
        return users.values().stream().filter(data->data.getName().equals(name)).findFirst().orElse(null);
    }

}
