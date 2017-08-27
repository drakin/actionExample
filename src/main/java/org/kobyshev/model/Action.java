package org.kobyshev.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Action {

    private String value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date actionDate = new Date();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getActionDate() {
        return actionDate;
    }

}
