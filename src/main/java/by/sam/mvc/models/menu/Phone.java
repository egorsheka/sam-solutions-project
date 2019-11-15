package by.sam.mvc.models.menu;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Phone")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(name = "`number`", unique = true)
    private String number;

    @ManyToOne
    private Person person;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}