package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex Korneyko on 23.10.2016 19:42.
 */
@Controller()
public class AdminWebController {

    @RequestMapping(value = "/admin/users")
    public ModelAndView users() {

        ModelAndView modelAndView = new ModelAndView("admin/users");

        return modelAndView;
    }
}
