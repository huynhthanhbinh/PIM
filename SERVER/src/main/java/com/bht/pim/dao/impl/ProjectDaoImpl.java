package com.bht.pim.dao.impl;

import com.bht.pim.dao.ProjectDao;
import com.bht.pim.entity.ProjectEntity;
import com.bht.pim.entity.group.ProjectEntityGroup;
import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
@Repository
@Transactional
@SuppressWarnings("all")
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
    public long getNumberOfProjects() {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            return sessionFactory.getCurrentSession()
                    .createQuery(query
                            .select(builder.count(root)))
                    .getSingleResult();

        } catch (Exception exception) {

            log.info(exception);
            return -1;
        }
    }

    @Override
    public long getNumberOfProjectsByStatus(String status) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            return sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .select(builder.count(root))
                            .where(builder.equal(root.get("status"), status)))
                    .getSingleResult();

        } catch (Exception exception) {

            log.info(exception);
            return -1;
        }
    }

    @Override
    public long getNumberOfProjectsByKeyword(String keyword) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            return sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .select(builder.count(root))
                            .where(builder.or(
                                    builder.like(root.get("number").as(String.class), convertKeyword(keyword)),
                                    builder.like(root.get("name"), convertKeyword(keyword)),
                                    builder.like(root.get("customer"), convertKeyword(keyword)))))
                    .getSingleResult();

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
    public List<ProjectEntity> getProjectList(int maxRow, int pageIndex) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> query = builder.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            TypedQuery<ProjectEntity> allQuery = sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .select(root)
                            .orderBy(builder.desc(root)));

            allQuery.setMaxResults(maxRow);
            allQuery.setFirstResult(maxRow * pageIndex);

            return allQuery.getResultList();

        } catch (Exception exception) {

            log.info(exception);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProjectEntity> getProjectListByStatus(int maxRow, int pageIndex, String status) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> query = builder.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            TypedQuery<ProjectEntity> queryByStatus = sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .select(root)
                            .where(builder.equal(root.get("status"), status))
                            .orderBy(builder.desc(root)));

            queryByStatus.setMaxResults(maxRow);
            queryByStatus.setFirstResult(maxRow * pageIndex);

            return queryByStatus.getResultList();

        } catch (Exception exception) {

            log.info(exception);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProjectEntity> getProjectListByKeyword(int maxRow, int pageIndex, String keyword) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> query = builder.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            TypedQuery<ProjectEntity> queryByStatus = sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .select(root)
                            .where(builder.or(
                                    builder.like(root.get("number").as(String.class), convertKeyword(keyword)),
                                    builder.like(root.get("name"), convertKeyword(keyword)),
                                    builder.like(root.get("customer"), convertKeyword(keyword))))
                            .orderBy(builder.desc(root)));

            queryByStatus.setMaxResults(maxRow);
            queryByStatus.setFirstResult(maxRow * pageIndex);

            return queryByStatus.getResultList();

        } catch (Exception exception) {

            log.info(exception);
            return Collections.emptyList();
        }
    }

    @Override
    public ProjectEntity getProjectByNumber(long number) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> query = builder.createQuery(ProjectEntity.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            ProjectEntity projectEntity = sessionFactory.getCurrentSession()
                    .createQuery(query
                            .select(root)
                            .where(builder.equal(root.get("number"), number)))
                    .getSingleResult();

            // As Hibernate is lazy-initialization !
            Hibernate.initialize(projectEntity.getEnrolledEmployees());

            return projectEntity;

        } catch (Exception exception) {

            log.info(exception);
            return null;
        }
    }

    @Override
    public List<ProjectEntityGroup> getProjectGroupByStatus() {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<ProjectEntityGroup> query = builder.createQuery(ProjectEntityGroup.class);
            Root<ProjectEntity> root = query.from(ProjectEntity.class);

            Expression<String> groupByStatusExp = root.get("status").as(String.class);
            Expression<Long> countGroupByStatus = builder.count(groupByStatusExp);

            TypedQuery<ProjectEntityGroup> queryByStatus = sessionFactory
                    .getCurrentSession()
                    .createQuery(query
                            .multiselect(groupByStatusExp, countGroupByStatus)
                            .groupBy(groupByStatusExp));

            return queryByStatus.getResultList();

        } catch (Exception exception) {

            log.info(exception);
            return null;
        }
    }

    // for using like to search for containings
    private String convertKeyword(String keyword) {
        return "%" + keyword + "%";
    }
}
