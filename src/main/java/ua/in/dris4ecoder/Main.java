package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.ServiceController;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.gui.windowsSet.MainWindow;
import ua.in.dris4ecoder.gui.windowsSet.SimpleMainWindow;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main{

    private StaffController staffController;
    private ServiceController serviceController;
    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) {

        mainWindow.runMainWindow(args);

        staffController.getAllEmployees().forEach(System.out::println);
        staffController.getAllEmployeePosts().forEach(System.out::println);

    }


    public void setStaffController(StaffController staffController) {
        this.staffController = staffController;
    }

    public void setServiceController(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
