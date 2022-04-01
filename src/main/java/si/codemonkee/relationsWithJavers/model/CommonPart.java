package si.codemonkee.relationsWithJavers.model;

import javax.persistence.*;

@Entity
@Table(name = "COMMON_PARTS")
public class CommonPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Pon pon;

    public CommonPart() {
    }

    public CommonPart(Long id, String name, Pon pon) {
        this.id = id;
        this.name = name;
        this.pon = pon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pon getPon() {
        return pon;
    }

    public void setPon(Pon pon) {
        this.pon = pon;
    }
}
