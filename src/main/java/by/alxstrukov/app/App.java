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

            /*Получение сущности из БД, а также данные относящиеся к ней. Каскадирование в Hibernate*/

            session.beginTransaction();

            Person person = session.find(Person.class, 24);
            System.out.println(person.getPassport());

            Passport passport = session.find(Passport.class, 22);
            System.out.println(passport.getOwner().getPassport());


            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
