package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.IngredientAddEditDialogueWindowController;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 16.08.2016 19:01.
 */
public class IngredientTabController {

    private ObservableList<Ingredient> observableList;
    private Stage mainStage;
    private Stage ingredientAddEditStage;
    private IngredientAddEditDialogueWindowController ingredientAddEditDialogueWindowController;
    private TableView<Ingredient> tableView;

    public IngredientTabController(ObservableList<Ingredient> observableList, Stage mainStage) throws IOException {
        this.observableList = observableList;
        this.mainStage = mainStage;
        createStage();
    }

    @FXML
    public void addAction(ActionEvent actionEvent) throws IOException {

        ingredientAddEditStage.setTitle("Создать");
        ingredientAddEditStage.showAndWait();

    }

    @FXML
    public void editAction(ActionEvent actionEvent) {

        ingredientAddEditStage.setTitle("Изменить");
        final Ingredient selectedItem = tableView.getSelectionModel().getSelectedItem();
        ingredientAddEditDialogueWindowController.setIngredient(selectedItem);
        ingredientAddEditStage.showAndWait();
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

    private void createStage() throws IOException {

        if(ingredientAddEditStage == null) {
            ingredientAddEditStage = new Stage();
            ingredientAddEditStage.setResizable(false);
            ingredientAddEditStage.initModality(Modality.WINDOW_MODAL);
            ingredientAddEditStage.initOwner(mainStage);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientAddEdit.fxml"));
            ingredientAddEditStage.setScene(new Scene(fxmlLoader.load()));
            ingredientAddEditDialogueWindowController = fxmlLoader.getController();
            ingredientAddEditDialogueWindowController.setObservableList(observableList);
        }
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
