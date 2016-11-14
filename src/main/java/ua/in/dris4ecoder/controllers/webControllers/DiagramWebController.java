package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 17:47.
 */
@Controller
public class DiagramWebController {

    @RequestMapping(value = "/diagram")
    public ModelAndView diagram(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/diagram");

        return modelAndView;
    }
}
