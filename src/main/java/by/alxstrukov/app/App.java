package by.alxstrukov.app;

import by.alxstrukov.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            
            /*ВАЖНО - session и sessionFactory открывать в блоке try(session, sessionFactory){}
            чтобы эти ресурсы потом закрылись*/
            
            session.beginTransaction();

            //Hibernate работает не с таблицами, а с java-классами (сущности @Entity)

            String hqlQuery = "update Person set name=:newName where age >:targetAge";

            /*hqlQuery запрос обновит столбец (поле) name всем строкам в БД в таблице people
            (но помним что Hibernate работает не с БД, а с java-сущностями,
            * то есть, он знает что таблицу people из БД в java представляет класс Person
            * помеченный аннотациями @Entity и @Table (name = "people") */

            int updateCount = session.createQuery(hqlQuery)
                    .setParameter("newName", "Some name")
                    .setParameter("targetAge", 50)
                    .executeUpdate();

            /*При выполнении hql-запросов на обновление/удаление данных, в метод createQuery() не нужно передавать
            * имя класса сущности (например Person.class) как при запросах типа /select * from Person/
            * нужно передать только сам запрос, в котором указать имена параметров (:newName, :newAge)
            *  для условий (where=...) и изменений (set=...)
            * затем после метода createQuery() вызвать нужное кол-во раз метод setParameter()
            * в который в качестве аргументов передать имя нужного параметра и его значение
            * после чего вызвать метод executeUpdate();
            * */

            System.out.println(updateCount);//выведет в консоль кол-во обновленных элементов


            session.getTransaction().commit();//сохраняем транзакцию
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
