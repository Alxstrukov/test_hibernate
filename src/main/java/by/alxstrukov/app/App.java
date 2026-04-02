package by.alxstrukov.app;

import by.alxstrukov.app.model.Actor;
import by.alxstrukov.app.model.Movie;
import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            
            /*ВАЖНО - session и sessionFactory открывать в блоке try(session, sessionFactory){}
            чтобы эти ресурсы потом закрылись*/

            session.beginTransaction();

            //здесь будет код

            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
