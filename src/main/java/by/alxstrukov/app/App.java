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

            Person person = session.find(Person.class, 8);//получаем человека из БД.
            person.setName("Veroni4ka");// Изменяем у человека имя. Hibernate это поймёт и в БД тоже изменит имя.

            session.getTransaction().commit();//сохраняем транзакцию

            System.out.println(person);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
