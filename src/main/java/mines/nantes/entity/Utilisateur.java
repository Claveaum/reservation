package mines.nantes.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mclaveau on 20/10/15.
 */
@Entity
@Table(name = "UTILISATEUR",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"login"})
        }
)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "responsable")
    private List<Ressource> listeRessource;

    @Column(name = "admin", columnDefinition = "boolean default false")
    private boolean admin;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> listeReservation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Ressource> getListeRessource() {
        return listeRessource;
    }

    public void setListeRessource(List<Ressource> listeRessource) {
        this.listeRessource = listeRessource;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }
}
