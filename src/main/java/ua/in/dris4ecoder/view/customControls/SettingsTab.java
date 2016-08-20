package ua.in.dris4ecoder.view.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.SettingsTabController;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 20.08.2016 14:03.
 */
public class SettingsTab extends Tab {

    public SettingsTab(String text, String id) {
        super(text);
        setId(id);
    }

    public void init(Stage mainStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settingsTab.fxml"));
        SettingsTabController settingsTabController = new SettingsTabController();
        fxmlLoader.setController(settingsTabController);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        settingsTabController.init();
    }
}
