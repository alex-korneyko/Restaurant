package ua.in.dris4ecoder.controllers.webControllers.restWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessServices.StaffService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 06.12.2016.
 */
@RestController
public class RestStaffWebController {

    private StaffService staffService;

    @Autowired
    public RestStaffWebController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = "/employee")
    public List<Employee> getAllEmployees() {

        List<Employee> allEmployees = staffService.getAllEmployees();
        allEmployees.forEach(employee -> employee.getUser().setUserGroups(new ArrayList<>()));

        return allEmployees;
    }

    @RequestMapping(value = "/employee/{employeeUserName}")
    public Employee getEmployee(@PathVariable("employeeUserName") String employeeUserName) {

        Employee employeeByUserName = staffService.findEmployeeByUserName(employeeUserName);
        return employeeByUserName;
    }
}
