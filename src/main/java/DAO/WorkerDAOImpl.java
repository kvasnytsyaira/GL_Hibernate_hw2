package DAO;

import model.Availability;
import model.Worker;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WorkerDAOImpl implements DAO<Worker, Integer> {
    private static final Logger log = Logger.getLogger(WorkerDAOImpl.class);

    private final SessionFactory sessionFactory;

    public WorkerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List getListWorkersByDepartmentAndAvailability(int IdOfDepartment, Availability availability) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//
        Query readAllWorkers = session.createQuery("from Worker where departmentId.id = :id and availability = :ava ");
        readAllWorkers.setParameter("id", IdOfDepartment);
        readAllWorkers.setParameter("ava", availability);
//
        session.getTransaction().commit();
        return readAllWorkers.list();
    }

    public List getListWorkersByDepartmentAndAvailabilityByCriteria(int IdOfDepartment, Availability availability) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = cb.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);
        query.select(root);
        CriteriaQuery<Worker> availabilityy = query
                .where(
                        cb.equal(root.get("availability"), availability),
                        cb.equal(root.get("departmentId"), IdOfDepartment));
        List<Worker> resultList = session.createQuery(availabilityy).getResultList();
//
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public void create(Worker worker) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        session.saveOrUpdate(worker);
//
        session.getTransaction().commit();
    }

    @Override
    public Worker read(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Worker worker = session.get(Worker.class, integer);
//
        session.getTransaction().commit();
        return worker;
    }

    @Override
    public List readAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Query readAll = session.createQuery("from Worker");
        java.util.List listWorkers = readAll.list();
//
        session.getTransaction().commit();
        return listWorkers;
    }

    @Override
    public void updateById(Worker worker) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        session.saveOrUpdate(worker);
//
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Worker worker = session.get(Worker.class, integer);
        session.delete(worker);
//
        session.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Query deleteWorkers = session.createQuery("FROM Worker");
        List workersToDelete = deleteWorkers.list();
        for (Object o : workersToDelete) {
            session.delete(o);
        }
//
        session.getTransaction().commit();
    }


}
