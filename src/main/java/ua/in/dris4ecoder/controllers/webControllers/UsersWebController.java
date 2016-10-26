package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex Korneyko on 25.10.2016 16:32.
 */
@Controller
public class UsersWebController {

    @RequestMapping(value = "/user/loginSuccess")
    public ModelAndView loginSuccess() {

        ModelAndView modelAndView = new ModelAndView("user/loginSuccess");
        return modelAndView;
    }

    @RequestMapping(value = "/user/logout")
    public ModelAndView logout() {

        ModelAndView modelAndView = new ModelAndView("user/logoutPage");
        return modelAndView;
    }
}
