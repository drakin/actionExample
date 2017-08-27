package org.kobyshev.service;

import org.kobyshev.model.Action;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ActionServiceImpl implements ActionService {

    private final List<Long> actionList = new CopyOnWriteArrayList<>();

    public void addAction(Action action) {
        if (action == null) return;
        getActionList().add(getCurrentTimeMillis());
    }

    private Integer countActions(Integer diffInMillis) {
        Long currentTime = getCurrentTimeMillis();
        // Assume that all actions are sorted (they should appended)
        int binarySearch = Collections.binarySearch(getActionList(), currentTime - diffInMillis);
        if (binarySearch >= 0) {
            // if value presented, it's mean that boundary in list
            // find the last one if several values are presented.
            // and exclude them.
            Long val = getActionList().get(binarySearch);
            while (binarySearch + 1 < getActionList().size() && Objects.equals(getActionList().get(binarySearch + 1), val)) {
                binarySearch++;
            }
            binarySearch++;
        } else {
            // if value not presented then fix index
            binarySearch = -binarySearch - 1;
        }
        // return items count between (boundary,last]
        return getActionList().size() - binarySearch;
    }

    @Transactional(readOnly = true)
    public Integer getLastSecondActionsCount() {
        return countActions(1000);
    }

    @Transactional(readOnly = true)
    public Integer getLastMinuteActionsCount() {
        return countActions(1000 * 60);
    }

    @Transactional(readOnly = true)
    public Integer getLastHourActionsCount() {
        return countActions(1000 * 60 * 60);
    }

    @Transactional(readOnly = true)
    public Integer getLastDayActionsCount() {
        return countActions(1000 * 60 * 60 * 24);
    }

    private List<Long> getActionList() {
        return actionList;
    }

    private Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
