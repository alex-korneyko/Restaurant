package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by Alex Korneyko on 10.09.2016 14:04.
 */
public class SimpleDialogueWindowController {

    public Label labelMessage;
    public ImageView imageIcon;
    private boolean result;
    private Stage controlledStage;

    public void init(Stage controlledStage) {
        this.controlledStage = controlledStage;
    }

    public void setParameters(String url, String message) {
        labelMessage.setText(message);
        imageIcon.setImage(new Image(url));
    }

    public boolean getResult() {
        return result;
    }

    public void okAction(ActionEvent actionEvent) {
        result = true;
        controlledStage.hide();
    }

    public void cancelAction(ActionEvent actionEvent) {
        result = false;
        controlledStage.hide();
    }
}
