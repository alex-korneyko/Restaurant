package ua.in.dris4ecoder.view.customControls;

import javafx.css.Styleable;
import javafx.event.EventTarget;
import javafx.stage.Stage;

/**
 * Created by Alex Korneyko on 25.08.2016 13:30.
 */
public interface CustomTab extends EventTarget, Styleable {
    void init(Stage mainStage) throws Exception;
}
