package ua.in.dris4ecoder.controllers.webControllers;

import org.controlsfx.control.PropertySheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:20.
 */
@Controller
public class DrivingDirectionsWebController {

    @RequestMapping(value = "/drivingDirections")
    public ModelAndView drivingDirections(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/drivingDirections");

        return modelAndView;
    }
}
