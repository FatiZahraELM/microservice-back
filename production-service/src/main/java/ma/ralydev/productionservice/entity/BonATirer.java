package ma.ralydev.productionservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BonATirer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomEtiquette;
    private long idCommande;
    private long idInfographiste;

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }

    public String getNomEtiquette() {
        return nomEtiquette;
    }

    public void setNomEtiquette(String nomEtiquette) {
        this.nomEtiquette = nomEtiquette;
    }

    public long getIdInfographiste() {
        return idInfographiste;
    }

    public void setIdInfographiste(long idInfographiste) {
        this.idInfographiste = idInfographiste;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
