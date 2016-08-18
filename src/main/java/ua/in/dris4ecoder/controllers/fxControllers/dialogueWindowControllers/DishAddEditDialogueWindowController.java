package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;
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

    private List<String> dishCategories;
    private ObservableList<Dish> dishObservableList;
    private ObservableList<Ingredient> ingredientObservableList;
    private Stage ingredientsListStage;
    private IngredientListDialogueWindowController ingredientListDialogueWindowController;

    public DishAddEditDialogueWindowController() {
    }



    public void init(ObservableList<Dish> observableList, Window owner) throws IOException {
        comboBoxCategory.setItems(new ObservableListWrapper<>(DishCategory.stringValues()));
        comboBoxCategory.setValue(comboBoxCategory.getItems().get(0));
        this.dishObservableList = observableList;
        ingredientObservableList = FXCollections.observableArrayList();
        ServiceClass.setColumns(tableViewIngredients, "id", "idProp");
        ServiceClass.setColumns(tableViewIngredients, "Ингредиент", "ingredientNameProp");
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

    public void addIngredientsToDish(ActionEvent actionEvent) {

        ingredientsListStage.setTitle("Ингредиенты");
        ingredientsListStage.showAndWait();
    }
}
