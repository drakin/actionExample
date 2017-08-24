package org.kobyshev.dao;

import org.kobyshev.model.Action;
import org.kobyshev.model.Period;

import java.util.List;

public interface ActionDao {
    Action addAction(Action action);

    List<Action> getLastActions(Period period);

    Action getActionById(int id);
}
