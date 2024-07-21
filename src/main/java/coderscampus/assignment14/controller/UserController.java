package coderscampus.assignment14.controller;


import coderscampus.assignment14.channelRepository.ChannelRepository;
import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
            userService.saveUser(user);
            session.setAttribute("userId", userId);
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
        User user = userService.findById(userId).orElseThrow();

        Message message = new Message();
        message.setUserId(user.getId());
        message.setUserName(user.getName());
        message.setContent(content);

        Channel channel = channelRepository.getChannelById(channelId).orElseThrow();
        channel.getMessages().add(message);

        return "redirect:/channels/" + channelId;
    }

    @GetMapping("/channels/{channelId}/messages")
    @ResponseBody
    public Iterable<Message> getMessages(@PathVariable String channelId) {
        Channel channel = channelRepository.getChannelById(channelId).orElseThrow();
        return channel.getMessages();
    }
}