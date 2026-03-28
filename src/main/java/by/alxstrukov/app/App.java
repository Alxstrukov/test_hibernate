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

            /*Добавление товара к человеку*/

//            Person person = new Person(27, "Egor");

            Person personFromDataBase = session.find(Person.class, 3);
            System.out.println(personFromDataBase);
            List<Item> items = personFromDataBase.getItems();
            System.out.println(items);//проверю у человека из БД список товаров ему принадлежащих

            Item item = new Item("Notebook", personFromDataBase);
            session.persist(item);//сохраняю в БД новый товар который принадлежит человеку в БД под id=3

            System.out.println(items);//проверю, изменился ли список товаров у человека
            // *Нет, не изменится* - потому что у Hibernate есть свой кэш и так как он работает с ним,
            //то он достанет оттуда объект personFromDataBase.getItems() в котором нет новых товаров.
            //Чтобы этого не было, нужно обязательно после сохранения в БД нового товара,
            // добавить его и в список товаров для personFromDataBase -> personFromDataBase.getItems().add(item);

            personFromDataBase.getItems().add(item);//в список товаров человека под id=3 добавили новый товар

            System.out.println(items);


            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
