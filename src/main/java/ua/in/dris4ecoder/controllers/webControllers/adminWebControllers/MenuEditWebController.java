package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 21:36.
 */
@Controller
public class MenuEditWebController {

    @RequestMapping(value = "/admin/menu")
    public ModelAndView menu(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/admin/menu");

        return modelAndView;
    }
}
