package com.bht.pim.dao.impl;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(ProjectDaoImpl.class);

    @Override
    public long nextIdValue() {
        try {
            String sql = "SELECT IDENT_CURRENT('PROJECT') + 1 AS ID";
            List results = sessionFactory.getCurrentSession()
                    .createSQLQuery(sql).list();

            return ((BigDecimal) results.get(0)).longValue();

        } catch (Exception exception) {

            logger.info(exception);
            return -1;
        }
    }

    @Override
    public List<Long> getAllProjectsNumber() {
        try {
            String sql = "SELECT PROJECT_NUMBER AS NUMBER FROM PROJECT";

            List<Long> results = new ArrayList<>();

            sessionFactory.getCurrentSession().createSQLQuery(sql)
                    .list()
                    .forEach(value -> results
                            .add(Long.valueOf((Integer) value)));

            return results;

        } catch (Exception exception) {

            logger.info(exception);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean addProject(ProjectEntity projectEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(projectEntity);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean updateProject(ProjectEntity projectEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .merge(projectEntity);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean deleteProject(long id) {
        try {
            sessionFactory.getCurrentSession()
                    .delete(getProjectById(id));

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public ProjectEntity getProjectById(long id) {
        try {
            ProjectEntity projectEntity = sessionFactory
                    .getCurrentSession()
                    .get(ProjectEntity.class, id);

            // As Hibernate is lazy-initialization !
            Hibernate.initialize(projectEntity.getEnrolledEmployees());

            return projectEntity;

        } catch (Exception exception) {

            logger.info(exception);
            return null;
        }
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> query = builder.createQuery(ProjectEntity.class);

            Root<ProjectEntity> root = query.from(ProjectEntity.class);
            TypedQuery<ProjectEntity> allQuery = sessionFactory.getCurrentSession()
                    .createQuery(query.select(root));

            return allQuery.getResultList();

        } catch (Exception exception) {

            logger.info(exception);
            return Collections.emptyList();
        }
    }
}
