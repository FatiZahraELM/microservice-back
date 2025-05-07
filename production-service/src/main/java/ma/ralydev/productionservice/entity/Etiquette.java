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
public class Etiquette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomEtq;
    private long laize;
    private long developpe;
    private String forme;

    public long getDeveloppe() {
        return developpe;
    }

    public void setDeveloppe(long developpe) {
        this.developpe = developpe;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLaize() {
        return laize;
    }

    public void setLaize(long laize) {
        this.laize = laize;
    }

    public String getNomEtq() {
        return nomEtq;
    }

    public void setNomEtq(String nomEtq) {
        this.nomEtq = nomEtq;
    }
}
