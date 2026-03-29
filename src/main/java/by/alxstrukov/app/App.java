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

            /*Сохранение человека в БД, а также данные относящиеся к нему. Каскадирование в Hibernate*/

            Person person = new Person(36, "Alex");


            person.addItem(new Item("Молоко"));
            person.addItem(new Item("Хлеб"));
            person.addItem(new Item("Яйца"));
            /*ВАЖНО! Внутри метода addItem() при помощи сеттера на объекте item происходит добавление (привязка)
            * человека к товару (item.setOwner(this);)
            * Если этого не сделать, то дальше в коде -> session.persist(person) обратится к БД,
            * добавит нового человека в БД, добавит новые товары в БД, но при этом не свяжет эти товары с этим человеком.
            * Связано это с тем, что при сохранении человека метод persist() не возвращает (т.к. не получает)
            *  сгенерированный базой данных первичный ключ и поэтому он не может связать новые товары с человеком.
            * Он (метод persist()) генерирует следующий HQl-запрос в БД ->
            * insert into items (item_name,person_id) values (?,?);
            * Так как ему не известен id человека из таблицы людей (people),
            *  то в атрибут person_id он не может ничего подставить,
            *  в связи с чем связывание товара с человеком не происходит.*/

            session.persist(person);//добавит в БД нового человека


            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
