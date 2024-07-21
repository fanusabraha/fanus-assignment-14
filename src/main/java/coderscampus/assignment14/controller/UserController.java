package coderscampus.assignment14.controller;


import coderscampus.assignment14.channelRepository.ChannelRepository;
import coderscampus.assignment14.domain.Channel;
import coderscampus.assignment14.domain.Messages;
import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelRepository channelRepository;
    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "welcome";
        }

        model.addAttribute("channels", channelRepository.getAllChannels());
        return "channelList";
    }
    @PostMapping("/setName")
    public String setName(@RequestParam String name, HttpSession session){
            String userId = UUID.randomUUID().toString();
            User user = new User();
            user.setId(userId);
            user.setName(name);
            userService.addUser(user);
            session.setAttribute("userId", userId);
            return "redirect:/";
        }
    @PostMapping("/addChannel")
    public String addChannel(@RequestParam String channelName) {
        String channelId = UUID.randomUUID().toString();
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setUsername(channelName);
        channelRepository.addChannel(channel);
        return "redirect:/";
    }
    @GetMapping("/channels/{channelId}")
    public String channel(@PathVariable String channelId, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }

        model.addAttribute("channelId", channelId);
        return "channel";
    }

    @PostMapping("/channels/{channelId}/sendMessage")
    public String sendMessage(@PathVariable String channelId, @RequestParam String content, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        User user = userService.getUserById(userId).orElseThrow();

        Messages message = new Messages();
        message.setUserId(user.getId());
        message.setUserName(user.getName());
        message.setContent(content);

        Channel channel = channelRepository.getChannelById(channelId).orElseThrow();
        channel.getMessages().add(message);

        return "redirect:/channels/" + channelId;
    }

    @GetMapping("/channels/{channelId}/messages")
    @ResponseBody
    public Iterable<Messages> getMessages(@PathVariable String channelId) {
        Channel channel = channelRepository.getChannelById(channelId).orElseThrow();
        return channel.getMessages();
    }
}