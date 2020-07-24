package org.hibernatedemomaven;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernatedemomaven.models.Employee;

import java.util.List;

public class App {

    public static Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeNumber(1000L);
        employee.setEmail("email");
        employee.setExtension("extension");
        employee.setFirstName("firstName");
        employee.setLastName("lastName");
        employee.setJobTitle("PM");
        employee.setOfficeCode("OS5");
        employee.setReportsTo(1000L);
        return employee;
    }

    private static void printAllEmployees(Session session) {
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        transaction.commit();
        session.close();
    }
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Employee.class)
                .configure("hibernate.cfg.xml");
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();
        App.printAllEmployees(session);
        System.out.println("End program");
    }
}
