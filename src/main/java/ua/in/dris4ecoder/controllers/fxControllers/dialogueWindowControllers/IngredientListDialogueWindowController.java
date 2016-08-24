package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 18.08.2016 17:58.
 */
public class IngredientListDialogueWindowController implements AddEditController<Ingredient> {
    public TableView<Ingredient> tableViewIngredientsList;
    public TextField textFieldFilter;

    private ObservableList<Ingredient> ingredientsFullList = FXCollections.observableArrayList();
    private ObservableList<Ingredient> selectedIngredientsList;
    private Stage mainStage;


    public void createNewIngredientAction() {

    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        selectedIngredientsList.addAll(tableViewIngredientsList.getSelectionModel().getSelectedItems());
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

    public void init(ObservableList<Ingredient> selectedIngredientsList) {
        this.selectedIngredientsList = selectedIngredientsList;
        ServiceClass.setColumns(tableViewIngredientsList, "id", "idProp");
        ServiceClass.setColumns(tableViewIngredientsList, "Название", "ingredientNameProp");
        tableViewIngredientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ingredientsFullList.addAll(Main.getManagementController().findAllIngredients());
        tableViewIngredientsList.setItems(ingredientsFullList);
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {

    }

    public void keyReleasedAction() {
        CharSequence mask = textFieldFilter.getText();
        List<Ingredient> filteredIngredients =
                Main.getManagementController().findAllIngredients().stream().filter(ingredient -> ingredient.getIngredientName().contains(mask)).collect(Collectors.toList());
        ingredientsFullList.clear();
        ingredientsFullList.addAll(filteredIngredients);
    }
}
