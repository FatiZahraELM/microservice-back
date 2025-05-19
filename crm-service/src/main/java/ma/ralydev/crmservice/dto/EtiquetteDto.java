package ma.ralydev.crmservice.dto;

public class EtiquetteDto {
    private Long id;
    private String nomEtq;
    private long laize;
    private long developpe;
    private String forme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEtq() {
        return nomEtq;
    }

    public void setNomEtq(String nomEtq) {
        this.nomEtq = nomEtq;
    }

    public long getLaize() {
        return laize;
    }

    public void setLaize(long laize) {
        this.laize = laize;
    }

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
}
