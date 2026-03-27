package by.alxstrukov.app;

import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            //Hibernate работает не с таблицами,а с java-классами (сущности @Entity)
            int minAge = 50;
            String hqlQuery = "from Person where age < " + minAge;//hql - вывести всех людей у которых возраст > 50
            List<Person> personList = session.createQuery(hqlQuery, Person.class).getResultList();
            personList.forEach(System.out::println);

            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
