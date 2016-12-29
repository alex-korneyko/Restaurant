package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.businessServices.ServiceService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 19.12.2016.
 */
@Controller
public class KitchenWebController {

    private ServiceService serviceController;

    @Autowired
    public KitchenWebController(ServiceService serviceController) {
        this.serviceController = serviceController;
    }

    @RequestMapping(value = "admin/kitchen")
    public ModelAndView kitchen(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/kitchen");

        if (params.containsKey("prepare")) orderChangeStatus(params, OrderDishStatus.IN_PROCESS);

        if (params.containsKey("prepared")) orderChangeStatus(params, OrderDishStatus.PREPARED);

        if (params.containsKey("backToQueue")) orderChangeStatus(params, OrderDishStatus.IN_QUEUE);

        modelAndView.addObject("allOrders", serviceController.getAllOrders().stream()
                .filter(order -> order.getStatus() != OrderDishStatus.CLOSED).collect(Collectors.toList()));

        return modelAndView;
    }

    private void orderChangeStatus(@RequestParam Map<String, String> params, OrderDishStatus status) {
        List<Integer> selectedIds = getSelectedOrders(params);

        for (Integer id : selectedIds) {
            Order order = serviceController.findOrder(id);
            order.setStatus(status);
            serviceController.editOrder(order);
        }
    }

    private List<Integer> getSelectedOrders(@RequestParam Map<String, String> params) {

        return params.keySet().stream()
                .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                .map(s -> Integer.parseInt(params.get(s))).collect(Collectors.toList());
    }
}
