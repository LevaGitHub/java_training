package ru.stqa.addressbok.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.addressbok.model.GroupData;
import ru.stqa.addressbok.model.Groups;
import ru.stqa.addressbok.model.PersonData;
import ru.stqa.addressbok.model.Persons;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Persons persons() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<PersonData> result = session.createQuery( "from PersonData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Persons(result);
    }
}
