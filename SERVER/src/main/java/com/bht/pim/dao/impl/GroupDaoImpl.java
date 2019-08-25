package com.bht.pim.dao.impl;

import com.bht.pim.dao.GroupDao;
import com.bht.pim.entity.GroupEntity;
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
import java.util.Collections;
import java.util.List;

@Log4j
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long nextIdValue() {
        try {
            String sql = "SELECT IDENT_CURRENT('[GROUP]') + 1 AS ID";
            List results = sessionFactory.getCurrentSession()
                    .createSQLQuery(sql).list();

            return ((BigDecimal) results.get(0)).longValue();

        } catch (Exception exception) {

            log.info(exception);
            return -1;
        }
    }

    @Override
    public boolean addGroup(GroupEntity groupEntity) {
        try {
            sessionFactory.getCurrentSession()
                    .save(groupEntity);

            return true;

        } catch (Exception exception) {
            log.info(exception);

            return false;
        }
    }

    @Override
    public GroupEntity getGroupById(long id) {
        try {
            GroupEntity groupEntity =
                    sessionFactory.getCurrentSession()
                            .get(GroupEntity.class, id);

            // As Hibernate is lazy-initialization !
            Hibernate.initialize(groupEntity.getJoinedProjects());

            return groupEntity;

        } catch (Exception exception) {

            log.info(exception);
            return null;
        }
    }

    @Override
    public List<GroupEntity> getGroupList(int maxRow, int pageIndex) {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);

            Root<GroupEntity> root = query.from(GroupEntity.class);
            TypedQuery<GroupEntity> allQuery = sessionFactory.getCurrentSession()
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
    public long getNumberOfGroups() {
        try {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<GroupEntity> root = query.from(GroupEntity.class);

            return sessionFactory.getCurrentSession()
                    .createQuery(query
                            .select(builder.count(root)))
                    .getSingleResult();

        } catch (Exception exception) {

            log.info(exception);
            return -1;
        }
    }
}
