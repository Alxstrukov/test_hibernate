package by.alxstrukov.app;

import by.alxstrukov.app.model.Item;
import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            
            /*ВАЖНО - session и sessionFactory открывать в блоке try(session, sessionFactory){}
            чтобы эти ресурсы потом закрылись*/

            session.beginTransaction();

            /*Удаление товаров относящихся к человеку*/

            Person person = session.find(Person.class, 3);
            List<Item> items = person.getItems();

            //SQL
            items.forEach(session::remove);//в цикле удаляем поочередно все товары у человека (HQL)

            //Не порождает SQl, но нужно для того, чтобы обновилось в кэше Hibernate
            items.clear();//очищаем список товаров у человека (Java)



            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
