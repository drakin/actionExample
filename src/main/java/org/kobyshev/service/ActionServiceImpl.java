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

    @Transactional(readOnly = true)
    public Long getLastSecondActionsCount() {
        return this.actionDao.getLastActions(Period.SECOND);
    }

    @Transactional(readOnly = true)
    public Long getLastMinuteActionsCount() {
        return this.actionDao.getLastActions(Period.MINUTE);
    }

    @Transactional(readOnly = true)
    public Long getLastHourActionsCount() {
        return this.actionDao.getLastActions(Period.HOUR);
    }

    @Transactional(readOnly = true)
    public Long getLastDayActionsCount() {
        return this.actionDao.getLastActions(Period.DAY);
    }
}
