package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("FROM User").getResultList();
   }

   public List<User> getUserByCarModelAndSeries(String model, int series) {
      return sessionFactory.openSession().createQuery("FROM User " +
              "WHERE car.model= :model " +
              "and car.series= :series")
              .setParameter("model", model)
              .setParameter("series", series).getResultList();
   }
}
