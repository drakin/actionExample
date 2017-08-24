package org.kobyshev.service;

import org.kobyshev.model.Action;

import java.util.List;

public interface ActionService {

    Action addAction(Action action);

    Action getAction(int id);

    List<Action> getLastSecondActions();

    List<Action> getLastMinuteActions();

    List<Action> getLastHourActions();

    List<Action> getLastDayActions();
}
