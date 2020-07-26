package org.hibernatedemomaven;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernatedemomaven.models.Address;
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

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Employee.class)
                .configure("hibernate.cfg.xml");
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();
        Employee employee1 = buildEmployee("Pham", "Tuan");
        Employee employee2 = buildEmployee("Pham2", "Tuan2");
        Transaction transaction = session.beginTransaction();
        session.save(employee1);
        session.save(employee2);
        transaction.commit();
        session.close();
        System.out.println("End program");
    }

    private static Employee buildEmployee(String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setEmail("linhpt.uet@gmail.com");
        employee.setExtension("extension");
        Name fullName1 = new Name();
        fullName1.setLastName(lastName);
        fullName1.setFirstName(firstName);
        employee.setFullName(fullName1);
        employee.setJobTitle("PM");
        employee.setOfficeCode("1");
        Address address = new Address();
        address.setCity("New york");
        address.setState("Ohio");
        address.setStreet("122 st.Luis");
        employee.setAddress(address);
        Address officeAddress = new Address();
        officeAddress.setCity("Atalanta");
        officeAddress.setState("silicon");
        officeAddress.setStreet("122 st.Luis");
        employee.setOfficeAddress(officeAddress);
        return employee;
    }
}
