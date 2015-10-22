package mines.nantes.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mclaveau on 20/10/15.
 */
@Entity
@Table(name = "RESSOURCE")
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToMany(mappedBy="ressource")
    private List<Reservation> listeReservation;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeRessource type;

    @Column(name="nom")
    private String nom;

    @Column(name="description")
    private String description;

    @Column(name="localisation")
    private String localisation;

    @ManyToOne
    @JoinColumn(name = "responsable")
    private Utilisateur responsable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listReservation) {
        this.listeReservation = listReservation;
    }

    public TypeRessource getType() {
        return type;
    }

    public void setType(TypeRessource type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Utilisateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }
}
