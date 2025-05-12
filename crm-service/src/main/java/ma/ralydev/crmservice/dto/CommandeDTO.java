package ma.ralydev.crmservice.dto;


import ma.ralydev.crmservice.entity.StatutCommande;

import java.util.Date;

public class CommandeDTO {
    private Long id;
    private String reference;
    private String client;
    private double quantite;
    private Long idEtiquette;
    private Date dateCommande;
    private DetailsCommandeDTO detailsCommande; // Ajout du DTO des détails
    private StatutCommande statut;

    // Getters & Setters

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getIdEtiquette() {
        return idEtiquette;
    }

    public void setIdEtiquette(Long idEtiquette) {
        this.idEtiquette = idEtiquette;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public DetailsCommandeDTO getDetailsCommande() {
        return detailsCommande;
    }

    public void setDetailsCommande(DetailsCommandeDTO detailsCommande) {
        this.detailsCommande = detailsCommande;
    }
}