package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by Alex Korneyko on 16.08.2016 12:44.
 */
public class ServiceClass {

    public static <T> TableView<T> getTableView(Tab stage) {
        AnchorPane anchorPane = (AnchorPane) stage.getContent();
        VBox vBox = ((VBox) anchorPane.getChildren().get(0));
        AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(1);
        return (TableView<T>) anchorPane1.getChildren().get(0);
    }

    public static <T> void setColumns(TableView<T> tableView, String columnName, String propertyName) {
        TableColumn<T, String> tableColumn = new TableColumn<>(columnName);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        tableView.getColumns().add(tableColumn);
    }
}
