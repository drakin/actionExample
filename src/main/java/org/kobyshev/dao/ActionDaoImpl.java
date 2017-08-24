package org.kobyshev.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kobyshev.model.Action;
import org.kobyshev.model.Period;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ActionDaoImpl implements ActionDao {

    private SessionFactory sessionFactory;

    public ActionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Action addAction(Action action) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(action);
        return action;
    }

    @Transactional(readOnly = true)
    public List<Action> getLastActions(Period period) {
        Session session = this.sessionFactory.getCurrentSession();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(period.getValue(), -1);
        return (List<Action>) session.createQuery(
                "SELECT a FROM Action a WHERE a.actionDate > :startDate AND a.actionDate <= :endDate")
                .setParameter("startDate", calendar.getTime(), TemporalType.DATE)
                .setParameter("endDate", now, TemporalType.DATE)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Action getActionById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Action.class, id);
    }

}
