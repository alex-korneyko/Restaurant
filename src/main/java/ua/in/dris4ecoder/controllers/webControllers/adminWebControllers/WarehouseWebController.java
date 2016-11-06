package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;

import java.util.Map;

/**
 * Created by Alex Korneyko on 05.11.2016 11:07.
 */
@Controller
public class WarehouseWebController {

    @Autowired
    ManagementController managementController;

    @RequestMapping(value = "admin/warehouse")
    public ModelAndView warehouse(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/warehouse");

        if (params.containsKey("showAll")) {

            modelAndView.addObject("wholeWarehouse", managementController.findAllPositions());
        }


        modelAndView.addObject("wholeWarehouse", managementController.findAllPositions());
        return modelAndView;
    }
}
