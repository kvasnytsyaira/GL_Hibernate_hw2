package model;

import DAO.DepartmentDAOImpl;
import DAO.WorkerDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl(sessionFactory);
        WorkerDAOImpl workerDAOImpl = new WorkerDAOImpl(sessionFactory);

//PLEASE RUN Company database script BEFORE RUNNING public static void main

/////////////////////////////////////////////////

        List workersByDepartmentAndAvailability = workerDAOImpl
                .getListWorkersByDepartmentAndAvailability(8, Availability.FULLTIME);
        for (int k = 0; k < workersByDepartmentAndAvailability.size(); k++) {
            Object workerByDepIdAvailability = workersByDepartmentAndAvailability.get(k);
            System.out.println(workerByDepIdAvailability.toString());
        }

        List CriteriaWorkersByDepartmentAndAvailability =
                workerDAOImpl.getListWorkersByDepartmentAndAvailabilityByCriteria(8, Availability.FULLTIME);
        for (int i = 0; i < CriteriaWorkersByDepartmentAndAvailability.size(); i++) {
            Object criteriaWorkerByDepIdAvailability = CriteriaWorkersByDepartmentAndAvailability.get(i);
            System.out.println(criteriaWorkerByDepIdAvailability.toString());
        }


        List AllActiveDepartments = departmentDAOImpl.readAllActiveDepartments();
        for (int j = 0; j < AllActiveDepartments.size(); j++) {
            Department department = (Department) AllActiveDepartments.get(j);
            System.out.println(department.toString());
        }

        List criteriaAllActiveDepartments = departmentDAOImpl.readAllActiveDepartmentsByCriteria();
        for (int r = 0; r <criteriaAllActiveDepartments.size() ; r++) {
            Object criteriaActiveDep = criteriaAllActiveDepartments.get(r);
            System.out.println(criteriaActiveDep.toString());
        }

/////////////////////////////////////////////////

//        System.out.println("CREATION");
//
//        workerDAOImpl.create(new Worker().builder().full_name("Holly Berry").age(31).availability(Worker.Availability.FULLTIME)
//                .departmentId(departmentDAOImpl.read(1)).build());
//        workerDAOImpl.create(new Worker().builder().full_name("Berry Holly").age(42).availability(Worker.Availability.PARTTIME)
//                .departmentId(departmentDAOImpl.read(4)).build());
//
//        departmentDAOImpl.create(new Department().builder().name("IT").status(false).build());

/////////////////////////////////////////////////

//        System.out.println("READING");
//
//        System.out.println(departmentDAOImpl.read(1).toString());
//
//        System.out.println(workerDAOImpl.read(1).toString());

/////////////////////////////////////////////////

//        System.out.println("READING ALL");
//        List listWorkers = workerDAOImpl.readAll();
//        for (int i = 0; i < listWorkers.size(); i++) {
//            Worker worker = (Worker) listWorkers.get(i);
//            System.out.println(worker.toString());
//        }
//
//        List listDepartment = departmentDAOImpl.readAll();
//        for (int i = 0; i < listDepartment.size(); i++) {
//            Department department = (Department) listDepartment.get(i);
//            System.out.println(department.toString());
//        }

/////////////////////////////////////////////////

//        System.out.println("DELETION");
//
//        workerDAOImpl.delete(2);
//
//        departmentDAOImpl.delete(8);

/////////////////////////////////////////////////

//        System.out.println("DELETION ALL");
//
//        workerDAOImpl.deleteAll();
//
//        departmentDAOImpl.deleteAll();

/////////////////////////////////////////////////

        session.close();
        sessionFactory.close();
    }
}




