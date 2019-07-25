package com.bht.pim.daos.impl;

import com.bht.pim.daos.ProjectEmployeeDao;
import com.bht.pim.entities.ProjectEmployeeEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ProjectEmployeeDaoImpl implements ProjectEmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(ProjectEmployeeDaoImpl.class);

    private void addProjectEmployee(ProjectEmployeeEntity projectEmployeeEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(projectEmployeeEntity);

        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Override
    public boolean addProjectEmployees(List<ProjectEmployeeEntity> projectEmployeeEntityList) {
        try {
            projectEmployeeEntityList
                    .forEach(this::addProjectEmployee);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    private void deleteProjectEmployee(ProjectEmployeeEntity projectEmployeeEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .delete(projectEmployeeEntity);

        } catch (Exception exception) {

            logger.info(exception);
        }
    }

    @Override
    public boolean deleteProjectEmployees(List<ProjectEmployeeEntity> projectEmployeeEntityList) {
        try {
            projectEmployeeEntityList
                    .forEach(this::deleteProjectEmployee);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
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
