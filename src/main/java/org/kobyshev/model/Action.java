package org.kobyshev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;
    @Column(name = "ACTION_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date actionDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
