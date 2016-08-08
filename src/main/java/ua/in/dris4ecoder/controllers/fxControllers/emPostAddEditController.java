package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

/**
 * Created by Alex Korneyko on 08.08.2016 12:21.
 */
public class EmPostAddEditController implements EditController {

    @FXML public TextField textFieldEmployeePostName;

    @Override
    public void closeAction(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage window = (Stage) source.getScene().getWindow();
        window.hide();
    }

    public void setEmployeePost(EmployeePost post) {
        textFieldEmployeePostName.setText(post.getPostName());
    }
}
