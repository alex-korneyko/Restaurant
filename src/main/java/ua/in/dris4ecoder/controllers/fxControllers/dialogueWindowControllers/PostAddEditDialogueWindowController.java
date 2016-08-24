package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.BusinessObject;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

/**
 * Created by Alex Korneyko on 08.08.2016 12:21.
 */
public class PostAddEditDialogueWindowController implements AddEditController<EmployeePost> {

    @FXML
    public TextField textFieldEmployeePostName;

    private EmployeePost employeePost;
    private ObservableList<BusinessObject> observableList;

    @Override
    @Transactional
    public void saveAction(ActionEvent actionEvent) {

        if (!textFieldEmployeePostName.getText().isEmpty()) {
            if (employeePost != null) {
                employeePost.setPostName(textFieldEmployeePostName.getText());
                Main.getStaffController().editEmployeePost(employeePost.getId(), employeePost.getPostName());
            } else {
                if (observableList != null) {
                    Main.getStaffController().addEmployeePost(textFieldEmployeePostName.getText());
                    observableList.add(Main.getStaffController().findEmployeePost(textFieldEmployeePostName.getText()).get(0));
                }
            }
        }
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage window = (Stage) source.getScene().getWindow();
        window.hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void setValueForEditing(EmployeePost valueForEditing) {
        employeePost = valueForEditing;
        textFieldEmployeePostName.setText(valueForEditing == null ? "" : valueForEditing.getPostName());
    }

    public void setEmployeePost(EmployeePost post) {
        employeePost = post;
        textFieldEmployeePostName.setText(post == null ? "" : post.getPostName());
    }

    public void init(ObservableList observableList, Stage stage) {
        this.observableList = observableList;
    }
}
