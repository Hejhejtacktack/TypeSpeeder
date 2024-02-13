package se.ju23.typespeeder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usernames")
public class Username {
    @Id
    private Integer id;
    @Column(name = "username")
    private String value;

    public Username() {
    }

    public Username(String value) {
        this.value = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private boolean validate() {
        return true;
    }
}
