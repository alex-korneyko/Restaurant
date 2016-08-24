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
    private ObservableList<Ingredient> ingredientObservableList;
    private Dish dish;
    private Stage mainStage;

    public DishAddEditDialogueWindowController() {
    }

    public void addIngredientsToDishAction() throws IOException {

        DialogueWindows.getStage("ingredientsListStage").setTitle("Ингредиенты");
        DialogueWindows.getStage("ingredientsListStage").showAndWait();
    }

    public void removeIngredientFromDishAction() {

        final Ingredient selectedItem = tableViewIngredients.getSelectionModel().getSelectedItem();
        ingredientObservableList.remove(selectedItem);
    }

    public void clearIngredientListFromDishAction() {
        ingredientObservableList.clear();
    }

    public void editIngredientWeightInDishAction() {
        // TODO: 18.08.2016
    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if(textFieldName.getText().isEmpty()) return;

        if(dish == null) {
            dish = new Dish();
            fillDish();
            Main.getManagementController().addDish(dish);
        } else {
            fillDish();
            Main.getManagementController().editDish(dish.getId(), dish);
        }

        dishObservableList.clear();
        dishObservableList.addAll(Main.getManagementController().findAllDishes());
        dish = null;
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void init(ObservableList<Dish> observableList) throws Exception {
        this.dishObservableList = observableList;

        comboBoxCategory.setItems(new ObservableListWrapper<>(DishCategory.stringValues()));
        this.dishObservableList = observableList;
        ingredientObservableList = FXCollections.observableArrayList();
        ServiceClass.setColumns(tableViewIngredients, "id", "idProp");
        ServiceClass.setColumns(tableViewIngredients, "Ингредиент", "ingredientNameProp");
        ServiceClass.setColumns(tableViewIngredients, "Вес", "");
        tableViewIngredients.getColumns().get(0).setPrefWidth(50);
        tableViewIngredients.getColumns().get(1).setPrefWidth(200);
        tableViewIngredients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewIngredients.setItems(ingredientObservableList);

        if ((DialogueWindows.getStage("ingredientsListStage") == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientSelectList.fxml"));
            DialogueWindows.createStage("ingredientsListStage", mainStage, fxmlLoader, ingredientObservableList);
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
            ingredientObservableList.clear();
        } else {
            textFieldName.setText(dish.getDishName());
            textFieldPrice.setText(String.valueOf(dish.getPrice()));
            textFieldWeight.setText(String.valueOf(dish.getWeight()));
            comboBoxCategory.setValue(dish.getDishCategory().toString());
            ingredientObservableList.clear();
            List<Ingredient> ingredients = new ArrayList<>(Main.getManagementController().findDish(dish.getId()).getIngredients());
            ingredientObservableList.addAll(ingredients);
        }
    }

    private void fillDish() {
        dish.setDishName(textFieldName.getText());
        dish.setDishCategory(DishCategory.getValueByStringName(comboBoxCategory.getValue()));
        dish.setIngredients(tableViewIngredients.getItems());
        dish.setPrice(Double.parseDouble(textFieldPrice.getText()));
        dish.setWeight(Double.parseDouble(textFieldWeight.getText()));
    }
}
