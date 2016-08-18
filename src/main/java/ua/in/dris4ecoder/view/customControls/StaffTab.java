package ua.in.dris4ecoder.view.customControls;

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
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.EmployeeAddEditDialogueWindowController;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.PostAddEditDialogueWindowController;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
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
public class StaffTab<T> extends Tab {

    @FXML private TableView<T> tableView;
    private ObservableList<T> observableList;
    private Parent emPostAddEditParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private PostAddEditDialogueWindowController postAddEditDialogueWindowController;
    private EmployeeAddEditDialogueWindowController employeeAddEditDialogueWindowController;
    private Stage mainStage;

    private Stage emPostAddEditStage;
    private Stage emAddEditStage;

    @FXML
    private void initialize() {
    }

    public StaffTab(String text, String id, Stage mainStage) {
        super(text);
        setId(id);

        observableList = FXCollections.observableArrayList();
        this.mainStage = mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/postAndEmployeeTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tableView = ServiceClass.getTableView(this);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                editAction(new ActionEvent());
            }
        });
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

    @FXML
    @Transactional
    public void addAction(ActionEvent actionEvent) throws IOException {

        fxmlLoader = new FXMLLoader();

        if(getId().equals("posts")) {

            if(emPostAddEditStage == null) {
                postAddEditDialogueWindowController = (PostAddEditDialogueWindowController) initLoader("/fxml/emPostAddEditDialogueWindow.fxml");
                emPostAddEditStage = createAddEditWindow("Добавить должность");
            }
            postAddEditDialogueWindowController.setEmployeePost(null);
            postAddEditDialogueWindowController.setObservableList(observableList);
            emPostAddEditStage.showAndWait();
        }

        if (getId().equals("employees")) {
//            if(emAddEditStage == null) {
                employeeAddEditDialogueWindowController = (EmployeeAddEditDialogueWindowController) initLoader("/fxml/emAddEditDialogueWindow.fxml");
                emAddEditStage = createAddEditWindow("Добавить сотрудника");
//            }
            employeeAddEditDialogueWindowController.setEmployee(null);
            employeeAddEditDialogueWindowController.setObservableList(observableList);
            employeeAddEditDialogueWindowController.setOwner(this);
            emAddEditStage.showAndWait();
            getAllAction(actionEvent);
        }
    }

    @FXML
    @Transactional
    protected void editAction(ActionEvent actionEvent) {

        if(tableView.getSelectionModel().getSelectedItem() == null)
            return;

        fxmlLoader = new FXMLLoader();

        if(this.getId().equals("posts")) {
            EmployeePost selectedItem = (EmployeePost) tableView.getSelectionModel().getSelectedItem();
            if(emPostAddEditStage == null) {
                postAddEditDialogueWindowController = (PostAddEditDialogueWindowController) initLoader("/fxml/emPostAddEditDialogueWindow.fxml");
                emPostAddEditStage = createAddEditWindow("Изменить должность");
            }
            postAddEditDialogueWindowController.setEmployeePost(selectedItem);
            emPostAddEditStage.showAndWait();
        }

        if (this.getId().equals("employees")) {
            Employee selectedItem = (Employee) tableView.getSelectionModel().getSelectedItem();
            selectedItem.getEmployeePost().setId(Main.getStaffController().findEmployeePost(selectedItem.getEmployeePost().getPostName()).get(0).getId());
            if (emAddEditStage == null) {
                employeeAddEditDialogueWindowController = (EmployeeAddEditDialogueWindowController) initLoader("/fxml/emAddEditDialogueWindow.fxml");
                emAddEditStage = createAddEditWindow("Изменить сотрудника");
            }
            employeeAddEditDialogueWindowController.setEmployee(selectedItem);
            employeeAddEditDialogueWindowController.setOwner(this);
            emAddEditStage.showAndWait();
            getAllAction(actionEvent);
        }
    }

    @FXML
    @Transactional
    protected void deleteAction(ActionEvent actionEvent) {

        if(this.getId().equals("posts")) {
            EmployeePost selectedItem = (EmployeePost) tableView.getSelectionModel().getSelectedItem();
            Main.getStaffController().removeEmployeePost(selectedItem.getId());
            observableList.removeIf(ePost -> ((EmployeePost) ePost).getId() == selectedItem.getId());
        }

        if(this.getId().equals("employees")) {
            Employee selectedItem = (Employee) tableView.getSelectionModel().getSelectedItem();
            Main.getStaffController().removeEmployee(selectedItem.getId());
            observableList.removeIf(em -> ((Employee) em).getId() == selectedItem.getId());
        }
    }

    @FXML
    @Transactional
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
