package org.kobyshev.service;

import org.kobyshev.model.Action;

public interface ActionService {

    void addAction(Action action);

    Integer getLastSecondActionsCount();

    Integer getLastMinuteActionsCount();

    Integer getLastHourActionsCount();

    Integer getLastDayActionsCount();
}
