package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 16.08.2016 19:22.
 */
public class EmployeeTabController implements TabController<Employee> {

    private ObservableList<Employee> observableList;
    private TableView<Employee> tableView;
    private Stage mainStage;


    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {

        Stage employeeAddEditStage = DialogueWindows.getStage("employeeAddEditStage");
        employeeAddEditStage.setTitle("Создать");
        DialogueWindows.getController("employeeAddEditStage").setValueForEditing(null);
        employeeAddEditStage.showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        Employee selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Ничего не выбрано", mainStage);
            return;
        }

        final Stage employeeAddEditStage = DialogueWindows.getStage("employeeAddEditStage");
        employeeAddEditStage.setTitle("Изменить");
        DialogueWindows.getController("employeeAddEditStage").setValueForEditing(selectedItem);
        employeeAddEditStage.showAndWait();

        getAllAction(actionEvent);
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {

        final Employee selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getStaffController().removeEmployee(selectedItem.getId());
        getAllAction(actionEvent);
    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<Employee> employees = Main.getStaffController().getAllEmployees();
        observableList.addAll(employees);
    }

    @Override
    public void init(Stage mainStage, TableView<Employee> tableView) throws Exception {

        this.tableView = tableView;
        this.mainStage = mainStage;
        observableList = tableView.getItems();

        if(DialogueWindows.getStage("employeeAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/emAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("employeeAddEditStage", mainStage, fxmlLoader, observableList);
        }

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
