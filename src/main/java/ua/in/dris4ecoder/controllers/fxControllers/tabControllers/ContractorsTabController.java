package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 16:57.
 */
public class ContractorsTabController implements TabController<Contractor> {

    private TableView<Contractor> tableView;
    private ObservableList<Contractor> observableList;
    private Stage mainStage;

    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {
        DialogueWindows.getStage("contractorAddEditStage").setTitle("Создать");
        DialogueWindows.getController("contractorAddEditStage").setValueForEditing(null);
        DialogueWindows.getStage("contractorAddEditStage").showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {
        Contractor contractor = tableView.getSelectionModel().getSelectedItem();
        if (contractor == null) {
            WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Ничего не выбрано", mainStage);
            return;
        }

        final Stage contractorAddEditStage = DialogueWindows.getStage("contractorAddEditStage");
        contractorAddEditStage.setTitle("Изменить");
        DialogueWindows.getController("contractorAddEditStage").setValueForEditing(contractor);
        contractorAddEditStage.showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {
        final Contractor selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        Main.getManagementController().removeContractor(selectedItem);
        observableList.removeIf(contractor -> contractor.getId() == selectedItem.getId());
    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {
        observableList.clear();
        final List<Contractor> allContractors = Main.getManagementController().findAllContractors();
        observableList.addAll(allContractors);
    }

    @Override
    public void init(Stage mainStage, TableView<Contractor> tableView) throws Exception {

        this.mainStage = mainStage;
        this.tableView = tableView;
        this.observableList = tableView.getItems();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });

        if (DialogueWindows.getStage("contractorAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/contractorAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("contractorAddEditStage", mainStage, fxmlLoader, observableList);
        }
    }
}
