package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.ServiceController;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.gui.windowsSet.MainWindow;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main{

    private static StaffController staffController;
    private ServiceController serviceController;
    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) {

        mainWindow.runMainWindow(args);

//        staffController.getAllEmployeePosts().forEach(System.out::println);
//        staffController.getAllEmployees().forEach(System.out::println);

    }

    public static StaffController getStaffController() {
        return staffController;
    }

    public void setStaffController(StaffController staffController) {
        Main.staffController = staffController;
    }

    public void setServiceController(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
