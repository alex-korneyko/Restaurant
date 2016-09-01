package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 18.08.2016 13:12.
 */
public class DishAddEditDialogueWindowController implements AddEditController<Dish>{

    public TextField textFieldName;
    public TextField textFieldPrice;
    public TextField textFieldWeight;
    public CheckBox checkBoxAutoWeight;
    public ComboBox<String> comboBoxCategory;
    public CheckBox checkBoxAutoPrice;
    public TableView<Ingredient> tableViewIngredients;
    public Button buttonAddIngredient;
    public Button buttonEditIngredient;
    public Button buttonRemoveIngredient;
    public Button buttonClearTabIngredients;
    public Button buttonOk;
    public Button buttonCancel;

    private ObservableList<Dish> dishObservableList;
    private ObservableList<Ingredient> ingredientsInCurrentDishObservableList;
    private Dish dish;
    private Stage mainStage;
    private Stage controlledStage;

    public DishAddEditDialogueWindowController() {
    }

    public void addIngredientsToDishAction() throws IOException {

        DialogueWindows.getStage("ingredientsListStage").setTitle("Ингредиенты");
        DialogueWindows.getStage("ingredientsListStage").showAndWait();
        final Ingredient ingredient = (Ingredient) DialogueWindows.getController("ingredientsListStage").getNewValue();

        if (ingredient != null) {
            DialogueWindows.getStage("ingredientParams").setTitle("Параметры");
            DialogueWindows.getController("ingredientParams").setValueForEditing(ingredient);
            DialogueWindows.getStage("ingredientParams").showAndWait();
        }

        autoPrice();
        autoWeight();
    }

    public void removeIngredientFromDishAction() {

        final Ingredient selectedItem = tableViewIngredients.getSelectionModel().getSelectedItem();
        ingredientsInCurrentDishObservableList.remove(selectedItem);
        autoPrice();
        autoWeight();
    }

    public void clearIngredientListFromDishAction() {
        ingredientsInCurrentDishObservableList.clear();
        autoPrice();
        autoWeight();
    }

    public void editIngredientWeightInDishAction() {
        // TODO: 18.08.2016


        autoPrice();
        autoWeight();
    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if(textFieldName.getText().isEmpty()) return;

        if(dish == null) {
            dish = new Dish();
            fillDish();
            Main.getInstrumentsController().addDish(dish);
        } else {
            fillDish();
            Main.getInstrumentsController().editDish(dish.getId(), dish);
        }

        dishObservableList.clear();
        dishObservableList.addAll(Main.getInstrumentsController().findAllDishes());
        dish = null;
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void init(ObservableList<Dish> observableList, Stage thisStage) throws Exception {
        this.dishObservableList = observableList;
        this.controlledStage = thisStage;

        comboBoxCategory.setItems(new ObservableListWrapper<>(DishCategory.stringValues()));
        this.dishObservableList = observableList;
        ingredientsInCurrentDishObservableList = FXCollections.observableArrayList();
        ServiceClass.setColumns(tableViewIngredients, "id", "idProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Ингредиент", "ingredientNameProp", 200);
        ServiceClass.setColumns(tableViewIngredients, "Вес", "ingredientWeightProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Цена", "ingredientPriceProp", 50);
        ServiceClass.setColumns(tableViewIngredients, "Сумма", "ingredientPriceOfWeightProp", 50);
        tableViewIngredients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewIngredients.setItems(ingredientsInCurrentDishObservableList);

        if (checkBoxAutoPrice.isSelected()) {
            textFieldPrice.setEditable(false);
        }

        if ((DialogueWindows.getStage("ingredientsListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectList.fxml"));
            DialogueWindows.createStage("ingredientsListStage", controlledStage, fxmlLoader, ingredientsInCurrentDishObservableList);
        }

        if (DialogueWindows.getStage("ingredientParams") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientSelectedFromListParams.fxml"));
            DialogueWindows.createStage("ingredientParams", controlledStage, fxmlLoader, ingredientsInCurrentDishObservableList);
        }
    }

    @Override
    public void setValueForEditing(Dish valueForEditing) {
        this.dish = valueForEditing;

        if(dish == null) {
            this.dish = null;
            textFieldName.setText("");
            textFieldPrice.setText("0.0");
            textFieldWeight.setText("0.0");
            comboBoxCategory.setValue(comboBoxCategory.getItems().get(0));
            ingredientsInCurrentDishObservableList.clear();
        } else {
            textFieldName.setText(dish.getDishName());
            textFieldPrice.setText(String.valueOf(dish.getPrice()));
            textFieldWeight.setText(String.valueOf(dish.getWeight()));
            comboBoxCategory.setValue(dish.getDishCategory().toString());
            ingredientsInCurrentDishObservableList.clear();
            List<Ingredient> ingredients = new ArrayList<>(Main.getInstrumentsController().findDish(dish.getId()).getIngredients());
            ingredientsInCurrentDishObservableList.addAll(ingredients);
        }
    }

    @Override
    public Dish getNewValue() {
        return this.dish;
    }

    private void autoPrice() {
        if (checkBoxAutoPrice.isSelected()) {
            textFieldPrice.setText(String.valueOf(ingredientsInCurrentDishObservableList.stream().mapToDouble(Ingredient::getIngredientPriceOfWeight).sum()));
        }
    }

    private void autoWeight() {
        if (checkBoxAutoWeight.isSelected()) {
            textFieldWeight.setText(String.valueOf(ingredientsInCurrentDishObservableList.stream().mapToDouble(Ingredient::getIngredientWeight).sum()));
        }
    }

    private void fillDish() {
        dish.setDishName(textFieldName.getText());
        dish.setDishCategory(DishCategory.getValueByStringName(comboBoxCategory.getValue()));
        dish.setIngredients(tableViewIngredients.getItems());
        dish.setPrice(Double.parseDouble(textFieldPrice.getText()));
        dish.setWeight(Double.parseDouble(textFieldWeight.getText()));
    }

    public void checkBoxAutoPriceAction(ActionEvent actionEvent) {
        if (checkBoxAutoPrice.isSelected()) {
            textFieldPrice.setEditable(false);
            autoPrice();
        } else {
            textFieldPrice.setEditable(true);
            textFieldPrice.setText("0.0");
        }
    }

    public void checkBoxAutoWeightAction(ActionEvent actionEvent) {
        if (checkBoxAutoWeight.isSelected()) {
            textFieldWeight.setEditable(false);
            autoWeight();
        } else {
            textFieldWeight.setEditable(true);
            textFieldWeight.setText("0.0");
        }
    }
}
