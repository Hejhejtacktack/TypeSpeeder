package se.ju23.typespeeder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
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

    private boolean validate() {
        return true;
    }
}
