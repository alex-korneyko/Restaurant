package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.model.businessObjects.Currency;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 04.11.2016 19:10.
 */
@Controller
public class IngredientsWebController {

    @Autowired
    InstrumentsController instrumentsController;

    @RequestMapping(value = "admin/ingredients")
    public ModelAndView ingredients(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/ingredients");

        if (params.containsKey("create")) {

            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("allUnits", instrumentsController.findAllUnits());
            modelAndView.addObject("openEditWindow", true);
        }

        if(params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("wrongCount", true);
            } else {
                int ingredientId = Integer.parseInt(params.get(selected.get(0)));

                modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
                modelAndView.addObject("allUnits", instrumentsController.findAllUnits());
                modelAndView.addObject("ingredientIdForEditing", ingredientId);
                modelAndView.addObject("ingredientForEditing", instrumentsController.findIngredient(ingredientId));

                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("newIngredient")) {

            if (params.get("ingredientId").equals("")) {
                Ingredient ingredient = new Ingredient(params.get("newIngredient"));
                ingredient.setUnit(instrumentsController.findUnit(params.get("selectedUnit")));
                ingredient.setCurrency(instrumentsController.getMainCurrency());
                instrumentsController.addIngredient(ingredient);
            } else {
                Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("ingredientId")));
                ingredient.setIngredientName(params.get("newIngredient"));
                ingredient.setUnit(instrumentsController.findUnit(params.get("selectedUnit")));
                instrumentsController.editIngredient(Integer.parseInt(params.get("ingredientId")), ingredient);
            }
        }

        if (params.containsKey("delete")) {

            params.keySet().stream().filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .forEach(key -> instrumentsController.removeIngredient(Integer.parseInt(params.get(key))));
        }

        if (params.containsKey("showAll")) {

            modelAndView.addObject("ingredients", instrumentsController.findAllIngredients());
        }

        modelAndView.addObject("ingredients", instrumentsController.findAllIngredients());

        return modelAndView;
    }
}
