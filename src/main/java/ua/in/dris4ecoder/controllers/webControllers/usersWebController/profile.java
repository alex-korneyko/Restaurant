package ua.in.dris4ecoder.controllers.webControllers.usersWebController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.UserRegistrationController;

import java.util.Map;

/**
 * Created by Alex Korneyko on 04.11.2016 15:43.
 */
@Controller
public class profile {

    @Autowired
    UserRegistrationController userRegistrationController;

    @RequestMapping(value = "/user/userProfile")
    public ModelAndView userProfiles(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("user/userProfile");

//        String info = params.keySet().stream().filter(s ->
//                s.length() > 5 && s.substring(0, 4).equals("info")).collect(Collectors.toList()).get(0);

        String userLogin = params.get("login");

        modelAndView.addObject("user", userRegistrationController.findUser(userLogin));

        return modelAndView;

    }
}
