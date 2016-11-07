package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

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

    private Dish dish;

    @RequestMapping(value = "admin/dishes")
    public ModelAndView dishes(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/dishes");
        modelAndView.addObject("dishCategories", DishCategory.stringValues());

        if (params.containsKey("create")) {

            dish = new Dish(true, true);
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("errorMessage", "Необходимо выбрать одно блюдо для редактирования");
            } else {
                int dishId = Integer.parseInt(params.get(selected.get(0)));
                dish = instrumentsController.findDish(dishId);
                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("find")) {

        }

        if (params.containsKey("delete")) {

            params.keySet().stream().filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .forEach(key -> instrumentsController.removeDish(Integer.parseInt(params.get(key))));
        }

        if (params.containsKey("showAll")) {

            modelAndView.addObject("dishes", instrumentsController.findAllDishes());
        }

        //----------------- INGREDIENTS LIST DIALOG ----------------------------------

        if (params.containsKey("addIngredient")) {

            fillDish(dish, params);
            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("openEditWindow", true);
            modelAndView.addObject("openIngredientsWindow", true);
        }

        if (params.containsKey("editIngredient")) {

            fillDish(dish, params);
            List<Integer> ingredientIds = params.keySet().stream()
                    .filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .map(key -> Integer.parseInt(params.get(key))).collect(Collectors.toList());

            if (!ingredientIds.isEmpty()) {

                modelAndView.addObject("paramsForIngredient",
                        dish.getIngredientWithParamsInDish(instrumentsController.findIngredient(ingredientIds.get(0))));
                modelAndView.addObject("openIngredientParamsWindow", true);
            }

            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("removeIngredient")) {

            fillDish(dish, params);
            params.keySet().stream().filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .forEach(key ->
                            dish.removeIngredient(instrumentsController.findIngredient(Integer.parseInt(params.get(key)))));

            modelAndView.addObject("dish", dish);
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("clearAllIngredients")) {

            fillDish(dish, params);
            dish.removeAllIngredients();
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- INGREDIENT PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickIngredient")) {

            Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("clickIngredient")));

            modelAndView.addObject("dish", dish);
            modelAndView.addObject("paramsForIngredient", ingredient);
            modelAndView.addObject("openEditWindow", true);
            modelAndView.addObject("openIngredientParamsWindow", true);
        }

        if (params.containsKey("paramsEntered")) {

            double ingredientParamPrice = Double.parseDouble(params.get("ingredientParamPrice"));
            double ingredientParamWeight = Double.parseDouble(params.get("ingredientParamWeight"));

            Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("selectedIngredientForDish")));
            ingredient.setIngredientPrice(ingredientParamPrice);
            ingredient.setIngredientWeight(ingredientParamWeight);

//            if (dish.getIngredients().contains(ingredient)) {
//                dish.removeIngredient(ingredient);
//            }

            dish.addIngredient(ingredient);

            modelAndView.addObject("dish", dish);
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- SAVE INVOICE -------------------------------------

        if (params.containsKey("saveDishForm")) {

            if (dish.getId() == 0) {
                instrumentsController.addDish(dish);
            } else {
                fillDish(dish, params);
                instrumentsController.editDish(dish.getId(), dish);
            }
        }

        modelAndView.addObject("dishes", instrumentsController.findAllDishes());
        modelAndView.addObject("dish", dish);

        return modelAndView;
    }

    private void fillDish(Dish dish, Map<String, String> params) {

        if (dish == null) dish = new Dish(true, true);

        if (!params.get("selectedCategory").equals("0")) {
            dish.setDishCategory(DishCategory.getValueByStringName(params.get("selectedCategory")));
        }

        dish.setDishName(params.get("dishName"));

        if (params.get("dishAutoPriceCheckBox") == null) {
            dish.setAutoPrice(false);
            dish.setPrice(Double.parseDouble(params.get("dishPrice")));
        }

        if (params.get("dishAutoWeightCheckBox") == null) {
            dish.setAutoWeight(false);
            dish.setWeight(Double.parseDouble(params.get("dishWeight")));
        }
    }
}
