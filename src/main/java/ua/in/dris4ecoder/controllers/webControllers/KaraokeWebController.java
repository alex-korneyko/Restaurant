package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:11.
 */
@Controller
public class KaraokeWebController {

    @RequestMapping(value = "/karaoke")
    public ModelAndView karaoke(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/karaoke");

        return modelAndView;
    }
}
