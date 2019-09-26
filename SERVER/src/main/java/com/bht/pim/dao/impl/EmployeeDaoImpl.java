package com.bht.pim.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EmployeeEntity> getEmployeeList(int maxRow, int pageIndex) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<EmployeeEntity> query = builder.createQuery(EmployeeEntity.class);

            Root<EmployeeEntity> root = query.from(EmployeeEntity.class);
            TypedQuery<EmployeeEntity> allQuery = sessionFactory.getCurrentSession()
                    .createQuery(query
                            .select(root)
                            .orderBy(builder.desc(root)));

            if (maxRow != 0) { // not get all
                allQuery.setMaxResults(maxRow);
                allQuery.setFirstResult(maxRow * pageIndex);
            }

            return allQuery.getResultList();

        } catch (Exception exception) {

            log.warn(exception);
            return Collections.emptyList();
        }
    }

    @Override
    public EmployeeEntity getEmployeeById(long id) {
        try {
            EmployeeEntity employeeEntity =
                    sessionFactory.getCurrentSession()
                            .get(EmployeeEntity.class, id);

            // As Hibernate is lazy-initialization !
            Hibernate.initialize(employeeEntity.getEnrolledProjects());

            return employeeEntity;

        } catch (Exception exception) {

            log.warn(exception);
            return null;
        }
    }

    @Override
    public long getNumberOfEmployees() {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

            return sessionFactory.getCurrentSession()
                    .createQuery(query
                            .select(builder.count(root)))
                    .getSingleResult();

        } catch (Exception exception) {

            log.warn(exception);
            return -1;
        }
    }
}