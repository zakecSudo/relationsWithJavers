package si.codemonkee.relationsWithJavers.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PONS")
public class Pon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Person> persons;

    public Pon() {
    }

    public Pon(Long id, String description, List<Person> persons) {
        this.id = id;
        this.description = description;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
