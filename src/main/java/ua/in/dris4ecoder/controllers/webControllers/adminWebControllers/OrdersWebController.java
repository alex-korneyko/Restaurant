package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.model.businessServices.*;
import ua.in.dris4ecoder.model.businessObjects.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 07.11.2016 21:58.
 */
@Controller
public class OrdersWebController {

    @Autowired
    private ServiceService serviceController;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private InstrumentsService instrumentsController;

    @Autowired
    private StaffService staffController;

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

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("errorMessage", "Нужно выбрать один заказ");
            } else {
                int orderId = Integer.parseInt(params.get(selected.get(0)));
                order = serviceController.findOrder(orderId);

                modelAndView.addObject("openNewOrderWindow", true);
            }
        }

        if (params.containsKey("delete")) {

            List<Integer> selectedIds = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .map(s -> Integer.parseInt(params.get(s))).collect(Collectors.toList());

            for (Integer id : selectedIds) {
                Order order = serviceController.findOrder(id);
                if (order.getStatus() == OrderDishStatus.IN_QUEUE) {
                    serviceController.removeOrder(id);
                } else {
                    modelAndView.addObject("errorMessage", "Заказ " + order.getStatus().toString() + ". Удаление невозможно");
                }
            }
        }

        if (params.containsKey("orderInfo")) {

            order = serviceController.findOrder(Integer.parseInt(params.get("orderInfo")));

            modelAndView.addObject("order", order);
            modelAndView.addObject("disableOk");
            modelAndView.addObject("openNewOrderWindow", true);
        }

        //----------------- DISHES LIST DIALOG ----------------------------------

        if (params.containsKey("addDish")) {

            fillOrder(order, params);
            modelAndView.addObject("allDishes", instrumentsController.findAllDishes());
            modelAndView.addObject("openNewOrderWindow", true);
            modelAndView.addObject("openDishesSelectWindow", true);
        }

        if (params.containsKey("editDish")) {

            List<Integer> dishIds = params.keySet().stream()
                    .filter(key -> key.length() > 12 && key.substring(0, 12).equals("selectedDish"))
                    .map(key -> Integer.parseInt(params.get(key))).collect(Collectors.toList());

            if (!dishIds.isEmpty()) {

                Dish dish = instrumentsController.findDish(dishIds.get(0));
                int dishCount = order.getDishCount(dish);
                order.removeDish(dish);

                modelAndView.addObject("dishCount", dishCount);
                modelAndView.addObject("paramsForDish", dish);
                modelAndView.addObject("openNewOrderWindow", true);
                modelAndView.addObject("openDishesCountWindow", true);
            }
        }


        //----------------- DISH PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickDish")) {

            Dish dish = instrumentsController.findDish(Integer.parseInt(params.get("clickDish")));

            modelAndView.addObject("paramsForDish", dish);
            modelAndView.addObject("dishCount", 1);
            modelAndView.addObject("openNewOrderWindow", true);
            modelAndView.addObject("openDishesCountWindow", true);
        }

        if (params.containsKey("paramsEntered")) {

            int count = Integer.parseInt(params.get("dishCount"));

            Dish selectedDishForOrder = instrumentsController.findDish(Integer.parseInt(params.get("selectedDishForOrder")));
            order.deleteDish(selectedDishForOrder);

            while (count > 0) {
                order.addDish(selectedDishForOrder);
                count--;
            }

            modelAndView.addObject("openNewOrderWindow", true);
        }

        //----------------- SAVE ORDER -------------------------------------

        if (params.containsKey("saveOrderForm")) {

            WarehouseChangeResult result;

            if (order.getId() == 0) {
                order.setStatus(OrderDishStatus.IN_QUEUE);
                result = serviceController.addOrder(order);
            } else {
                fillOrder(order, params);
                result = serviceController.editOrder(order);
            }

            if (!result.isChangeSuccessfully()) {
                modelAndView.addObject("errorMessage", "Невозможно выполнить заказ. На складе недостаточно ингредиентов");
            }
        }

        modelAndView.addObject("allOrders", serviceController.getAllOrders());
        modelAndView.addObject("allUsers", userRegistrationService.getAllUsers());
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    private void fillOrder(Order order, Map<String, String> params) {

        if (order != null) {
            order.setOrderOwner(userRegistrationService.findUser(params.get("selectedUserId")));
            order.setDesk(Integer.parseInt(params.get("desk")));
        }
    }
}
