package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 03.09.2016 19:26.
 */
public class PurchaseInvoiceAddEditDialogueWindowController implements AddEditController<PurchaseInvoice> {

    public TableView<Ingredient> tableViewIngredients;
    public TextField textFieldAmount;
    public CheckBox checkBoxAutoAmount;
    public TextField textFieldContractor;
    public DatePicker datePickerInvoiceDate;
    public TextField textFieldIdFromContractor;

    private ObservableList<PurchaseInvoice> invoicesObservableList;
    private Stage controlledStage;
    private ObservableList<Ingredient> ingredientsInCurrentInvoiceObservableList;
    private ObservableList<Contractor> contractorObservableList;
    private PurchaseInvoice purchaseInvoice;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if (textFieldContractor.getText().isEmpty() || tableViewIngredients.getItems().isEmpty()) {
            System.out.println("Error");
            return;
        }

        if (purchaseInvoice == null) {
            purchaseInvoice = new PurchaseInvoice();
            fillInvoice();
            Main.getManagementController().addPurchaseInvoice(purchaseInvoice);
        } else {
            fillInvoice();
            Main.getManagementController().editPurchaseInvoice(purchaseInvoice);
        }

        invoicesObservableList.clear();
        invoicesObservableList.addAll(Main.getManagementController().findAllPurchaseInvoices());
        purchaseInvoice = null;
        controlledStage.hide();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        controlledStage.hide();
    }

    public void selectContractorAction() {

        DialogueWindows.getStage("contractorsListStage").setTitle("Контрагент");
        DialogueWindows.getStage("contractorsListStage").showAndWait();

        if (!contractorObservableList.isEmpty())
            textFieldContractor.setText(contractorObservableList.get(0).getContractorName());
    }

    public void addIngredientsToInvoiceAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("purchaseIngredientsListStage").setTitle("Ингредиенты");
        DialogueWindows.getStage("purchaseIngredientsListStage").showAndWait();
        final Ingredient ingredient = (Ingredient) DialogueWindows.getController("purchaseIngredientsListStage").getNewValue();

        if (ingredient != null) {
            DialogueWindows.getStage("purchaseIngredientParams").setTitle("Параметры");
            DialogueWindows.getController("purchaseIngredientParams").setValueForEditing(ingredient);
            DialogueWindows.getStage("purchaseIngredientParams").showAndWait();
        }

        autoPrice();
    }

    public void editIngredientInInvoiceAction(ActionEvent actionEvent) {

        final Ingredient ingredient = tableViewIngredients.getSelectionModel().getSelectedItem();
        if (ingredient != null) {
            DialogueWindows.getStage("purchaseIngredientParams").setTitle("Параметры");
            DialogueWindows.getController("purchaseIngredientParams").setValueForEditing(ingredient);
            DialogueWindows.getStage("purchaseIngredientParams").showAndWait();
        }

        autoPrice();
    }

    public void removeIngredientFromInvoiceAction(ActionEvent actionEvent) {

        final Ingredient ingredient = tableViewIngredients.getSelectionModel().getSelectedItem();
        if (ingredient != null) {
            ingredientsInCurrentInvoiceObservableList.remove(ingredient);
            purchaseInvoice.getIngredients().remove(ingredient);
        }

        autoPrice();
    }

    public void clearIngredientListFromInvoiceAction(ActionEvent actionEvent) {

        ingredientsInCurrentInvoiceObservableList.clear();
        purchaseInvoice.getIngredients().clear();
        autoPrice();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<PurchaseInvoice> observableList, Stage thisStage) throws Exception {
        this.invoicesObservableList = observableList;
        this.controlledStage = thisStage;

        ingredientsInCurrentInvoiceObservableList = FXCollections.observableArrayList();
        ServiceClass.setColumns(tableViewIngredients, "id", "idProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Ингредиент", "ingredientNameProp", 200);
        ServiceClass.setColumns(tableViewIngredients, "Вес", "ingredientWeightProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Цена", "ingredientPriceProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Сумма", "ingredientPriceOfWeightProp", 50);
        tableViewIngredients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewIngredients.setItems(ingredientsInCurrentInvoiceObservableList);

        if (checkBoxAutoAmount.isSelected()) {
            textFieldAmount.setEditable(false);
        }

        if ((DialogueWindows.getStage("purchaseIngredientsListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectList.fxml"));
            DialogueWindows.createStage("purchaseIngredientsListStage", controlledStage, fxmlLoader, ingredientsInCurrentInvoiceObservableList);
        }

        contractorObservableList = FXCollections.observableArrayList();
        if ((DialogueWindows.getStage("contractorsListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/contractorSelectList.fxml"));
            DialogueWindows.createStage("contractorsListStage", controlledStage, fxmlLoader, contractorObservableList);
        }

        if (DialogueWindows.getStage("purchaseIngredientParams") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectedFromListParams.fxml"));
            DialogueWindows.createStage("purchaseIngredientParams", controlledStage, fxmlLoader, ingredientsInCurrentInvoiceObservableList);
        }

        tableViewIngredients.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editIngredientInInvoiceAction(new ActionEvent());
            }
        });
    }

    public void checkBoxAutoPriceAction() {

        if (checkBoxAutoAmount.isSelected()) {
            textFieldAmount.setEditable(false);
            autoPrice();
        } else {
            textFieldAmount.setEditable(true);
        }
    }

    @Override
    public void setValueForEditing(PurchaseInvoice valueForEditing) {

        this.purchaseInvoice = valueForEditing;
        fillStageFields(valueForEditing);
    }

    @Override
    public PurchaseInvoice getNewValue() {
        return purchaseInvoice;
    }

    private void autoPrice() {
        if (checkBoxAutoAmount.isSelected()) {
            final double sum = ingredientsInCurrentInvoiceObservableList.stream().mapToDouble(Ingredient::getIngredientPriceOfWeight).sum();
            textFieldAmount.setText(String.valueOf(sum));
        }
    }

    private void fillStageFields(PurchaseInvoice valueForEditing) {
        textFieldContractor.setText(valueForEditing == null ? "" : valueForEditing.getContractor().getContractorName());
        textFieldAmount.setText(String.valueOf(valueForEditing == null ? "0.0" : valueForEditing.getAmountOfInvoice()));
        textFieldIdFromContractor.setText(valueForEditing == null ? "" : valueForEditing.getInvoiceIdFromContractor());
        checkBoxAutoAmount.setSelected(valueForEditing != null && valueForEditing.isAutoPrice());
        ingredientsInCurrentInvoiceObservableList.clear();
        datePickerInvoiceDate.setValue(valueForEditing == null ? LocalDate.now() : valueForEditing.getInvoiceDate());

        if (valueForEditing != null) {
            final PurchaseInvoice purchaseInvoice = Main.getManagementController().findPurchaseInvoice(valueForEditing.getId());
            List<Ingredient> ingredients = new ArrayList<>(purchaseInvoice.getIngredients());
            ingredientsInCurrentInvoiceObservableList.addAll(ingredients);
        }

    }

    private void fillInvoice() {
        purchaseInvoice.setContractor(Main.getManagementController().findContractor(textFieldContractor.getText()).get(0));
        purchaseInvoice.setAmountOfInvoice(Double.parseDouble(textFieldAmount.getText()));
        purchaseInvoice.setInvoiceIdFromContractor(textFieldIdFromContractor.getText());
        purchaseInvoice.setIngredients(tableViewIngredients.getItems());
        purchaseInvoice.setInvoiceDate(datePickerInvoiceDate.getValue());
        purchaseInvoice.setAutoPrice(checkBoxAutoAmount.isSelected());
    }
}
