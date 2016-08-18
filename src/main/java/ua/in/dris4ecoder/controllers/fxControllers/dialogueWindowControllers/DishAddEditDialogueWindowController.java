package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import ua.in.dris4ecoder.model.businessObjects.Dish;

/**
 * Created by Alex Korneyko on 18.08.2016 13:12.
 */
public class DishAddEditDialogueWindowController {

    private ObservableList<Dish> observableList;

    public void setObservableList(ObservableList<Dish> observableList) {
        this.observableList = observableList;
    }
}
