package DAO;

import model.Department;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDepartmentDAOImpl {
    private static final Logger log = Logger.getLogger(TestDepartmentDAOImpl.class);

    @Test
    public void TestReadAllActiveDepartmentsPositive() {
        SessionFactory sessionFactory;
        Session session;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
        session = sessionFactory.openSession();
        session.beginTransaction();

        Department helloDepartment = new Department("hello",true);
        Department holaDepartment = new Department("hola",false);
        session.save(helloDepartment);
        session.save(holaDepartment);

        session.getTransaction().commit();

        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl(sessionFactory);
        List list = departmentDAO.readAllActiveDepartments();

        assertEquals(helloDepartment,list.get(0));

        session.close();
        sessionFactory.close();

    }

    @Test
    public void TestReadAllActiveDepartmentsNegative() {
        Session session;
        SessionFactory sessionFactory;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
        session = sessionFactory.openSession();

        session.beginTransaction();
        Department department = new Department("Dep", false);
        session.save(department);
        session.getTransaction().commit();

        DepartmentDAOImpl departmentDAO= new DepartmentDAOImpl(sessionFactory);
        List list = departmentDAO.readAllActiveDepartments();

        session.close();
        sessionFactory.close();

        assert list.isEmpty();
    }

    @Test
    public void TestReadAllActiveDepartmentsByCriteriaPositive() {
        SessionFactory sessionFactory;
        Session session;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
        session = sessionFactory.openSession();
        session.beginTransaction();

        Department helloDepartment = new Department("hello",true);
        Department holaDepartment = new Department("hola",false);
        session.save(helloDepartment);
        session.save(holaDepartment);

        session.getTransaction().commit();

        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl(sessionFactory);
        List list = departmentDAO.readAllActiveDepartmentsByCriteria();

        assertEquals(helloDepartment,list.get(0));

        session.close();
        sessionFactory.close();
    }


    @Test
    public void TestReadAllActiveDepartmentsByCriteriaNegative() {
        Session session;
        SessionFactory sessionFactory;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
        session = sessionFactory.openSession();

        session.beginTransaction();
        Department department = new Department("Dep", false);
        session.save(department);
        session.getTransaction().commit();

        DepartmentDAOImpl departmentDAO= new DepartmentDAOImpl(sessionFactory);
        List list = departmentDAO.readAllActiveDepartmentsByCriteria();

        session.close();
        sessionFactory.close();
        assert list.isEmpty();
    }


}
