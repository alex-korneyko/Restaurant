package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 16.08.2016 19:47.
 */
public class IngredientAddEditDialogueWindowController implements AddEditController<Ingredient> {

    public ComboBox<String> comboBoxUnits;
    private ObservableList<Ingredient> observableList;
    private Ingredient selectedIngredient;
    private Stage mainStage;

    @FXML
    public TextField textFieldIngredientName;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if (selectedIngredient == null) {
            if (!textFieldIngredientName.getText().isEmpty()) {
                Ingredient ingredient = new Ingredient(textFieldIngredientName.getText());
                ingredient.setUnit(Main.getInstrumentsController().findUnit(comboBoxUnits.getValue()));
                Main.getInstrumentsController().addIngredient(ingredient);
                observableList.add(Main.getInstrumentsController().findIngredient(ingredient.getIngredientName()));
            }
        } else {
            Main.getInstrumentsController().editIngredient(selectedIngredient.getId(), new Ingredient(textFieldIngredientName.getText()));
            observableList.clear();
            observableList.addAll(Main.getInstrumentsController().findAllIngredients());
        }

        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        selectedIngredient = null;
        textFieldIngredientName.setText("");
        this.comboBoxUnits.setValue(comboBoxUnits.getItems().get(0));
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {
        selectedIngredient = valueForEditing;
        textFieldIngredientName.setText(valueForEditing.getIngredientName());
        comboBoxUnits.setValue(valueForEditing.getUnit().getUnitName());
    }

    @Override
    public Ingredient getNewValue() {
        return this.selectedIngredient;
    }

    @Override
    public void init(ObservableList<Ingredient> observableList, Stage thisStage) {
        this.observableList = observableList;
        this.comboBoxUnits.setItems(new ObservableListWrapper(Main.getInstrumentsController().findAllUnits().stream().map(unit -> unit.getUnitName()).collect(Collectors.toList())));
        this.comboBoxUnits.setValue(comboBoxUnits.getItems().get(0));
    }
}
