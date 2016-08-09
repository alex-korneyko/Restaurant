package ua.in.dris4ecoder.gui.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.EmAddEditController;
import ua.in.dris4ecoder.controllers.fxControllers.EmPostAddEditController;
import ua.in.dris4ecoder.model.businessObjects.BusinessObject;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 06.08.2016 11:33.
 */
public class CustomTab<T> extends Tab {

    @FXML private TableView<T> tableView;
    private ObservableList<T> observableList;
    private Parent emPostAddEditParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EmPostAddEditController emPostAddEditController;
    private EmAddEditController emAddEditController;
    private Stage mainStage;

    private Stage emPostAddEditStage;
    private Stage emAddEditStage;

    @FXML
    private void initialize() {

        tableView = getTableView();

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                editAction(new ActionEvent());
            }
        });
    }

    public CustomTab(String text, String id, Stage mainStage) {
        super(text);

        observableList = FXCollections.observableArrayList();
        this.mainStage = mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setId(id);
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

        //Receiving list of businessObjectClass fields
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
    protected void addAction(ActionEvent actionEvent) throws IOException {

        if(getId().equals("posts")) {
//            createAddEditWindow("/emPostAddEdit.fxml", "Новая должность");
        }

        if (getId().equals("employees")) {
//            createAddEditWindow("/emAddEdit.fxml", "Новый сотрудник");
        }
    }

    @FXML
    protected void editAction(ActionEvent actionEvent) {

        if(tableView.getSelectionModel().getSelectedItem() == null)
            return;

        if(this.getId().equals("posts")) {
            EmployeePost selectedItem = (EmployeePost) tableView.getSelectionModel().getSelectedItem();
            if(emPostAddEditStage == null) {
                emPostAddEditController = (EmPostAddEditController) initLoader("/emPostAddEdit.fxml");
                emPostAddEditStage = createAddEditWindow("Изменить должность");
            }
            emPostAddEditController.setEmployeePost(selectedItem);
            emPostAddEditStage.showAndWait();
        }

        if (this.getId().equals("employees")) {
            Employee selectedItem = (Employee) tableView.getSelectionModel().getSelectedItem();
            if (emAddEditStage == null) {
                emAddEditController = (EmAddEditController) initLoader("/emAddEdit.fxml");
                emAddEditStage = createAddEditWindow("Изменить сотрудника");
            }
            emAddEditController.setEmployee(selectedItem);
            emAddEditStage.showAndWait();
        }
    }

    @FXML
    protected void deleteAction(ActionEvent actionEvent) {

    }

    @FXML
    protected void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<? extends T> businessObjects = new ArrayList<>();

        if(getId().equals("posts")) {
            businessObjects = (List<? extends T>) Main.getStaffController().getAllEmployeePosts();
        }

        if(getId().equals("employees")) {
            businessObjects = (List<? extends T>) Main.getStaffController().getAllEmployees();
        }

        observableList.addAll(businessObjects);
    }

    private Stage createAddEditWindow(String stageTitle) {

        Stage stage = new Stage();

        stage.setTitle(stageTitle);
        stage.setResizable(false);
        stage.setScene(new Scene(emPostAddEditParent));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);

        return stage;
    }

    private Object initLoader(String resource) {
        try {
            fxmlLoader.setLocation(getClass().getResource(resource));
            emPostAddEditParent = fxmlLoader.load();
            return fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
