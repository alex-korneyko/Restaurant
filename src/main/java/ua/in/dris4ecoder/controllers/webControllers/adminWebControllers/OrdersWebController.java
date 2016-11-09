package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.*;
import ua.in.dris4ecoder.model.businessObjects.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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

    @Autowired
    private StaffController staffController;

    private Order order;

    @RequestMapping(value = "admin/orders")
    public ModelAndView orders(@RequestParam Map<String, String> params, Principal user) {

        ModelAndView modelAndView = new ModelAndView("admin/orders");

        if (params.containsKey("create")) {

            String userName = user.getName();
            Employee employee = staffController.findEmployeeByUserName(userName);

            order = new Order(employee);

            modelAndView.addObject("openNewOrderWindow", true);
        }

        if (params.containsKey("edit")) {

            modelAndView.addObject("order", order);
        }

        //----------------- DISHES LIST DIALOG ----------------------------------

        if (params.containsKey("addDish")) {

            fillOrder(order, params);
            modelAndView.addObject("allDishes", instrumentsController.findAllDishes());
            modelAndView.addObject("openNewOrderWindow", true);
            modelAndView.addObject("openDishesSelectWindow", true);
        }


        //----------------- DISH PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickDish")) {

            Dish dish = instrumentsController.findDish(Integer.parseInt(params.get("clickDish")));

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

        //----------------- SAVE ORDER -------------------------------------

        if (params.containsKey("saveOrderForm")) {

            if (order.getId() == 0) {
                order.setStatus(OrderDishStatus.IN_QUEUE);
                WarehouseChangeResult result = serviceController.addOrder(order);
                if (!result.isChangeSuccessfully()) {
                    modelAndView.addObject("errorMessage", "Невозможно выполнить заказ. На складе недостаточно ингредиентов");
                }
            } else {
                fillOrder(order, params);
                serviceController.editOrder(order);
            }
        }

        modelAndView.addObject("allOrders", serviceController.getAllOrders());
        modelAndView.addObject("allUsers", userRegistrationController.getAllUsers());
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    private void fillOrder(Order order, Map<String, String> params) {

        if (order != null) {
            order.setOrderOwner(userRegistrationController.findUser(params.get("selectedUserId")));
            order.setDesk(Integer.parseInt(params.get("desk")));
        }
    }
}
