package ua.in.dris4ecoder.springConfigClasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;
import ua.in.dris4ecoder.view.windowsSet.SimpleMainWindow;

/**
 * Created by Alex Korneyko on 05.08.2016 10:36.
 */
@Configuration
public class FXSpringConfig {

    @Bean
    MainWindow mainWindow() {
        return new SimpleMainWindow();
    }
}
