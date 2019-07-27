package com.bht.pim.dao.impl;

import com.bht.pim.dao.EmployeeDao;
import com.bht.pim.entity.EmployeeEntity;
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
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(ProjectDaoImpl.class);

    @Override
    public List<EmployeeEntity> getAllEmployees() {

        logger.info(sessionFactory);

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = builder.createQuery(EmployeeEntity.class);

        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);
        TypedQuery<EmployeeEntity> allQuery = sessionFactory.getCurrentSession()
                .createQuery(query.select(root));

        return allQuery.getResultList();
    }

    @Override
    public EmployeeEntity getEmployeeById(long id) {
        return sessionFactory.getCurrentSession()
                .get(EmployeeEntity.class, id);
    }
}
