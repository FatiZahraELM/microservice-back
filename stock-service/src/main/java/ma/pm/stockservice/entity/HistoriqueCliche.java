package ma.pm.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class HistoriqueCliche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliche cliche;
    private String support;
    private String observation;
    private Long idLotDeProduction;

    public Cliche getCliche() {
        return cliche;
    }

    public void setCliche(Cliche cliche) {
        this.cliche = cliche;
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
