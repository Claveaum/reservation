package mines.nantes.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mclaveau on 20/10/15.
 */
@Entity
@Table(name = "TYPE_RESSOURCE", schema = "ROOT")
public class TypeRessource {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="nom")
    private String nom;

    @OneToMany(mappedBy="type", targetEntity=Ressource.class)
    private List<Ressource> listeRessource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ressource> getListeRessource() {
        return listeRessource;
    }

    public void setListeRessource(List<Ressource> listeRessource) {
        this.listeRessource = listeRessource;
    }
}
