package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.Invoice;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;

import java.util.List;
import java.util.stream.Collectors;

import static ua.in.dris4ecoder.view.customControls.WarningsDialogWindow.WindowType.WARNING;

/**
 * Created by Alex Korneyko on 31.08.2016 12:39.
 */
public class IngredientParamsDialogueWindowController implements AddEditController<Ingredient> {

    public Label labelIngredientName;
    public Label labelUnit;
    public Label labelLastPrice;
    public Label labelAveragePrice;
    public TextField textFieldPrice;
    public TextField textFieldAmount;
    public Label labelWarehouse;
    public Label labelReserved;
    public Label labelCurrency;
    private Ingredient ingredient;
    private ObservableList<Ingredient> observableList;
    private double averageIngredientPrice;
    private Double lastIngredientPrice;
    private Double ingredientAvailability;
    private Stage controlledStage;
    private boolean isPurchase;

    @Override
    public void saveAction(ActionEvent actionEvent) {
        ingredient.setIngredientPrice(Double.parseDouble(textFieldPrice.getText().replace(',', '.')));
        ingredient.setIngredientWeight(Double.parseDouble(textFieldAmount.getText().replace(',', '.')));

        if (!isPurchase && ingredient.getIngredientWeight() > ingredientAvailability) {
            WarningsDialogWindow.showWindow(WARNING, "Не достаточно ингредиентов на складе", controlledStage);
            return;
        }

        if (observableList.contains(ingredient)) {
            observableList.set(observableList.indexOf(ingredient), ingredient);
        } else {
            observableList.add(ingredient);
        }

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {
        String userData = (String) mainStage.getUserData();
        isPurchase = userData.equals("purchaseInvoiceAddEditStage");
    }

    @Override
    public void init(ObservableList<Ingredient> observableList, Stage thisStage) throws Exception {
        this.controlledStage = thisStage;
        this.observableList = observableList;
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {
        this.ingredient = valueForEditing;
        labelIngredientName.setText(valueForEditing.getIngredientName());
        labelUnit.setText(valueForEditing.getUnit().getUnitName());
        labelCurrency.setText(valueForEditing.getCurrency().getShortName());
        textFieldPrice.setText(ingredient.getIngredientPrice() == null ? "0.0" : ingredient.getIngredientPrice().toString());
        textFieldAmount.setText(ingredient.getIngredientWeight() == null ? "0.0" : ingredient.getIngredientWeight().toString());
        generateLastAndAveragePurchasingPrice();
        labelLastPrice.setText(String.valueOf(lastIngredientPrice));
        labelAveragePrice.setText(String.valueOf(averageIngredientPrice));
        generateAvailabilityAndReserved();
        labelWarehouse.setText(String.valueOf(ingredientAvailability));
        labelReserved.setText("0.0");
    }

    @Override
    public Ingredient getNewValue() {
        return null;
    }

    private void generateAvailabilityAndReserved() {

        ingredientAvailability = 0.0;
//        ingredientAvailability = Main.getManagementController().checkAmount(ingredient);

        // TODO: 09.09.2016 not yet implement calculate ingredients which reserved
    }

    private void generateLastAndAveragePurchasingPrice() {
        PurchaseInvoice purchaseInvoice = null;

        List<PurchaseInvoice> allPurchaseInvoices = Main.getManagementController().findAllPurchaseInvoices();

        List<PurchaseInvoice> invoicesWithCurrentIngredient = allPurchaseInvoices.stream().filter(invoice ->
                invoice.getIngredients().contains(ingredient)).collect(Collectors.toList());

        if (invoicesWithCurrentIngredient.isEmpty()) {
            lastIngredientPrice = 0.0;
            averageIngredientPrice = 0.0;
            return;
        }

        // TODO: 10.09.2016 incorrect calculating average price. Ignored weight of ingredient each invoice
        double sumIngredientPrices = invoicesWithCurrentIngredient.stream().map(Invoice::getIngredients).map(ingredients ->
                ingredients.get(ingredients.indexOf(ingredient))).mapToDouble(Ingredient::getIngredientPrice).sum();

        averageIngredientPrice = sumIngredientPrices / invoicesWithCurrentIngredient.size();

        for (PurchaseInvoice invoice : invoicesWithCurrentIngredient) {
            if (purchaseInvoice == null || purchaseInvoice.getInvoiceDate().isBefore(invoice.getInvoiceDate()))
                purchaseInvoice = invoice;
        }

        assert purchaseInvoice != null;
        lastIngredientPrice = purchaseInvoice.getIngredients().get(purchaseInvoice.getIngredients().indexOf(ingredient)).getIngredientPrice();
    }
}
