package ua.in.dris4ecoder.controllers.webControllers.usersWebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:54.
 */
@Controller
public class RestRoomWebController {

    @RequestMapping(value = "/user/restRoom")
    public ModelAndView restRoom(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/user/restRoom");

        return modelAndView;
    }
}
