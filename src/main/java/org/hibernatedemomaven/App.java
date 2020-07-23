package org.hibernatedemomaven;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernatedemomaven.models.Employees;

import java.util.List;

public class App {

    public static Employees buildEmployee() {
        Employees employees = new Employees();
        employees.setEmployeeNumber(1000L);
        employees.setEmail("email");
        employees.setExtension("extension");
        employees.setFirstName("firstName");
        employees.setLastName("lastName");
        employees.setJobTitle("PM");
        employees.setOfficeCode("OS5");
        employees.setReportsTo(1000L);
        return employees;
    }
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Employees.class)
                .configure("hibernate.cfg.xml");
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);

        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(Employees.class);
//        Employees employees = buildEmployee();
//        session.save(employees);
        List<Employees> employeesList = crit.list();
        for (Employees employees : employeesList) {
            System.out.println(employees);
        }

        transaction.commit();
        session.getTransaction().commit();
        session.close();
//        System.out.println("End program");
        System.out.println("End program " + employeesList.size());
    }
}
