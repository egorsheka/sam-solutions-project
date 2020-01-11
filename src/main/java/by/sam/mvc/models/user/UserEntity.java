package by.sam.mvc.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    private String idVerification;
    private boolean isVerify;



    @ManyToOne
    private Role role;

    public UserEntity() {}

    public UserEntity(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String email, String password, Role role, String idVerification, boolean isVerify) {
        this.email = email;
        this.password = password;
        this.idVerification = idVerification;
        this.isVerify = isVerify;
        this.role = role;
    }

    public UserEntity(UserEntity userEntity) {
        this.email = userEntity.email;
        this.password = userEntity.password;
        this.idVerification = userEntity.idVerification;
        this.isVerify = userEntity.isVerify;
        this.role = userEntity.role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role;}

    public String getIdVerification() { return idVerification;}

    public void setIdVerification(String idVerification) { this.idVerification = idVerification;}

    public boolean isVerify() { return isVerify;}

    public void setVerify(boolean verify) { isVerify = verify;}
}