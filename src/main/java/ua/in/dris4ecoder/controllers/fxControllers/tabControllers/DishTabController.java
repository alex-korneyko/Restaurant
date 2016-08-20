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
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.DishAddEditDialogueWindowController;
import ua.in.dris4ecoder.model.businessObjects.Dish;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 17.08.2016 19:54.
 */
public class DishTabController {

    @FXML private TableView<Dish> tableView;
    private Stage mainStage;
    private Stage dishAddEditStage;
    private DishAddEditDialogueWindowController dishAddEditDialogueWindowController;
    private ObservableList<Dish> observableList;

    public DishTabController(ObservableList<Dish> observableList, Stage mainStage) throws IOException {

        this.observableList = observableList;
        this.mainStage = mainStage;
        createStage();
    }

    public void addAction(ActionEvent actionEvent) {

        dishAddEditStage.setTitle("Создать");
        dishAddEditDialogueWindowController.setTo(null);
        dishAddEditStage.showAndWait();
    }

    public void deleteAction(ActionEvent actionEvent) {
        final Dish selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getManagementController().removeDish(selectedItem.getId());
        getAllAction(actionEvent);
    }

    public void editAction(ActionEvent actionEvent) {

        dishAddEditStage.setTitle("Изменить");
        dishAddEditDialogueWindowController.setTo(tableView.getSelectionModel().getSelectedItem());
        dishAddEditStage.showAndWait();

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

    private void createStage() throws IOException {

        if (dishAddEditStage != null) return;

        dishAddEditStage = new Stage();
        dishAddEditStage.setResizable(false);
        dishAddEditStage.initModality(Modality.WINDOW_MODAL);
        dishAddEditStage.initOwner(mainStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dishAddEditDialogueWindow.fxml"));
        dishAddEditStage.setScene(new Scene(fxmlLoader.load()));
        dishAddEditDialogueWindowController = fxmlLoader.getController();
        dishAddEditDialogueWindowController.init(observableList, dishAddEditStage);

    }
}
