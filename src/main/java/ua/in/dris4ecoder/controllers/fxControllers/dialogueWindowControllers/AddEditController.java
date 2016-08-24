package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Created by Alex Korneyko on 08.08.2016 15:50.
 */
public interface AddEditController<T> {

    void saveAction(ActionEvent actionEvent);

    void closeAction(ActionEvent actionEvent);

    void setMainStage(Stage mainStage);

    void init(ObservableList<T> observableList, Stage stage) throws Exception;

    void setValueForEditing(T valueForEditing);
}
