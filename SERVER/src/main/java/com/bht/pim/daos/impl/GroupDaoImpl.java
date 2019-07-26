package com.bht.pim.daos.impl;

import com.bht.pim.daos.GroupDao;
import com.bht.pim.entities.GroupEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @Autowired
    SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(ProjectDaoImpl.class);

    @Override
    public long nextIdValue() {
        String sql = "SELECT IDENT_CURRENT('[GROUP]') + 1 AS ID";
        List results = sessionFactory.getCurrentSession()
                .createSQLQuery(sql).list();

        return ((BigDecimal) results.get(0)).longValue();
    }

    @Override
    public boolean addGroup(GroupEntity groupEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(groupEntity);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean updateGroup(GroupEntity groupEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .merge(groupEntity);

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public boolean deleteGroup(long id) {
        try {
            sessionFactory.getCurrentSession()
                    .delete(getGroupById(id));

            return true;

        } catch (Exception exception) {
            logger.info(exception);

            return false;
        }
    }

    @Override
    public GroupEntity getGroupById(long id) {
        return sessionFactory.getCurrentSession()
                .get(GroupEntity.class, id);
    }

    @Override
    public List<GroupEntity> getAllGroups() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);

        Root<GroupEntity> root = query.from(GroupEntity.class);
        TypedQuery<GroupEntity> allQuery = sessionFactory.getCurrentSession()
                .createQuery(query.select(root));

        return allQuery.getResultList();
    }
}
