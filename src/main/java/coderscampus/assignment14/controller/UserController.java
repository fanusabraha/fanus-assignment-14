package coderscampus.assignment14.controller;
import coderscampus.assignment14.domain.Messages;
import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;
    private List<Messages> messagesList = new ArrayList<> ();

    @GetMapping(value = {"/welcome","/"})
    public String welcome(HttpSession session, Model model) {

        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName);

        if (userId == null || userService.getUserById(userId).isEmpty()) {
            return "welcome";
        }
            return "chat";
    }

    @PostMapping("/setName")
    public String setName(@RequestParam String name, @RequestParam String userId, HttpSession session) {
        if (userService.getUserById(userId).isEmpty()) {
            User user = new User();
            user.setId(userId);
            user.setName(name);
            userService.addUser(user);
            session.setAttribute("userName", name);
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

//    private static long extractTimestamp(String userId) {
//        // Regular expression to match the timestamp part of the userId
//        Pattern pattern = Pattern.compile("user-(\\d+)-");
//        Matcher matcher = pattern.matcher(userId);
//
//        if (matcher.find()) {
//            // Group 1 contains the timestamp
//            return Long.parseLong(matcher.group(1));
//        } else {
//            throw new IllegalArgumentException("Timestamp not found in userId");
//        }
//    }
}