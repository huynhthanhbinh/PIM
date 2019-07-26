package com.bht.pim.daos.impl;

import com.bht.pim.daos.EmployeeDao;
import com.bht.pim.entities.EmployeeEntity;
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

        logger.info("<<< PIM - EMPLOYEE LIST >>>");
        allQuery.getResultList().forEach(logger::info);

        return allQuery.getResultList();
    }
}
