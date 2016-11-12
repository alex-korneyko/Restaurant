package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.model.businessServices.GroupsRegistrationService;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.model.businessObjects.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 31.10.2016 19:41.
 */
@Controller
public class EmployeesWebController {

    @Autowired
    private StaffService staffController;

    @Autowired
    private GroupsRegistrationService groupsRegistrationService;

    @RequestMapping(value = "/admin/employees/**")
    public ModelAndView employees(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/employees");

        if (params.containsKey("create")) {

            modelAndView.addObject("allPosts", staffController.getAllEmployeePosts());
            modelAndView.addObject("allGroups", groupsRegistrationService.getAllGroups());
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("edit")) {

            System.out.println(params);

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("wrongCount", true);
            } else {
                int employeeId = Integer.parseInt(params.get(selected.get(0)));
                Employee employee = staffController.findEmployeeById(employeeId);
                modelAndView.addObject("employeeNameForEditing", employee.getFirstName());
                modelAndView.addObject("employeeIdForEditing", employee.getId());
                modelAndView.addObject("employeeSurNameForEditing", employee.getLastName());
                modelAndView.addObject("employeeLoginForEditing", employee.getUser().getUserLogin());
                modelAndView.addObject("employeeGroups", employee.getUser().getUserGroups());
                modelAndView.addObject("post", employee.getEmployeePost());
                modelAndView.addObject("employeePhoneForEditing", employee.getPhoneNumber());
                modelAndView.addObject("employeeBirthForEditing", employee.getDateOfBirth());
                modelAndView.addObject("employeeSalaryForEditing", employee.getSalary());
                modelAndView.addObject("passEditDisable", true);
                modelAndView.addObject("allGroups", groupsRegistrationService.getAllGroups());
                modelAndView.addObject("allPosts", staffController.getAllEmployeePosts());

                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("delete")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            selected.forEach(s -> staffController.removeEmployee(Integer.parseInt(params.get(s))));
        }

        if (params.containsKey("showAll")) {
            modelAndView.addObject("employees", staffController.getAllEmployees());
        }

        if (params.containsKey("saveEmployeeForm")) {

            String validateResult = staffController.validateEmployeeFormData(params);
            if (!validateResult.isEmpty()) {
                modelAndView.addObject("error", validateResult);
            } else {
                if (params.get("employeeId").equals("")) {
                    staffController.addEmployee(params);
                } else {
                    staffController.editEmployee(params);
                }
            }
        }

        modelAndView.addObject("employees", staffController.getAllEmployees());

        return modelAndView;
    }
}
