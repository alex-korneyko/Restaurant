package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;

import java.util.Map;

/**
 * Created by Alex Korneyko on 31.10.2016 19:41.
 */
@Controller
public class EmployeesWebController {

    @Autowired
    private StaffController staffController;

//    @Bean
//    String setStaffController(StaffController staffController) {
//
//        this.staffController = staffController;
//        return null;
//    }

    @RequestMapping(value = "/admin/employees")
    public ModelAndView employees(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/employees");

        if (params.containsKey("showAll")) {
            modelAndView.addObject("employees", staffController.getAllEmployees());
        }

        return modelAndView;
    }
}
