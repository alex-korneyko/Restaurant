package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 25.08.2016 16:39.
 */
public interface TabController<T> {
    @FXML
    void addAction(ActionEvent actionEvent) throws IOException;

    @FXML
    void editAction(ActionEvent actionEvent);

    @FXML
    void deleteAction(ActionEvent actionEvent);

    @FXML
    void getAllAction(ActionEvent actionEvent);

    void init(Stage mainStage, TableView<T> tableView) throws Exception;
}
