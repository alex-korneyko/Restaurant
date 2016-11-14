package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:56.
 */
@Controller
public class CustomOrderWebController {

    @RequestMapping(value = "/user/customOrders")
    public ModelAndView customOrder(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/user/customOrders");

        return modelAndView;
    }
}
