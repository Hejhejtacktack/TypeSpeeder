package se.ju23.typespeeder.model;

import jakarta.persistence.*;
import se.ju23.typespeeder.exception.ValidationException;

@Entity
@Table(name = "usernames")
public class Username {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String value;

    public Username() {
    }

    public Username(String value) throws ValidationException {
        validate(value);
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

    @Override
    public String toString() {
        return this.value;
    }

    private void validate(String input) throws ValidationException {
        if (input == null || input.isEmpty() || !input.matches("[a-zA-z-1-9]+")) {
            throw new ValidationException("Error: in Username creation.");
        }
    }
}
