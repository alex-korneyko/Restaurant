package ua.in.dris4ecoder.gui.customControls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 06.08.2016 11:33.
 */
public class CustomTab extends Tab {

    private TableView tableView;

    public CustomTab(String text) {
        super(text);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tableView = getTableView();
    }

    public void setDisableSearchByDataRange(boolean disable) {

        AnchorPane anchorPane = (AnchorPane) getContent();
        VBox vBox = ((VBox) anchorPane.getChildren().get(0));
        AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(0);
        ToolBar toolBar = ((ToolBar) anchorPane1.getChildren().get(0));
        MenuButton menuButton = (MenuButton) toolBar.getItems().filtered(item -> item instanceof MenuButton).get(0);
        MenuItem menuItem = menuButton.getItems().filtered(item -> item.getId().equals("searchByDataRange")).get(0);
        HBox hBox = (HBox) menuItem.getGraphic();
        hBox.getChildren().forEach(node -> node.setDisable(disable));
    }

    /**
     * Method create columns in tableView.
     * @param columns
     */
    public void setColumns(String... columns) {

        for (String columnName : columns) {
            TableColumn tableColumn = new TableColumn<>(columnName);
            tableView.getColumns().add(tableColumn);
        }
    }

    private TableView getTableView() {
        AnchorPane anchorPane = (AnchorPane) getContent();
        VBox vBox = ((VBox) anchorPane.getChildren().get(0));
        AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(1);
        return (TableView) anchorPane1.getChildren().get(0);
    }

    @FXML protected void getAllAction(ActionEvent actionEvent) {

        System.out.println(getText());

    }
}
