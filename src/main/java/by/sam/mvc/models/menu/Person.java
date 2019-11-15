package by.sam.mvc.models.menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    public Person() {
    }



    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        phones.add( phone );
        phone.setPerson( this );
    }

//    public void removePhone(Phone phone) {
//        phones.remove( phone );
//        phone.setPerson( null );
//    }




}