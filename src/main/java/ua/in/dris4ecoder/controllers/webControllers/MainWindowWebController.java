package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.UserRegistrationController;

import java.util.Map;

/**
 * Created by Alex Korneyko on 25.09.2016 20:32.
 */
@Controller
public class MainWindowWebController {

    private UserRegistrationController userRegistrationController;

    @Bean
    String setUserRegistrationController(UserRegistrationController userRegistrationController) {
        this.userRegistrationController = userRegistrationController;
        return null;
    }

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

    @RequestMapping(value = "/loginStatusFrame")
    public ModelAndView loginStatusPage() {

        ModelAndView modelAndView = new ModelAndView("loginStatusFrame");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof String)) {
            modelAndView.addObject("userLogin", ((UserDetails) principal).getUsername());
        }


        return modelAndView;
    }

    @RequestMapping(value = "/registrationPage")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registrationPage");
        return modelAndView;
    }

    @RequestMapping(value = "/registrationResult", method = RequestMethod.POST)
    public ModelAndView registrationSuccess(@RequestParam Map<String, String> model) {

        ModelAndView modelAndView = new ModelAndView("registrationPage");

        for (String key : model.keySet()) {
            if (model.get(key).isEmpty()) {
                modelAndView.addObject("regError", "Все поля должны быть заполнены");
                modelAndView.addObject("alreadyEntered", model);
                return modelAndView;
            }
        }

        if (!model.get("userPass1").equals(model.get("userPass2"))) {
            modelAndView.addObject("regError", "Пароли не совпадают");
            modelAndView.addObject("alreadyEntered", model);
            return modelAndView;
        }

        modelAndView.setViewName("registrationResult");
        userRegistrationController.addUser(model);

        return modelAndView;
    }

}
