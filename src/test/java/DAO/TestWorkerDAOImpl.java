package DAO;

import model.Department;
import model.Worker;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import java.util.List;
import static model.Availability.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWorkerDAOImpl {
    private static final Logger log = Logger.getLogger(TestWorkerDAOImpl.class);

    @Test
    public void TestGetListWorkersByDepartmentAndAvailabilityPositive() {

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

        Department department = new Department("DepForWorker",true);
        Department department2 = new Department("DepForWorker2",true);
        Worker worker0 = new Worker(22, PARTTIME, "Kvasnytsya Iryna", department);
        Worker worker1 = new Worker(22, FULLTIME, "Kvasnytsya Ira", department);
        Worker worker2 = new Worker(22, PARTTIME, "Kvasnytsya Iran", department);
        Worker worker3 = new Worker(22, FULLTIME, "Kvasnytsya Irina", department2);
        session.save(department);
        session.save(department2);
        session.save(worker0);
        session.save(worker1);
        session.save(worker2);
        session.save(worker3);
        session.getTransaction().commit();

        WorkerDAOImpl workerDAO = new WorkerDAOImpl(sessionFactory);
        List workersByDepartmentAndAvailability = workerDAO.getListWorkersByDepartmentAndAvailability(1, PARTTIME);

        assertEquals(worker0,workersByDepartmentAndAvailability.get(0));
        assertEquals(worker2,workersByDepartmentAndAvailability.get(1));

        session.close();
        sessionFactory.close();
    }

    @Test
    public void TestGetListWorkersByDepartmentAndAvailabilityNegative() {
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

        Department department = new Department("DepForWorker",false);
        Worker worker = new Worker(22, FULLTIME, "Kvasnytsya Ira",null);
        session.save(department);
        session.save(worker);

        session.getTransaction().commit();
        WorkerDAOImpl workerDAO= new WorkerDAOImpl(sessionFactory);
        List list = workerDAO.getListWorkersByDepartmentAndAvailability(department.getIdDepartment(), FULLTIME);

        assert list.isEmpty();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void TestGetListWorkersByDepartmentAndAvailabilityByCriteriaPositive() {

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
        Department department = new Department("DepForWorker",true);
        Department department2 = new Department("DepForWorker2",true);
        Worker worker0 = new Worker(22, PARTTIME, "Kvasnytsya Iryna", department);
        Worker worker1 = new Worker(22, FULLTIME, "Kvasnytsya Ira", department);
        Worker worker2 = new Worker(22, PARTTIME, "Kvasnytsya Iran", department);
        Worker worker3 = new Worker(22, PARTTIME, "Kvasnytsya Irina", department2);
        session.save(department);
        session.save(department2);
        session.save(worker0);
        session.save(worker1);
        session.save(worker2);
        session.save(worker3);
        session.getTransaction().commit();

        WorkerDAOImpl workerDAO = new WorkerDAOImpl(sessionFactory);
        List workersByDepartmentAndAvailabilityByCriteria = workerDAO.getListWorkersByDepartmentAndAvailabilityByCriteria(1, PARTTIME);

        assertEquals(worker0,workersByDepartmentAndAvailabilityByCriteria.get(0));
        assertEquals(worker2,workersByDepartmentAndAvailabilityByCriteria.get(1));

        session.close();
        sessionFactory.close();
    }

    @Test
    public void TestGetListWorkersByDepartmentAndAvailabilityByCriteriaNegative() {
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

        Department department = new Department("DepForWorker",false);
        Worker worker = new Worker(22, FULLTIME, "Kvasnytsya Ira",null);
        session.save(department);
        session.save(worker);

        session.getTransaction().commit();
        WorkerDAOImpl workerDAO= new WorkerDAOImpl(sessionFactory);
        List list = workerDAO.getListWorkersByDepartmentAndAvailabilityByCriteria(department.getIdDepartment(), FULLTIME);

        assert list.isEmpty();
        session.close();
        sessionFactory.close();
    }


}
