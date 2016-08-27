package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

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
    private Stage controlledStage;


    public void createNewIngredientAction() {

        final Stage ingredientAddEditStage = DialogueWindows.getStage("ingredientAddEditStage");
        ingredientAddEditStage.setTitle("Создать");
        ingredientAddEditStage.showAndWait();
        ingredientsFullList.clear();
        ingredientsFullList.addAll(Main.getManagementController().findAllIngredients());
    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        final ObservableList<Ingredient> selectedItems = tableViewIngredientsList.getSelectionModel().getSelectedItems();
        selectedIngredientsList.addAll(selectedItems);
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setMainStage(Stage mainStage) {

        this.mainStage = mainStage;
    }

    public void init(ObservableList<Ingredient> selectedIngredientsList, Stage thisStage) throws Exception {
        this.selectedIngredientsList = selectedIngredientsList;
        this.controlledStage = thisStage;

        ServiceClass.setColumns(tableViewIngredientsList, "id", "idProp");
        ServiceClass.setColumns(tableViewIngredientsList, "Название", "ingredientNameProp");
        tableViewIngredientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ingredientsFullList.addAll(Main.getManagementController().findAllIngredients());
        tableViewIngredientsList.setItems(ingredientsFullList);

        if(DialogueWindows.getStage("ingredientAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("ingredientAddEditStage", controlledStage, fxmlLoader, ingredientsFullList);
        }
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {

    }

    @Override
    public Ingredient getNewValue() {
        return null;
    }

    public void keyReleasedAction() {
        CharSequence mask = textFieldFilter.getText();
        List<Ingredient> filteredIngredients =
                Main.getManagementController().findAllIngredients().stream().filter(ingredient -> ingredient.getIngredientName().contains(mask)).collect(Collectors.toList());
        ingredientsFullList.clear();
        ingredientsFullList.addAll(filteredIngredients);
    }
}
