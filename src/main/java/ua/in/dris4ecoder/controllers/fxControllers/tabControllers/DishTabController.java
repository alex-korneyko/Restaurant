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
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 17.08.2016 19:54.
 */
public class DishTabController implements TabController<Dish> {

    @FXML
    private TableView<Dish> tableView;
    private ObservableList<Dish> observableList;
    private Stage mainStage;

    @Override
    public void addAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("dishAddEditStage").setTitle("Создать");
        DialogueWindows.getController("dishAddEditStage").setValueForEditing(null);
        DialogueWindows.getStage("dishAddEditStage").showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {
        final Dish selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getInstrumentsController().removeDish(selectedItem);
        getAllAction(actionEvent);
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Ничего не выбрано", mainStage);
            return;
        }

        DialogueWindows.getStage("dishAddEditStage").setTitle("Изменить");
        DialogueWindows.getController("dishAddEditStage").setValueForEditing(tableView.getSelectionModel().getSelectedItem());
        DialogueWindows.getStage("dishAddEditStage").showAndWait();

    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        final List<Dish> allDishes = Main.getInstrumentsController().findAllDishes();
        observableList.addAll(allDishes);
    }

    @Override
    public void init(Stage mainStage, TableView<Dish> tableView) throws Exception {

        this.mainStage = mainStage;
        this.tableView = tableView;
        this.observableList = tableView.getItems();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });

        if (DialogueWindows.getStage("dishAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/dishAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("dishAddEditStage", mainStage, fxmlLoader, observableList);
        }
    }
}
