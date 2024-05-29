package coderscampus.assignment14.controller;

import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("welcome")
    public String welcomeForm(){
        return "welcome";
    }
    @PostMapping("welcome")
    public String welcomeSubmit(@RequestParam String name){
        User user = userService.findByName(name);
        if (user == null){
            user = new User();
            user.setName(name);
            userService.saveUser(user);
        }
        return"redirect:/channels";
    }
}
