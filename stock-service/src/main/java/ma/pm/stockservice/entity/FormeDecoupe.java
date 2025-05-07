package ma.pm.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FormeDecoupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nmrPlanDecoupe;
    private String nmrSerie;
    @ManyToOne
    private Fournisseur fournisseur;

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

    public String getNmrPlanDecoupe() {
        return nmrPlanDecoupe;
    }

    public void setNmrPlanDecoupe(String nmrPlanDecoupe) {
        this.nmrPlanDecoupe = nmrPlanDecoupe;
    }

    public String getNmrSerie() {
        return nmrSerie;
    }

    public void setNmrSerie(String nmrSerie) {
        this.nmrSerie = nmrSerie;
    }
}
