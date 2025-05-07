package ma.pm.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class HistoriqueFormeDecoupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private FormeDecoupe formeDecoupe;
    private Long idLotDeProduction;
    private String support;
    private String observation;

    public FormeDecoupe getFormeDecoupe() {
        return formeDecoupe;
    }

    public void setFormeDecoupe(FormeDecoupe formeDecoupe) {
        this.formeDecoupe = formeDecoupe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLotDeProduction() {
        return idLotDeProduction;
    }

    public void setIdLotDeProduction(Long idLotDeProduction) {
        this.idLotDeProduction = idLotDeProduction;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
