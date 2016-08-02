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
//    private RestaurantDao restaurantDao;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.start();
    }

    private void start() {


    }

    public void setStaffController(StaffController staffController) {
        this.staffController = staffController;
    }

//    public void setRestaurantDao(RestaurantDao restaurantDao) {
//        this.restaurantDao = restaurantDao;
//    }
}
