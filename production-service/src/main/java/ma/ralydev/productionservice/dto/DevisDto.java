package ma.ralydev.productionservice.dto;

public class DevisDto {
    private Long id;

    private String reference;

    private CommandeDTO commandeDTO;

    private double montant;

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

    public CommandeDTO getCommandeDTO() {
        return commandeDTO;
    }

    public void setCommandeDTO(CommandeDTO commandeDTO) {
        this.commandeDTO = commandeDTO;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
