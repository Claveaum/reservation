package mines.nantes.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by mclaveau on 20/10/15.
 */
@Entity
@Table(name = "TYPE_RESSOURCE",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nom"})
        }
)
public class TypeRessource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "type")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Ressource> listeRessource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ressource> getListeRessource() {
        return listeRessource;
    }

    public void setListeRessource(List<Ressource> listeRessource) {
        this.listeRessource = listeRessource;
    }


}
