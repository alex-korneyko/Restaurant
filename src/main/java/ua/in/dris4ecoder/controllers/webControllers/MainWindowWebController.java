package ua.in.dris4ecoder.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Alex Korneyko on 25.09.2016 20:32.
 */
@Controller
public class MainWindowWebController {

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        
        return "index";
    }
}
