package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;
import ua.in.dris4ecoder.controllers.businessControllers.UserRegistrationController;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Alex Korneyko on 07.11.2016 21:58.
 */
@Controller
public class OrdersWebController {

    @Autowired
    private ServiceController serviceController;

    @Autowired
    private UserRegistrationController userRegistrationController;

    @Autowired
    private InstrumentsController instrumentsController;

    private Order order;

    @RequestMapping(value = "admin/orders")
    public ModelAndView orders(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/orders");

        if (params.containsKey("create")) {

            order = new Order();
            modelAndView.addObject("openNewOrderWindow", true);
        }

        if (params.containsKey("edit")) {

            modelAndView.addObject("order", order);
        }

        //----------------- DISHES LIST DIALOG ----------------------------------

        if (params.containsKey("addDish")) {

            modelAndView.addObject("allDishes", instrumentsController.findAllDishes());
            modelAndView.addObject("openNewOrderWindow", true);
            modelAndView.addObject("openDishesSelectWindow", true);

            fillOrder(order, params);
        }


        //----------------- DISH PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickDish")) {

            Dish dish = instrumentsController.findDish(Integer.parseInt(params.get("clickDish")));

            modelAndView.addObject("order", order);
            modelAndView.addObject("paramsForDish", dish);
            modelAndView.addObject("openNewOrderWindow", true);
            modelAndView.addObject("openDishesCountWindow", true);
        }

        if (params.containsKey("paramsEntered")) {

            int count = Integer.parseInt(params.get("dishCount"));

            while (count > 0) {
                Dish dish = instrumentsController.findDish(Integer.parseInt(params.get("selectedDishForOrder")));
                order.addDish(dish);
                count--;
            }

            modelAndView.addObject("openNewOrderWindow", true);
        }

        modelAndView.addObject("allOrders", serviceController.getAllOrders());
        modelAndView.addObject("allUsers", userRegistrationController.getAllUsers());
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    private void fillOrder(Order order, Map<String, String> params) {

    }
}
