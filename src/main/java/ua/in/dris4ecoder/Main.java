package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.StaffController;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

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

        staffController.getAllEmployeePosts().forEach(System.out::println);

//        System.out.println("Adding new employee post");
//        staffController.addEmployeePost("Завхоз");
//        staffController.getAllEmployeePosts().forEach(System.out::println);
//
//        System.out.println("Removing id 23");
//        staffController.removeEmployeePost(23);
//        staffController.getAllEmployeePosts().forEach(System.out::println);
//
//        System.out.println("Edit item with id 22");
//        staffController.editEmployeePost(22, "Сецуриту");
//        staffController.getAllEmployeePosts().forEach(System.out::println);

        System.out.println("Find Повар");
        System.out.println(staffController.findEmployeePost("Повар"));

        System.out.println("Find bi ID 20");
        System.out.println(staffController.findEmployeePostById(20));

    }

    public void setStaffController(StaffController staffController) {
        this.staffController = staffController;
    }
}
