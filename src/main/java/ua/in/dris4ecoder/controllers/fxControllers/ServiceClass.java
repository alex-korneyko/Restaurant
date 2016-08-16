package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Alex Korneyko on 16.08.2016 12:44.
 */
public class ServiceClass<T> {

    public static <T> TableView getTableView(Tab stage) {
        AnchorPane anchorPane = (AnchorPane) stage.getContent();
        VBox vBox = ((VBox) anchorPane.getChildren().get(0));
        AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(1);
        return (TableView) anchorPane1.getChildren().get(0);
    }
}
