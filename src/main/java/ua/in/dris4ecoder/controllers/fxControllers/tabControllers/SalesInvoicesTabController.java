package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.SalesInvoice;
import ua.in.dris4ecoder.view.customControls.WarningsDialogWindow;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 31.08.2016 10:46.
 */
public class SalesInvoicesTabController implements TabController<SalesInvoice> {

    private TableView<SalesInvoice> tableView;
    private ObservableList<SalesInvoice> observableList;
    private Stage mainStage;

    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {

        DialogueWindows.getStage("salesInvoiceAddEditDialogueWindow").setTitle("Новая расходная накладная");
        DialogueWindows.getController("salesInvoiceAddEditDialogueWindow").setValueForEditing(null);
        DialogueWindows.getStage("salesInvoiceAddEditDialogueWindow").showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("salesInvoiceAddEditDialogueWindow").setTitle("Изменить расходную накладную");
        SalesInvoice selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.ERROR, "Ничего не выбрано", mainStage);
            return;
        }
        DialogueWindows.getController("salesInvoiceAddEditDialogueWindow").setValueForEditing(selectedItem);
        DialogueWindows.getStage("salesInvoiceAddEditDialogueWindow").showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {

        if (!WarningsDialogWindow.showWindow(WarningsDialogWindow.WindowType.CONFIRM, "Удалить расходню накладную?", mainStage))
            return;

        final SalesInvoice selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getManagementController().removeSalesInvoice(selectedItem, true);
        getAllAction(actionEvent);
    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<SalesInvoice> salesInvoices = Main.getManagementController().findAllSalesInvoices();
        observableList.addAll(salesInvoices);

    }

    @Override
    public void init(Stage mainStage, TableView<SalesInvoice> tableView) throws Exception {

        this.mainStage = mainStage;
        this.tableView = tableView;
        this.observableList = tableView.getItems();

        if (DialogueWindows.getStage("salesInvoiceAddEditDialogueWindow") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/salesInvoiceAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("salesInvoiceAddEditDialogueWindow", mainStage, fxmlLoader, observableList);
        }

        this.tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
