package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 25.08.2016 13:23.
 */
public class PostTabController implements TabController<EmployeePost> {

    public TableView<EmployeePost> tableView;
    private ObservableList<EmployeePost> observableList;

    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {

        Stage postAddEditStage = DialogueWindows.getStage("postAddEditStage");
        postAddEditStage.setTitle("Создать");
        DialogueWindows.getController("postAddEditStage").setValueForEditing(null);
        postAddEditStage.showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        EmployeePost selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null) return;

        final Stage postAddEditStage = DialogueWindows.getStage("postAddEditStage");
        postAddEditStage.setTitle("Изменить");
        DialogueWindows.getController("postAddEditStage").setValueForEditing(selectedItem);
        postAddEditStage.showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {

        final EmployeePost selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getStaffController().removeEmployeePost(selectedItem.getId());
        getAllAction(actionEvent);
    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<EmployeePost> employeePosts = Main.getStaffController().getAllEmployeePosts();
        observableList.addAll(employeePosts);
    }

    @Override
    public void init(Stage mainStage, TableView<EmployeePost> tableView) throws Exception {

        this.tableView = tableView;
        observableList = tableView.getItems();

        if(DialogueWindows.getStage("postAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/emPostAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("postAddEditStage", mainStage, fxmlLoader, observableList);
        }

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
