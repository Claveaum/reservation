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
    @Column(name="id")
    private int id;

    @OneToMany(mappedBy="ressource", targetEntity=Reservation.class)
    private List<Reservation> listReservation;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeRessource type;

    @Column(name="nom")
    private String nom;

    @Column(name="description")
    private String description;

    @Column(name="localisation")
    private String localisation;

    @OneToOne
    @JoinColumn(name = "responsable")
    private Utilisateur responsable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public List<Reservation> getListReservation() {
        return listReservation;
    }

    public void setListReservation(List<Reservation> listReservation) {
        this.listReservation = listReservation;
    }

    public TypeRessource getType() {
        return type;
    }

    public void setType(TypeRessource type) {
        this.type = type;
    }*/

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
/*
    public Utilisateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }*/
}
