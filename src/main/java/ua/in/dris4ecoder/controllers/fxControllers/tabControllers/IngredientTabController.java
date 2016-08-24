package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 16.08.2016 19:01.
 */
public class IngredientTabController {

    private ObservableList<Ingredient> observableList;
    private TableView<Ingredient> tableView;

    public IngredientTabController(ObservableList<Ingredient> observableList, Stage mainStage) throws Exception {

        this.observableList = observableList;

        if(DialogueWindows.getStage("ingredientAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("ingredientAddEditStage", mainStage, fxmlLoader, observableList);
        }
    }

    @FXML
    public void addAction(ActionEvent actionEvent) throws IOException {

        Stage ingredientAddEditStage = DialogueWindows.getStage("ingredientAddEditStage");
        ingredientAddEditStage.setTitle("Создать");
        ingredientAddEditStage.showAndWait();

    }

    @FXML
    public void editAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("ingredientAddEditStage").setTitle("Изменить");
        Ingredient selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) return;
        DialogueWindows.getController("ingredientAddEditStage").setValueForEditing(selectedItem);
        DialogueWindows.getStage("ingredientAddEditStage").showAndWait();
    }

    @FXML
    public void deleteAction(ActionEvent actionEvent) {

        final Ingredient selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getManagementController().removeIngredient(selectedItem.getId());
        getAllAction(actionEvent);
    }

    @FXML
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        final List<Ingredient> allIngredients = Main.getManagementController().findAllIngredients();
        observableList.addAll(allIngredients);
    }

    public void init(TableView<Ingredient> tableView) {

        this.tableView = tableView;
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
