package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Contractor;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 03.09.2016 20:08.
 */
public class ContractorListDialogueWindowController implements AddEditController<Contractor> {

    public TableView<Contractor> tableViewAllContractorsList;
    public TextField textFieldFilter;
    private Stage controlledStage;
    private ObservableList<Contractor> contractorsFullList = FXCollections.observableArrayList();
    private Contractor contractor;
    private ObservableList<Contractor> observableList;

    public void createNewContractorAction() {

        final Stage ingredientAddEditStage = DialogueWindows.getStage("contractorAddEditStage");
        ingredientAddEditStage.setTitle("Новый контрагент");
        ingredientAddEditStage.showAndWait();
        contractorsFullList.clear();
        contractorsFullList.addAll(Main.getManagementController().findAllContractors());
    }

    @Override
    public void saveAction(ActionEvent actionEvent) {

        contractor = tableViewAllContractorsList.getSelectionModel().getSelectedItem();
        observableList.clear();
        observableList.add(contractor);
        controlledStage.hide();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        contractor = null;
        controlledStage.hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<Contractor> observableList, Stage thisStage) throws Exception {
        this.observableList = observableList;
        this.controlledStage = thisStage;

        ServiceClass.setColumns(tableViewAllContractorsList, "id", "idProp");
        ServiceClass.setColumns(tableViewAllContractorsList, "Имя", "contractorNameProp");
        tableViewAllContractorsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        contractorsFullList.addAll(Main.getManagementController().findAllContractors());
        tableViewAllContractorsList.setItems(contractorsFullList);

        if(DialogueWindows.getStage("contractorAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/contractorAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("contractorAddEditStage", controlledStage, fxmlLoader, contractorsFullList);
        }

        tableViewAllContractorsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                saveAction(new ActionEvent());
            }
        });
    }

    @Override
    public void setValueForEditing(Contractor valueForEditing) {

    }

    @Override
    public Contractor getNewValue() {
        return contractor;
    }

    public void keyReleasedAction() {

        CharSequence mask = textFieldFilter.getText();
        List<Contractor> filteredContractors =
                Main.getManagementController().findAllContractors().stream().filter(ingredient -> ingredient.getContractorName().contains(mask)).collect(Collectors.toList());
        contractorsFullList.clear();
        contractorsFullList.addAll(filteredContractors);
    }
}
