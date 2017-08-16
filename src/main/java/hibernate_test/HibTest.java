package hibernate_test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;


/**
 * Created by traitorwtf on 10.05.2017.
 */
public class HibTest {
    File file;
    SessionFactory factory;
    Session session;

    public static void main(String[] args) {
        HibTest hibTest = new HibTest();
        hibTest.file = new File("hibernate.cfg.xml");
        hibTest.factory = new Configuration().configure(hibTest.file).addAnnotatedClass(User.class).buildSessionFactory();

        hibTest.session = hibTest.factory.getCurrentSession();


//        hibTest.createNewUser();
//        hibTest.retreiveById(18);
//         //использовать from + не название таблицы, а класс в Java!!
        hibTest.retreiveByQuery("from User where city='Moscow'");
//        hibTest.retreiveByQuery("from User");
//        hibTest.updateById(2);
//        hibTest.deleteById(19);
    }

    void createNewUser(){
        try{
            User user = new User("Lesya", "Sbor", 16, "Glazov");

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

    void retreiveById(int id){
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        System.out.println(user.toString());
        factory.close();
    }

    void retreiveByQuery(String query){
        session.beginTransaction();
        List<User> selectedUsers = session.createQuery(query).list();
        session.getTransaction().commit();
        for (User us : selectedUsers){
            System.out.println(us.toString());
        }
        factory.close();
    }

    void updateById(int id){
        session.beginTransaction();
        User user = session.get(User.class, id);
        user.setAge(27);
        session.getTransaction().commit();
        System.out.println(user.toString());
        factory.close();
    }

    void deleteById(int id){
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        factory.close();
    }
}
