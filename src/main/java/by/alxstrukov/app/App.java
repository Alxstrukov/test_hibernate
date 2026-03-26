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
            session.remove(person);// Hibernate удалит из БД этого человека

            session.getTransaction().commit();//сохраняем транзакцию

            System.out.println(person);

            /*System.out.println(person);
            в БД человека уже нет,
            но в Java (область памяти куча (heap)) он остался и будет выведен в консоль */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
