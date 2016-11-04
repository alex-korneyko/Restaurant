package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.controlsfx.control.PropertySheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;

import java.util.Map;

/**
 * Created by Alex Korneyko on 04.11.2016 23:55.
 */
@Controller
public class ContractorsWebController {

    @Autowired
    private ManagementController managementController;

    @RequestMapping(value = "admin/contractors")
    public ModelAndView contractors(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/contractors");

        return modelAndView;
    }
}
