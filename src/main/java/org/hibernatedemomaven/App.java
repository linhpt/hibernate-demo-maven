package org.hibernatedemomaven;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernatedemomaven.models.*;


public class App {
    Configuration buildConfiguration() {
        return null;
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Office.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductLine.class)
                .configure("hibernate.cfg.xml");
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
}
