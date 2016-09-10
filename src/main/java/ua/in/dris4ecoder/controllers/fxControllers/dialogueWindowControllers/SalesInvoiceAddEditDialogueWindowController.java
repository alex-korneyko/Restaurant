package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 07.09.2016 17:42.
 */
public class SalesInvoiceAddEditDialogueWindowController implements AddEditController<SalesInvoice> {

    public TableView<Ingredient> tableViewIngredients;
    public TextField textFieldAmount;
    public CheckBox checkBoxAutoAmount;
    public TextField textFieldContractor;
    public DatePicker datePickerInvoiceDate;
    public TextField textFieldIdFromOrder;

    private ObservableList<SalesInvoice> invoicesObservableList;
    private Stage controlledStage;
    private ObservableList<Ingredient> ingredientsInCurrentInvoiceObservableList;
    private ObservableList<Contractor> contractorObservableList;
    private SalesInvoice salesInvoice;


    @Override
    public void saveAction(ActionEvent actionEvent) {

        if (textFieldContractor.getText().isEmpty() || tableViewIngredients.getItems().isEmpty()) {
            System.out.println("Error");
            return;
        }

        if (salesInvoice == null) {
            salesInvoice = new SalesInvoice();
            fillInvoice();
            Main.getManagementController().addSalesInvoice(salesInvoice, controlledStage);
        } else {
            fillInvoice();
            Main.getManagementController().editSalesInvoice(salesInvoice, controlledStage);
        }

        invoicesObservableList.clear();
        invoicesObservableList.addAll(Main.getManagementController().findAllSalesInvoices());
        salesInvoice = null;
        controlledStage.hide();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        controlledStage.hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<SalesInvoice> observableList, Stage thisStage) throws Exception {

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

        if ((DialogueWindows.getStage("salesIngredientsListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectList.fxml"));
            DialogueWindows.createStage("salesIngredientsListStage", controlledStage, fxmlLoader, ingredientsInCurrentInvoiceObservableList);
        }

        contractorObservableList = FXCollections.observableArrayList();
        if ((DialogueWindows.getStage("contractorsSalesPurchaseListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/contractorSelectList.fxml"));
            DialogueWindows.createStage("contractorsSalesPurchaseListStage", controlledStage, fxmlLoader, contractorObservableList);
        }

        if (DialogueWindows.getStage("salesIngredientParams") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectedFromListParams.fxml"));
            DialogueWindows.createStage("salesIngredientParams", controlledStage, fxmlLoader, ingredientsInCurrentInvoiceObservableList);
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
    public void setValueForEditing(SalesInvoice valueForEditing) {

        this.salesInvoice = valueForEditing;
        fillStageFields(valueForEditing);
    }

    @Override
    public SalesInvoice getNewValue() {
        return salesInvoice;
    }

    private void autoPrice() {
        if (checkBoxAutoAmount.isSelected()) {
            final double sum = ingredientsInCurrentInvoiceObservableList.stream().mapToDouble(Ingredient::getIngredientPriceOfWeight).sum();
            textFieldAmount.setText(String.valueOf(sum));
        }
    }

    private void fillStageFields(SalesInvoice valueForEditing) {

        textFieldContractor.setText(valueForEditing == null ? "" : valueForEditing.getContractor().getContractorName());
        textFieldAmount.setText(String.valueOf(valueForEditing == null ? "0.0" : valueForEditing.getAmountOfInvoice()));
        checkBoxAutoAmount.setSelected(valueForEditing != null && valueForEditing.isAutoPrice());
        ingredientsInCurrentInvoiceObservableList.clear();
        datePickerInvoiceDate.setValue(valueForEditing == null ? LocalDate.now() : valueForEditing.getInvoiceDate());
        Order order = valueForEditing == null ? new Order(0) : valueForEditing.getOrder();
        String orderId = String.valueOf(order == null ? "" : order.getId());
        textFieldIdFromOrder.setText(orderId);

        if (valueForEditing != null) {
            final SalesInvoice salesInvoice = Main.getManagementController().findSalesInvoice(valueForEditing.getId());
            List<Ingredient> ingredients = new ArrayList<>(salesInvoice.getIngredients());
            ingredientsInCurrentInvoiceObservableList.addAll(ingredients);
        }

    }

    private void fillInvoice() {
        salesInvoice.setContractor(Main.getManagementController().findContractor(textFieldContractor.getText()).get(0));
        salesInvoice.setAmountOfInvoice(Double.parseDouble(textFieldAmount.getText()));
        salesInvoice.setIngredients(tableViewIngredients.getItems());
        salesInvoice.setInvoiceDate(datePickerInvoiceDate.getValue());
        salesInvoice.setAutoPrice(checkBoxAutoAmount.isSelected());
    }

    public void checkBoxAutoPriceAction(ActionEvent actionEvent) {

        if (checkBoxAutoAmount.isSelected()) {
            textFieldAmount.setEditable(false);
            autoPrice();
        } else {
            textFieldAmount.setEditable(true);
        }
    }

    public void selectContractorAction(Event event) {

        DialogueWindows.getStage("contractorsSalesPurchaseListStage").setTitle("Контрагент");
        DialogueWindows.getStage("contractorsSalesPurchaseListStage").showAndWait();

        if (!contractorObservableList.isEmpty())
            textFieldContractor.setText(contractorObservableList.get(0).getContractorName());
    }

    public void addIngredientsToInvoiceAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("salesIngredientsListStage").setTitle("Ингредиенты");
        DialogueWindows.getStage("salesIngredientsListStage").showAndWait();
        final Ingredient ingredient = (Ingredient) DialogueWindows.getController("salesIngredientsListStage").getNewValue();

        if (ingredient != null) {
            DialogueWindows.getStage("salesIngredientParams").setTitle("Параметры");
            DialogueWindows.getController("salesIngredientParams").setValueForEditing(ingredient);
            DialogueWindows.getStage("salesIngredientParams").showAndWait();
        }

        autoPrice();
    }

    public void editIngredientInInvoiceAction(ActionEvent actionEvent) {

        final Ingredient ingredient = tableViewIngredients.getSelectionModel().getSelectedItem();
        if (ingredient != null) {
            DialogueWindows.getStage("salesIngredientParams").setTitle("Параметры");
            DialogueWindows.getController("salesIngredientParams").setValueForEditing(ingredient);
            DialogueWindows.getStage("salesIngredientParams").showAndWait();
        }

        autoPrice();
    }

    public void removeIngredientFromInvoiceAction(ActionEvent actionEvent) {

        final Ingredient ingredient = tableViewIngredients.getSelectionModel().getSelectedItem();
        if (ingredient != null) {
            ingredientsInCurrentInvoiceObservableList.remove(ingredient);
            salesInvoice.getIngredients().remove(ingredient);
        }

        autoPrice();
    }

    public void clearIngredientListFromInvoiceAction(ActionEvent actionEvent) {

        ingredientsInCurrentInvoiceObservableList.clear();
        salesInvoice.getIngredients().clear();
        autoPrice();
    }
}
