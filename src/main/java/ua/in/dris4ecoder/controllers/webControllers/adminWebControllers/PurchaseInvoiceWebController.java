package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.model.businessObjects.WarehouseChangeResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alex Korneyko on 05.11.2016 11:32.
 */
@Controller
public class PurchaseInvoiceWebController {

    @Autowired
    ManagementController managementController;

    @Autowired
    InstrumentsController instrumentsController;

    private PurchaseInvoice purchaseInvoice;

    @RequestMapping(value = "admin/purchaseInvoices")
    public ModelAndView purchaseInvoice(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/purchaseInvoices");

        if (params.containsKey("create")) {

            purchaseInvoice = new PurchaseInvoice(true);
            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("allContractors", managementController.findAllContractors());
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("errorMessage", "Нужно выбрать одну накладную");
            } else {
                int invoiceId = Integer.parseInt(params.get(selected.get(0)));
                purchaseInvoice = managementController.findPurchaseInvoice(invoiceId);

                modelAndView.addObject("invoiceForEditing", purchaseInvoice);
                modelAndView.addObject("allContractors", managementController.findAllContractors());
                modelAndView.addObject("openEditWindow", true);
            }
        }

        if (params.containsKey("delete")) {

            List<Integer> selectedIds = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .map(s -> Integer.parseInt(params.get(s))).collect(Collectors.toList());

            List<WarehouseChangeResult> results = new ArrayList<>();

            for (Integer selectedId : selectedIds) {
                WarehouseChangeResult result = managementController.removePurchaseInvoice(selectedId);
                if (!result.isChangeSuccessfully()) {
                    results.add(result);
                }
            }

            if (!results.isEmpty()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("errorMessage", "Не удалось удалить некоторые накладные");
            }
        }

        //----------------- INGREDIENTS LIST -------------------------------------

        if (params.containsKey("addIngredient")) {

            fillInvoice(purchaseInvoice, params);

            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("openEditWindow", true);
            modelAndView.addObject("openIngredientsWindow", true);
        }

        if (params.containsKey("editIngredient")) {

            fillInvoice(purchaseInvoice, params);

            List<Integer> ingredientIds = params.keySet().stream()
                    .filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .map(key -> Integer.parseInt(params.get(key))).collect(Collectors.toList());

            if (!ingredientIds.isEmpty()) {

                modelAndView.addObject("paramsForIngredient",
                        purchaseInvoice.getIngredientWithParamsInInvoice(instrumentsController.findIngredient(ingredientIds.get(0))));
                modelAndView.addObject("openIngredientParamsWindow", true);
            }

            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("removeIngredient")) {

            fillInvoice(purchaseInvoice, params);

            params.keySet().stream().filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .forEach(key ->
                            purchaseInvoice.removeIngredient(instrumentsController.findIngredient(Integer.parseInt(params.get(key)))));

            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("clearAllIngredients")) {

            fillInvoice(purchaseInvoice, params);

            purchaseInvoice.clearAllIngredients();
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- INGREDIENT PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickIngredient")) {

            Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("clickIngredient")));

            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("paramsForIngredient", ingredient);
            modelAndView.addObject("openEditWindow", true);
            modelAndView.addObject("openIngredientParamsWindow", true);
        }

        if (params.containsKey("paramsEntered")) {

            double ingredientParamPrice = Double.parseDouble(params.get("ingredientParamPrice"));
            double ingredientParamWeight = Double.parseDouble(params.get("ingredientParamWeight"));

            Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("selectedIngredientForInvoice")));
            ingredient.setIngredientPrice(ingredientParamPrice);
            ingredient.setIngredientWeight(ingredientParamWeight);

            if (purchaseInvoice.getIngredients().contains(ingredient)) {
                purchaseInvoice.removeIngredient(ingredient);
            }

            purchaseInvoice.addIngredient(ingredient);

            modelAndView.addObject("invoiceForEditing", purchaseInvoice);
            modelAndView.addObject("allContractors", managementController.findAllContractors());
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- SAVE INVOICE -------------------------------------

        if (params.containsKey("saveInvoiceForm")) {

            if (purchaseInvoice.getId() == 0) {
                managementController.addPurchaseInvoice(purchaseInvoice, null);
            } else {
                WarehouseChangeResult result = managementController.editPurchaseInvoice(purchaseInvoice, null);
                if (!result.isChangeSuccessfully()) {
                    modelAndView.addObject("error", true);
                    modelAndView.addObject("errorMessage", "На складе не хватает ингредиентов");
                }
            }
        }


        modelAndView.addObject("allInvoices", managementController.findAllPurchaseInvoices());
        return modelAndView;
    }

    private void fillInvoice(PurchaseInvoice invoice, Map<String, String> params) {

        if (invoice == null) invoice = new PurchaseInvoice();

        Contractor contractor = managementController.findContractor(params.get("selectedContractor"));

        if (contractor != null) {
            invoice.setContractor(contractor);
        }

        invoice.setInvoiceDate(LocalDate.parse(params.get("invoiceDate")));
        invoice.setAutoPrice(params.containsKey("invoiceAutoPriceCheckBox"));
        invoice.setInvoiceIdFromContractor(params.get("contractorsInvoiceNum"));
    }
}
