package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 18.08.2016 13:12.
 */
public class DishAddEditDialogueWindowController {

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
    private Stage ingredientsListStage;
    private IngredientListDialogueWindowController ingredientListDialogueWindowController;
    private Dish dish;

    public DishAddEditDialogueWindowController() {
    }


    public void addIngredientsToDishAction(ActionEvent actionEvent) {

        ingredientsListStage.setTitle("Ингредиенты");
        ingredientListDialogueWindowController.refreshIngredients();
        ingredientsListStage.showAndWait();
    }

    public void removeIngredientFromDishAction(ActionEvent actionEvent) {

        final Ingredient selectedItem = tableViewIngredients.getSelectionModel().getSelectedItem();
        ingredientObservableList.remove(selectedItem);
    }

    public void clearIngredientListFromDishAction(ActionEvent actionEvent) {
        ingredientObservableList.clear();
    }

    public void editIngredientWeightInDish(ActionEvent actionEvent) {
        // TODO: 18.08.2016
    }

    public void okAction(ActionEvent actionEvent) {

        if(textFieldName.getText().isEmpty()) return;

        dish = new Dish(textFieldName.getText(), DishCategory.getValueByStringName(comboBoxCategory.getValue()));
        List<Ingredient> ingredients = tableViewIngredients.getItems();
        dish.setIngredients(ingredients);
        dish.setPrice(Double.parseDouble(textFieldPrice.getText()));
        dish.setWeight(Double.parseDouble(textFieldWeight.getText()));

        Main.getManagementController().addDish(dish);
        dishObservableList.clear();
        dishObservableList.addAll(Main.getManagementController().findAllDishes());
        closeAction(actionEvent);
    }

    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void init(ObservableList<Dish> observableList, Window owner) throws IOException {
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

        createStage(owner);


    }

    private void createStage(Window owner) throws IOException {
        ingredientsListStage = new Stage();
        ingredientsListStage.setResizable(false);
        ingredientsListStage.initModality(Modality.WINDOW_MODAL);
        ingredientsListStage.initOwner(owner);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientSelectList.fxml"));
        ingredientsListStage.setScene(new Scene(fxmlLoader.load()));
        ingredientListDialogueWindowController = fxmlLoader.getController();
        ingredientListDialogueWindowController.init(ingredientObservableList);
    }

    public void setTo(Dish dish) {
        if(dish == null) {
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
}
