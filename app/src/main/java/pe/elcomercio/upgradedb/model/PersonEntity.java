package pe.elcomercio.upgradedb.model;

/**
 * Created by Ricardo Bravo on 4/02/16.
 */

public class PersonEntity {

    String id, name, lastname, email;

    public PersonEntity() {
    }

    public PersonEntity(String id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public PersonEntity(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
