package com.bht.pim.dao.impl;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import lombok.extern.log4j.Log4j;
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

@Log4j
@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long nextIdValue() {
        try {
            String sql = "SELECT IDENT_CURRENT('PROJECT') + 1 AS ID";
            List results = sessionFactory.getCurrentSession()
                    .createSQLQuery(sql).list();

            return ((BigDecimal) results.get(0)).longValue();

        } catch (Exception exception) {

            log.info(exception);
            return -1;
        }
    }

    @Override
    public List<Long> getAllProjectsNumber() {
        try {
            String sql = "SELECT PROJECT_NUMBER AS NUMBER FROM PROJECT";

            List<Long> results = new ArrayList<>();

            for (Object obj : sessionFactory.getCurrentSession()
                    .createSQLQuery(sql).list()) {

                results.add(Long.valueOf((Integer) obj));
            }

            return results;

        } catch (Exception exception) {

            log.info(exception);
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

            log.info(exception);
            return false;
        }
    }

    @Override
    public boolean updateProject(ProjectEntity projectEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .merge(projectEntity);
            // To avoid the NonUniqueObjectException,
            // use the merge method offered by the JPA

            return true;

        } catch (Exception exception) {

            log.info(exception);
            return false;
        }
    }

    @Override
    public boolean deleteProject(long id) {
        try {
            ProjectEntity projectEntity = getProjectById(id);

            if (projectEntity.getStatus().equals("NEW")) {
                sessionFactory.getCurrentSession()
                        .delete(projectEntity);

                return true;
            }

        } catch (Exception exception) {

            log.info(exception);
        }

        log.info("CANNOT delete this project !");
        return false;
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

            log.info(exception);
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

            log.info(exception);
            return Collections.emptyList();
        }
    }
}
