package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 17.08.2016 19:54.
 */
public class DishTabController {

    @FXML private TableView<Dish> tableView;
    private ObservableList<Dish> observableList;

    public DishTabController(ObservableList<Dish> observableList, Stage mainStage) throws Exception {

        this.observableList = observableList;
        if(DialogueWindows.getStage("dishAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dishAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("dishAddEditStage", mainStage, fxmlLoader, observableList);
        }
    }

    public void addAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("dishAddEditStage").setTitle("Создать");
        DialogueWindows.getController("dishAddEditStage").setValueForEditing(null);
        DialogueWindows.getStage("dishAddEditStage").showAndWait();
    }

    public void deleteAction(ActionEvent actionEvent) {
        final Dish selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getManagementController().removeDish(selectedItem.getId());
        getAllAction(actionEvent);
    }

    public void editAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("dishAddEditStage").setTitle("Изменить");
        DialogueWindows.getController("dishAddEditStage").setValueForEditing(tableView.getSelectionModel().getSelectedItem());
        DialogueWindows.getStage("dishAddEditStage").showAndWait();

    }

    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        final List<Dish> allDishes = Main.getManagementController().findAllDishes();
        observableList.addAll(allDishes);
    }

    public void init(TableView<Dish> tableView) {

        this.tableView = tableView;
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
