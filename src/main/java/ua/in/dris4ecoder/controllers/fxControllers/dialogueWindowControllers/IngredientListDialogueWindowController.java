package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 18.08.2016 17:58.
 */
public class IngredientListDialogueWindowController {
    public TableView<Ingredient> tableViewIngredientsList;
    public TextField textFieldFilter;

    private ObservableList<Ingredient> ingredientsFullList = FXCollections.observableArrayList();
    private ObservableList<Ingredient> selectedIngredientsList;



    public void saveSelectedIngredients(ActionEvent actionEvent) {

        selectedIngredientsList.addAll(tableViewIngredientsList.getSelectionModel().getSelectedItems());
        close(actionEvent);
    }

    public void close(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void init(ObservableList<Ingredient> selectedIngredientsList) {
        this.selectedIngredientsList = selectedIngredientsList;
        ServiceClass.setColumns(tableViewIngredientsList, "id", "idProp");
        ServiceClass.setColumns(tableViewIngredientsList, "Название", "ingredientNameProp");
        tableViewIngredientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ingredientsFullList.addAll(Main.getManagementController().findAllIngredients());
        tableViewIngredientsList.setItems(ingredientsFullList);
    }

    public void keyTypedAction(Event event) {

    }

    public void keyReleasedAction(Event event) {
        CharSequence mask = textFieldFilter.getText();
        List<Ingredient> filteredIngredients =
                Main.getManagementController().findAllIngredients().stream().filter(ingredient -> ingredient.getIngredientName().contains(mask)).collect(Collectors.toList());
        ingredientsFullList.clear();
        ingredientsFullList.addAll(filteredIngredients);
    }

    public void refreshIngredients() {

        ingredientsFullList.clear();
        ingredientsFullList.addAll(Main.getManagementController().findAllIngredients());
    }
}
