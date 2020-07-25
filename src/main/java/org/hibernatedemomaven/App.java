package org.hibernatedemomaven;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernatedemomaven.models.Employee;
import org.hibernatedemomaven.models.Name;

import java.util.List;

public class App {
    private static void printAllEmployees(Session session) {
        System.out.println("Print all employees");
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        transaction.commit();
    }

    private static void createEmployee(Session session) {
        System.out.println("Create employee");
        Employee employee = new Employee();
        employee.setEmployeeNumber(1000L);
        employee.setEmail("jane.kurhs@gmail.com");
        employee.setExtension("extension");
        Name fullName = new Name();
        fullName.setFirstName("Pham");
        fullName.setLastName("Tuaan");
        employee.setJobTitle("PM");
        employee.setOfficeCode("1");
        employee.setReportsTo(1002L);
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
    }

    private static void getEmployee(Session session, Long id) {
        System.out.println("Get employee with id = " + id);
        Employee employee = (Employee) session.get(Employee.class, id);
        System.out.println(employee);
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
        App.createEmployee(session);
        App.getEmployee(session, 1000L);
        session.close();
        System.out.println("End program");
    }
}
