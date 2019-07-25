package com.bht.pim.daos.impl;

import com.bht.pim.daos.ProjectEmployeeDao;
import com.bht.pim.entities.ProjectEmployeeEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProjectEmployeeDaoImpl implements ProjectEmployeeDao {

    @Autowired
    SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(ProjectEmployeeDaoImpl.class);

    @Override
    public boolean addProjectEmployee(ProjectEmployeeEntity projectEmployeeEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(projectEmployeeEntity);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean addProjectEmployeeList(List<ProjectEmployeeEntity> projectEmployeeEntityList) {
        try {
            projectEmployeeEntityList
                    .forEach(this::addProjectEmployee);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean deleteProjectEmployee(int id) {
        try {
            sessionFactory.getCurrentSession()
                    .delete(getProjectEmployeeById(id));

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public ProjectEmployeeEntity getProjectEmployeeById(int id) {
        return sessionFactory.getCurrentSession()
                .get(ProjectEmployeeEntity.class, id);
    }

    @Override
    public List<ProjectEmployeeEntity> getEmployeesByProject(long projectId) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();

        // Create query of ProjectEmployeeEntity
        CriteriaQuery<ProjectEmployeeEntity> projectEmployeeEntityQuery =
                criteriaBuilder.createQuery(ProjectEmployeeEntity.class);

        // FROM-clause
        Root<ProjectEmployeeEntity> root = projectEmployeeEntityQuery
                .from(ProjectEmployeeEntity.class);

        // WHERE-clause
        projectEmployeeEntityQuery
                .where(criteriaBuilder.equal(root.get("projectId"), projectId));

        // SELECT-clause
        TypedQuery<ProjectEmployeeEntity> finalQuery = sessionFactory
                .getCurrentSession()
                .createQuery(projectEmployeeEntityQuery.select(root));

        // GET-results
        return finalQuery.getResultList();
    }
}
