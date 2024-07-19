package coderscampus.assignment14.controller;


import coderscampus.assignment14.channelRepository.ChannelRepository;
import coderscampus.assignment14.domain.User;
import coderscampus.assignment14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/welcome")
    public String welcomeSubmit(@RequestParam String name){
        User user = userService.findByName(name);
        if (user == null){
            user = new User();
            user.setName(name);
            userService.saveUser(user);
        }
        return"redirect:/channels";
    }
    @GetMapping("/channel")
    public String channelPage(ModelMap model) {
        model.put("channelId", "general");
        return "channel";
    }
}
