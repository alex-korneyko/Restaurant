package ua.in.dris4ecoder.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.model.businessObjects.Currency;
import ua.in.dris4ecoder.model.businessObjects.OrderDishStatus;
import ua.in.dris4ecoder.model.dao.CurrencyDao;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.11.2016 21:31.
 */
public class HibernateCurrenciesDao implements RestaurantDao<Currency>, CurrencyDao {

    SessionFactory sessionFactory;

    @Override
    public void addItem(Currency item) {

    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void removeItemByName(String name) {

    }

    @Override
    public void removeItem(Currency item) {

    }

    @Override
    public void editItem(int id, Currency changedItem) {

    }

    @Override
    public Currency findItemById(int id) {
        return null;
    }

    @Override
    public Currency findItem(String name) {
        return null;
    }

    @Override
    public Currency findItem(Currency item) {
        return null;
    }

    @Override
    public List<Currency> findItem(OrderDishStatus status) {
        return null;
    }

    @Override
    public List<Currency> findItem(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return null;
    }

    @Override
    public List<Currency> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Currency getMainCurrency() {

        Session currentSession = sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection")
        Query<Currency> query = currentSession.createQuery("select c from Currency c where c.isMain = :main");
        query.setParameter("main", true);
        return query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
