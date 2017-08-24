package org.kobyshev.model;

import java.util.Calendar;

public enum Period {

    SECOND(Calendar.SECOND),
    MINUTE(Calendar.MINUTE),
    HOUR(Calendar.HOUR),
    DAY(Calendar.DATE);

    private int value;

    Period(int value) {
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
