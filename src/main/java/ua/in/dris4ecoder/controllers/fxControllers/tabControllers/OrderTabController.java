package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.Order;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

/**
 * Created by Alex Korneyko on 28.08.2016 11:05.
 */
public class OrderTabController implements TabController<Order> {

    private Stage mainStage;
    private TableView<Order> tableView;
    private ObservableList<Order> observableList;

    @Override
    public void addAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("orderAddEditStage").setTitle("Создать");
        DialogueWindows.getController("orderAddEditStage").setValueForEditing(null);
        DialogueWindows.getStage("orderAddEditStage").showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Ничего не выбрано", mainStage);
            return;
        }

        DialogueWindows.getStage("orderAddEditStage").setTitle("Изменить");
        DialogueWindows.getController("orderAddEditStage").setValueForEditing(tableView.getSelectionModel().getSelectedItem());
        DialogueWindows.getStage("orderAddEditStage").showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {


    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

    }

    @Override
    public void init(Stage mainStage, TableView<Order> tableView) throws Exception {

        this.mainStage = mainStage;
        this.mainStage = mainStage;
        this.observableList = tableView.getItems();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });

        if(DialogueWindows.getStage("orderAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/orderAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("orderAddEditStage", mainStage, fxmlLoader, observableList);
        }
    }
}
