package org.kobyshev.service;

import org.kobyshev.dao.ActionDao;
import org.kobyshev.model.Action;
import org.kobyshev.model.Period;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    private ActionDao actionDao;

    public ActionServiceImpl(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    public Action addAction(Action action) {
        if (action == null) return null;
        return this.actionDao.addAction(action);
    }

    public Action getAction(int id) {
        return this.actionDao.getActionById(id);
    }

    public List<Action> getLastSecondActions() {
        return this.actionDao.getLastActions(Period.SECOND);
    }

    @Transactional
    public List<Action> getLastMinuteActions() {
        return this.actionDao.getLastActions(Period.MINUTE);
    }

    @Transactional
    public List<Action> getLastHourActions() {
        return this.actionDao.getLastActions(Period.HOUR);
    }

    @Transactional
    public List<Action> getLastDayActions() {
        return this.actionDao.getLastActions(Period.DAY);
    }
}
