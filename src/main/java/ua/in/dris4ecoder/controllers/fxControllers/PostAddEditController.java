package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.BusinessObject;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

/**
 * Created by Alex Korneyko on 08.08.2016 12:21.
 */
public class PostAddEditController implements AddEditController {

    @FXML
    public TextField textFieldEmployeePostName;

    private EmployeePost employeePost;
    private ObservableList<BusinessObject> observableList;

    @Override
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

    public void setEmployeePost(EmployeePost post) {
        employeePost = post;
        textFieldEmployeePostName.setText(post == null ? "" : post.getPostName());
    }

    public void setObservableList(ObservableList observableList) {
        this.observableList = observableList;
    }
}
