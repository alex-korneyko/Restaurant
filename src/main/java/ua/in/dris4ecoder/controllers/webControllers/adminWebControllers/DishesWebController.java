package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 04.11.2016 22:42.
 */
@Controller
public class DishesWebController {

    @Autowired
    InstrumentsController instrumentsController;

    @RequestMapping(value = "admin/dishes")
    public ModelAndView dishes(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/dishes");

        if (params.containsKey("create")) {

        }

        if (params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("wrongCount", true);
            } else {
                int dishId = Integer.parseInt(params.get(selected.get(0)));

                modelAndView.addObject("allDishes", instrumentsController.findAllDishes());
                modelAndView.addObject("dishIdForEditing", dishId);
                modelAndView.addObject("dishForEditing", instrumentsController.findDish(dishId));

                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("newDish")) {

        }

        if (params.containsKey("delete")) {

            params.keySet().stream().filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .forEach(key -> instrumentsController.removeDish(Integer.parseInt(params.get(key))));
        }

        if (params.containsKey("showAll")) {

            modelAndView.addObject("dishes", instrumentsController.findAllDishes());
        }

        modelAndView.addObject("dishes", instrumentsController.findAllDishes());

        return modelAndView;
    }
}
