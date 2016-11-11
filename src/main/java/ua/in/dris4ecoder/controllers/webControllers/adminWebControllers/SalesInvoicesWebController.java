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
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.model.businessObjects.WarehouseChangeResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 05.11.2016 11:36.
 */
@Controller
public class SalesInvoicesWebController {

    @Autowired
    ManagementController managementController;

    @Autowired
    InstrumentsController instrumentsController;

    private SalesInvoice salesInvoice;

    @RequestMapping(value = "admin/salesInvoices")
    public ModelAndView salesInvoices(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/salesInvoices");

        if (params.containsKey("create")) {

            salesInvoice = new SalesInvoice(true);
            modelAndView.addObject("invoiceForEditing", salesInvoice);
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
                salesInvoice = managementController.findSalesInvoice(invoiceId);

                modelAndView.addObject("invoiceForEditing", salesInvoice);
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
                WarehouseChangeResult result = managementController.removeSalesInvoice(selectedId, true);
                if (!result.isChangeSuccessfully()) {
                    results.add(result);
                }
            }

            if (!results.isEmpty()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("errorMessage", "Не удалось удалить некоторые накладные");
            }
        }

        if (params.containsKey("invoice")) {

            SalesInvoice invoice = managementController.findSalesInvoice(Integer.parseInt(params.get("invoice")));
            modelAndView.addObject("invoiceForEditing", invoice);
            modelAndView.addObject("disableOk", true);
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- INGREDIENTS LIST -------------------------------------

        if (params.containsKey("addIngredient")) {

            fillInvoice(salesInvoice, params);

            modelAndView.addObject("invoiceForEditing", salesInvoice);
            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("openEditWindow", true);
            modelAndView.addObject("openIngredientsWindow", true);
        }

        if (params.containsKey("editIngredient")) {

            List<Integer> ingredientIds = params.keySet().stream()
                    .filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .map(key -> Integer.parseInt(params.get(key))).collect(Collectors.toList());

            if (!ingredientIds.isEmpty()) {

                modelAndView.addObject("paramsForIngredient",
                        salesInvoice.getIngredientWithParamsInInvoice(instrumentsController.findIngredient(ingredientIds.get(0))));
            }

            modelAndView.addObject("invoiceForEditing", salesInvoice);
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("removeIngredient")) {

            params.keySet().stream().filter(key -> key.length() > 18 && key.substring(0, 18).equals("selectedIngredient"))
                    .forEach(key ->
                            salesInvoice.removeIngredient(instrumentsController.findIngredient(Integer.parseInt(params.get(key)))));

            modelAndView.addObject("invoiceForEditing", salesInvoice);
            modelAndView.addObject("allIngredients", instrumentsController.findAllIngredients());
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("clearAllIngredients")) {


        }

        //----------------- INGREDIENT PARAMS DIALOGUE WINDOWS -------------------------------------

        if (params.containsKey("clickIngredient")) {

            Ingredient ingredient = instrumentsController.findIngredient(Integer.parseInt(params.get("clickIngredient")));

            modelAndView.addObject("invoiceForEditing", salesInvoice);
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

            if (salesInvoice.getIngredients().contains(ingredient)) {
                salesInvoice.removeIngredient(ingredient);
            }

            salesInvoice.addIngredient(ingredient);

            modelAndView.addObject("invoiceForEditing", salesInvoice);
            modelAndView.addObject("allContractors", managementController.findAllContractors());
            modelAndView.addObject("openEditWindow", true);
        }

        //----------------- SAVE INVOICE -------------------------------------

        if (params.containsKey("saveInvoiceForm")) {

            WarehouseChangeResult result;

            if (salesInvoice.getId() == 0) {
                result = managementController.addSalesInvoice(salesInvoice, true);
            } else {
                result = managementController.editSalesInvoice(salesInvoice, true);
            }

            if (!result.isChangeSuccessfully()) {
                modelAndView.addObject("error", true);
                modelAndView.addObject("errorMessage", "На складе не хватает ингредиентов");
            }
        }

        modelAndView.addObject("allInvoices", managementController.findAllSalesInvoices());
        return modelAndView;
    }

    private void fillInvoice(SalesInvoice invoice, Map<String, String> params) {

        if (invoice == null) invoice = new SalesInvoice();

        Contractor contractor = managementController.findContractor(params.get("selectedContractor"));

        if (contractor != null) {
            invoice.setContractor(contractor);
        }

        invoice.setInvoiceDate(LocalDate.parse(params.get("invoiceDate")));
        invoice.setAutoPrice(params.containsKey("invoiceAutoPriceCheckBox"));
    }
}
