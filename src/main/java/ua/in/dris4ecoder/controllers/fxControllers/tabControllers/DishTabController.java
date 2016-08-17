package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

/**
 * Created by Alex Korneyko on 17.08.2016 19:54.
 */
public class DishTabController {

    @FXML private TableView<Dish> tableView;

    public DishTabController(ObservableList<Dish> observableList, Stage mainStage) {

    }

    public void addAction(ActionEvent actionEvent) {

    }

    public void deleteAction(ActionEvent actionEvent) {

    }

    public void editAction(ActionEvent actionEvent) {

    }

    public void getAllAction(ActionEvent actionEvent) {

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
