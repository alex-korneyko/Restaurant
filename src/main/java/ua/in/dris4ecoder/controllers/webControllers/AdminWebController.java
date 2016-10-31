package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;

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

    @RequestMapping(value = "/admin/ingredients")
    public ModelAndView ingredients() {

        ModelAndView modelAndView = new ModelAndView("admin/ingredients");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/dishes")
    public ModelAndView dishes() {

        ModelAndView modelAndView = new ModelAndView("admin/dishes");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/menus")
    public ModelAndView menus() {

        ModelAndView modelAndView = new ModelAndView("admin/menus");

        return modelAndView;
    }
}
