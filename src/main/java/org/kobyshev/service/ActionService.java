package org.kobyshev.service;

import org.kobyshev.model.Action;

import java.util.List;

public interface ActionService {

    Action addAction(Action action);

    Action getAction(int id);

    Long getLastSecondActionsCount();

    Long getLastMinuteActionsCount();

    Long getLastHourActionsCount();

    Long getLastDayActionsCount();
}
