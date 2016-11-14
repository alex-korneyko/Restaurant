package ua.in.dris4ecoder.controllers.webControllers.usersWebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:49.
 */
@Controller
public class ReserveTableWebController {

    @RequestMapping(value = "/user/reserveTable")
    public ModelAndView reserveTable(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/user/reserveTable");

        return modelAndView;
    }
}
