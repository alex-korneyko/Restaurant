package ua.in.dris4ecoder.gui.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.businessObjects.BusinessObject;
import ua.in.dris4ecoder.model.businessObjects.Employee;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 06.08.2016 11:33.
 */
public class CustomTab<T> extends Tab {

    private TableView<T> tableView;
    private ObservableList<T> observableList;

    public CustomTab() {
    }

    public CustomTab(String text, String id) {
        super(text);

        observableList = FXCollections.observableArrayList();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setId(id);

        tableView = getTableView();
    }

    /**
     * Enable or disable searching by Data range
     *
     * @param disable
     */
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

    public void setColumnsByClass(Class<? extends BusinessObject> businessObjectClass, String... columnNames) {

        List<String> fieldNames = Arrays.asList(businessObjectClass.getFields()).stream().map(Field::getName).collect(Collectors.toList());

        if(fieldNames.size() != columnNames.length) {
            throw new RuntimeException("Count of columns and fields does not coincide! Columns: " + columnNames.length + " and fields in class <"
                    + businessObjectClass.getSimpleName() + "> is: " + fieldNames.size());
        }

        for (int i=0; i < columnNames.length; i++) {
            TableColumn<T, String> tableColumn = new TableColumn<>(columnNames[i]);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fieldNames.get(i)));
            tableView.getColumns().add(tableColumn);
        }

        tableView.setItems(observableList);
    }

    private TableView getTableView() {
        AnchorPane anchorPane = (AnchorPane) getContent();
        VBox vBox = ((VBox) anchorPane.getChildren().get(0));
        AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(1);
        return (TableView) anchorPane1.getChildren().get(0);
    }

    @FXML
    protected void getAllAction(ActionEvent actionEvent) {

        observableList.clear();

        if(getId().equals("posts")) {
            List<? extends T> employeePosts = (List<? extends T>) Main.getStaffController().getAllEmployeePosts();
            observableList.addAll(employeePosts);
        }

        if(getId().equals("employees")) {
            List<? extends T> employees = (List<? extends T>) Main.getStaffController().getAllEmployees();
            observableList.addAll(employees);
        }
    }
}
