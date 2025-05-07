package ma.ralydev.productionservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LotDeProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OrdreDeFabrication ordreDeFabrication;
    private LocalDate dateUtilisation;
    private long qteEtiquette;
    private long nombreTour;
    @ManyToOne
    private Chaine chaine;

    public Chaine getChaine() {
        return chaine;
    }

    public void setChaine(Chaine chaine) {
        this.chaine = chaine;
    }

    public LocalDate getDateUtilisation() {
        return dateUtilisation;
    }

    public void setDateUtilisation(LocalDate dateUtilisation) {
        this.dateUtilisation = dateUtilisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(long nombreTour) {
        this.nombreTour = nombreTour;
    }

    public OrdreDeFabrication getOrdreDeFabrication() {
        return ordreDeFabrication;
    }

    public void setOrdreDeFabrication(OrdreDeFabrication ordreDeFabrication) {
        this.ordreDeFabrication = ordreDeFabrication;
    }

    public long getQteEtiquette() {
        return qteEtiquette;
    }

    public void setQteEtiquette(long qteEtiquette) {
        this.qteEtiquette = qteEtiquette;
    }
}
