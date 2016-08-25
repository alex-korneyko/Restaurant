package ua.in.dris4ecoder.view.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * Created by Alex Korneyko on 25.08.2016 13:29.
 */
public class PostTab extends Tab implements CustomTab {

    public PostTab(String text, String id) {
        super(text);
        setId(id);
    }

    @Override
    public void init(Stage mainStage) throws Exception {


    }
}
