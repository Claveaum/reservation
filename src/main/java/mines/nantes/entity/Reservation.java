package mines.nantes.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mclaveau on 20/10/15.
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ressource")
    private Ressource ressource;

    @Column(name="datedebut")
    private Date dateDebut;

    @Column(name="datefin")
    private Date dateFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ressource getRessource()
    {
        return ressource;
    }

    public void setRessource(Ressource ressource)
    {
        this.ressource=ressource;
    }


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
