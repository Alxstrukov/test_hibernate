package by.alxstrukov.app;

import by.alxstrukov.app.model.Item;
import by.alxstrukov.app.model.Passport;
import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            
            /*ВАЖНО - session и sessionFactory открывать в блоке try(session, sessionFactory){}
            чтобы эти ресурсы потом закрылись*/

            session.beginTransaction();

            /*Сохранение человека в БД, а также данные относящиеся к нему. Каскадирование в Hibernate*/


            Passport passport = new Passport(525397);
            Person person = new Person(16, "Viktor");

            person.setPassport(passport);

            session.persist(person);

            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
