package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Contractor;

/**
 * Created by Alex Korneyko on 30.08.2016 20:30.
 */
public class ContractorAddEditDialogueWindowController implements AddEditController<Contractor> {

    public TextField textFieldContractorName;
    private ObservableList<Contractor> observableList;
    private Stage controledStage;
    private Contractor contractor;

    @Override
    public void saveAction(ActionEvent actionEvent) {
        if (textFieldContractorName.getText().isEmpty()) return;

        if(contractor != null) {
            this.contractor.setContractorName(textFieldContractorName.getText());
            Main.getManagementController().editContractor(contractor.getId(), contractor);
        } else {
            if (observableList != null) {
                Main.getManagementController().addContractor(textFieldContractorName.getText());
                this.contractor = Main.getManagementController().findContractor(textFieldContractorName.getText());
                observableList.add(this.contractor);
            }
        }
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<Contractor> observableList, Stage thisStage) throws Exception {
        this.observableList = observableList;
        this.controledStage = thisStage;
    }

    @Override
    public void setValueForEditing(Contractor valueForEditing) {
        this.contractor = valueForEditing;
        textFieldContractorName.setText(valueForEditing == null ? "" : valueForEditing.getContractorName());
    }

    @Override
    public Contractor getNewValue() {
        return null;
    }
}
