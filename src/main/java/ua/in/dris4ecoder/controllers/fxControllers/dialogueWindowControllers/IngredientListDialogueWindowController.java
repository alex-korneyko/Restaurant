package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
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
    public TableView<Ingredient> tableViewAllIngredientsList;
    public TextField textFieldFilter;

    private ObservableList<Ingredient> ingredientsFullList = FXCollections.observableArrayList();
    private ObservableList<Ingredient> ingredientsInCurrentDish;
    private Stage controlledStage;
    private Ingredient ingredient;

    public void createNewIngredientAction() {

        final Stage ingredientAddEditStage = DialogueWindows.getStage("ingredientAddEditStage");
        ingredientAddEditStage.setTitle("Создать");
        ingredientAddEditStage.showAndWait();
        ingredientsFullList.clear();
        ingredientsFullList.addAll(Main.getInstrumentsController().findAllIngredients());
    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        ingredient = tableViewAllIngredientsList.getSelectionModel().getSelectedItem();

        controlledStage.close();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ingredient = null;
        controlledStage.close();
    }

    public void setMainStage(Stage mainStage) {

    }

    public void init(ObservableList<Ingredient> selectedIngredientsList, Stage thisStage) throws Exception {
        this.ingredientsInCurrentDish = selectedIngredientsList;
        this.controlledStage = thisStage;

        ServiceClass.setColumns(tableViewAllIngredientsList, "id", "idProp");
        ServiceClass.setColumns(tableViewAllIngredientsList, "Название", "ingredientNameProp");
        tableViewAllIngredientsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ingredientsFullList.addAll(Main.getInstrumentsController().findAllIngredients());
        tableViewAllIngredientsList.setItems(ingredientsFullList);

        if(DialogueWindows.getStage("ingredientAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/ingredientAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("ingredientAddEditStage", controlledStage, fxmlLoader, ingredientsFullList);
        }

        tableViewAllIngredientsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                saveAction(new ActionEvent());
            }
        });
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {

    }

    @Override
    public Ingredient getNewValue() {
        return ingredient;
    }

    public void keyReleasedAction() {
        CharSequence mask = textFieldFilter.getText();
        List<Ingredient> filteredIngredients =
                Main.getInstrumentsController().findAllIngredients().stream().filter(ingredient -> ingredient.getIngredientName().contains(mask)).collect(Collectors.toList());
        ingredientsFullList.clear();
        ingredientsFullList.addAll(filteredIngredients);
    }
}
