package by.alxstrukov.app;

import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Person person1 = new Person(27, "Veronika");
            Person person2 = new Person(42, "Klim");
            Person person3 = new Person(41, "Pavel");

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
