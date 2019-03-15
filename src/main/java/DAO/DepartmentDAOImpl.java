package DAO;

import model.Department;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDAOImpl implements DAO<Department, Integer> {
    private static final Logger log = Logger.getLogger(DepartmentDAOImpl.class);

    private final SessionFactory sessionFactory;

    public DepartmentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List readAllActiveDepartments() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Query readAllActiveDepartments = session.createQuery("from Department where status = true ");
        java.util.List listActiveDepartments = readAllActiveDepartments.list();
//
        session.getTransaction().commit();
        return listActiveDepartments;
    }

    public List readAllActiveDepartmentsByCriteria() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root);
        CriteriaQuery<Department> status = query.where(cb.equal(root.get("status"), true));
        List<Department> resultList = session.createQuery(status).getResultList();
//
        session.getTransaction().commit();
        return resultList;
    }

    @Override
    public void create(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        session.saveOrUpdate(department);
//
        session.getTransaction().commit();
    }

    @Override
    public Department read(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Department department = session.get(Department.class, integer);
//
        session.getTransaction().commit();
        return department;
    }

    @Override
    public List readAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Query readAllDepartments = session.createQuery("from Department");
        java.util.List listDepartments = readAllDepartments.list();
//
        session.getTransaction().commit();
        return listDepartments;
    }

    @Override
    public void updateById(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        session.saveOrUpdate(department);
//
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Department department = session.get(Department.class, integer);
        Query findWorkersToDelete = session.createQuery("from Worker where departmentId= " + integer);
        java.util.List workersToDelete = findWorkersToDelete.list();
        for (Object o : workersToDelete) {
            session.delete(o);
        }
        session.delete(department);
//
        session.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
        Query findWorkersToDelete = session.createQuery("from Worker");
        java.util.List workersToDelete = findWorkersToDelete.list();
        for (Object o : workersToDelete) {
            session.delete(o);
        }
        Query deleteDepartments = session.createQuery("from Department ");
        List departmentsToDelete = deleteDepartments.list();
        for (Object o : departmentsToDelete) {
            session.delete(o);
        }
//
        session.getTransaction().commit();
    }


}
