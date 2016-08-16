package ua.in.dris4ecoder.model.dao.jdbc;

import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.KitchenProcess;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.08.2016 10:57.
 */
public class JdbcKitchenProcessDao implements RestaurantDao<KitchenProcess> {

    private DataSource dataSource;

    @Override
    public void addItem(KitchenProcess item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void editItem(int id, KitchenProcess changedItem) {

    }

    @Override
    public KitchenProcess findItemById(int id) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(String name) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(KitchenProcess employee) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<KitchenProcess> findItem(LocalDate startPeriod, LocalDate endPeriod) {
        return null;
    }

    @Override
    public List<KitchenProcess> findAll() {
        return null;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
