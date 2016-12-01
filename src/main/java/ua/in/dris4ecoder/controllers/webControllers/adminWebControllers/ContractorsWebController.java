package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.model.businessServices.ManagementService;
import ua.in.dris4ecoder.model.businessObjects.Contractor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 04.11.2016 23:55.
 */
@Controller
@ComponentScan(value = "ua.in.dris4ecoder.springConfigClasses")
public class ContractorsWebController {

    private final ManagementService managementController;

    @Autowired
    public ContractorsWebController(ManagementService managementController) {
        this.managementController = managementController;
    }

    @RequestMapping(value = "admin/contractors")
    public ModelAndView contractors(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/contractors");

        if (params.containsKey("create")) {

            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("wrongCount", true);
            } else {
                int contractorId = Integer.parseInt(params.get(selected.get(0)));

                modelAndView.addObject("contractors", managementController.findAllContractors());
                modelAndView.addObject("contractorIdForEditing", contractorId);
                modelAndView.addObject("contractorForEditing", managementController.findContractor(contractorId));

                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("newContractor")) {

            if (params.get("contractorId").equals("")) {
                managementController.addContractor(params.get("newContractor"));
            }
            else {
                Contractor contractor = managementController.findContractor(Integer.parseInt(params.get("contractorId")));
                contractor.setContractorName(params.get("newContractor"));
                managementController.editContractor(contractor.getId(), contractor);
            }
        }

        if (params.containsKey("delete")) {

            params.keySet().stream().filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .forEach(key -> managementController.removeContractor(Integer.parseInt(params.get(key))));
        }

        if (params.containsKey("showAll")) {

            modelAndView.addObject("contractors", managementController.findAllContractors());
        }

        modelAndView.addObject("contractors", managementController.findAllContractors());

        return modelAndView;
    }
}
