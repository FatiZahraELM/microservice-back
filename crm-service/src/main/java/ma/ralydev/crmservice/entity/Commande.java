package ma.ralydev.crmservice.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private Date dateCommande;
    private double quantite;
    @OneToOne
    private DetailsCommande detailsCommande;
    @ManyToOne
    private Client client;
    private Long idEtiquette;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public DetailsCommande getDetailsCommande() {
        return detailsCommande;
    }

    public void setDetailsCommande(DetailsCommande detailsCommande) {
        this.detailsCommande = detailsCommande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEtiquette() {
        return idEtiquette;
    }

    public void setIdEtiquette(Long idEtiquette) {
        this.idEtiquette = idEtiquette;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
