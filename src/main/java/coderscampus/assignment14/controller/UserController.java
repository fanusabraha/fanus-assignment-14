package coderscampus.assignment14.controller;


import coderscampus.assignment14.domain.Messages;
import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    private List<Messages> messagesList = new ArrayList<> ();

    @GetMapping(value = {"/welcome","/"})
    public String welcome(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "welcome";
        }
        return "chat";
    }
    @PostMapping("/setName")
    public String setName(@RequestParam String name, @RequestParam String userId, HttpSession session) {
        Optional<User> existingUser = userService.getUserById(userId);
        if (existingUser.isEmpty()) {
            User user = new User();
            user.setId(userId);
            user.setName(name);
            userService.addUser(user);
        }
        session.setAttribute("userId", userId);
        return "redirect:/";
    }
    @GetMapping("/messages")
    @ResponseBody
    public List<Messages> getMessages() {
        return messagesList;
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(@RequestParam String content, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        User user = userService.getUserById(userId).orElseThrow();

        Messages message = new Messages();
        message.setUserId(user.getId());
        message.setUserName(user.getName());
        message.setContent(content);
        messagesList.add(message);
    }
}