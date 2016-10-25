package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 25.09.2016 20:32.
 */
@Controller
public class MainWindowWebController {

    @RequestMapping(value = "/")
    public ModelAndView index(Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    @RequestMapping(value = "/mainPage")
    public ModelAndView mainPage() {

        ModelAndView modelAndView = new ModelAndView("mainPage");

        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        ModelAndView modelAndView = new ModelAndView("loginPage");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationPage")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registrationPage");
        return modelAndView;
    }


}
