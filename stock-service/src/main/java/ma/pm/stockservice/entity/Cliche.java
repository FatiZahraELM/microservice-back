package ma.pm.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long nmrSerie;
    @ManyToOne
    private Fournisseur fournisseur;
    private Date dateDeProduction;
    private String nmrBL_FA;

    public Date getDateDeProduction() {
        return dateDeProduction;
    }

    public void setDateDeProduction(Date dateDeProduction) {
        this.dateDeProduction = dateDeProduction;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmrBL_FA() {
        return nmrBL_FA;
    }

    public void setNmrBL_FA(String nmrBL_FA) {
        this.nmrBL_FA = nmrBL_FA;
    }

    public long getNmrSerie() {
        return nmrSerie;
    }

    public void setNmrSerie(long nmrSerie) {
        this.nmrSerie = nmrSerie;
    }
}
