package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.Employee;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main {

    private StaffController staffController;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.start();
    }

    private void start() {

        staffController.getAllEmployees().forEach(System.out::println);

    }

    public void setStaffController(StaffController staffController) {
        this.staffController = staffController;
    }
}
