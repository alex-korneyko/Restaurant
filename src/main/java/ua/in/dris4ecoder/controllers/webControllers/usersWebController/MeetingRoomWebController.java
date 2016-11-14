package ua.in.dris4ecoder.controllers.webControllers.usersWebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Alex Korneyko on 14.11.2016 18:53.
 */
@Controller
public class MeetingRoomWebController {

    @RequestMapping(value = "/user/meetingRoom")
    public ModelAndView meetingRoom(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("/user/meetingRoom");

        return modelAndView;
    }
}
