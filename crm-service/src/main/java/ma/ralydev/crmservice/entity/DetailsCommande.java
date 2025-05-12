package ma.ralydev.crmservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class DetailsCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbEtqSurBobine;
    private int nbEtqSurFront;
    private boolean repiquage;
    private boolean vernis;
    private boolean dorure;
    private boolean plastification;
    private int mandrin;
    @Enumerated(EnumType.STRING)
    private PoseEtq poseEtq;
    @Enumerated(EnumType.STRING)
    private SensSortie sensSortie;
    @Enumerated(EnumType.STRING)
    private ChoixN choixN;

    private boolean impression;
    @Enumerated(EnumType.STRING)
    private TypeImpression typeImpression;
    private Integer nbCouleursRecto;
    private Integer nbCouleursVerso;
    @ManyToMany
    @JoinTable(
            name = "details_commande_couleurs_recto",
            joinColumns = @JoinColumn(name = "details_commande_id"),
            inverseJoinColumns = @JoinColumn(name = "couleur_id")
    )
    private List<Couleur> couleursRecto;
    @ManyToMany
    @JoinTable(
            name = "details_commande_couleurs_verso",
            joinColumns = @JoinColumn(name = "details_commande_id"),
            inverseJoinColumns = @JoinColumn(name = "couleur_id")
    )
    private List<Couleur> couleursVerso;

    private Long formeDecoupeId;
    private boolean formeDecoupeACommander;

    @ElementCollection
    private List<Long> clicheIds;
    private boolean clicheACommander;
    @Lob
    @Column(name = "enregistrement_audio", columnDefinition = "LONGBLOB")
    private byte[] enregistrementAudio;
    private String commentaire;
    public ChoixN getChoixN() {
        return choixN;
    }

    public void setChoixN(ChoixN choixN) {
        this.choixN = choixN;
    }

    public boolean isClicheACommander() {
        return clicheACommander;
    }

    public void setClicheACommander(boolean clicheACommander) {
        this.clicheACommander = clicheACommander;
    }

    public List<Long> getClicheIds() {
        return clicheIds;
    }

    public void setClicheIds(List<Long> clicheIds) {
        this.clicheIds = clicheIds;
    }

    public List<Couleur> getCouleursRecto() {
        return couleursRecto;
    }

    public void setCouleursRecto(List<Couleur> couleursRecto) {
        this.couleursRecto = couleursRecto;
    }

    public List<Couleur> getCouleursVerso() {
        return couleursVerso;
    }

    public void setCouleursVerso(List<Couleur> couleursVerso) {
        this.couleursVerso = couleursVerso;
    }

    public boolean isDorure() {
        return dorure;
    }

    public void setDorure(boolean dorure) {
        this.dorure = dorure;
    }

    public boolean isFormeDecoupeACommander() {
        return formeDecoupeACommander;
    }

    public void setFormeDecoupeACommander(boolean formeDecoupeACommander) {
        this.formeDecoupeACommander = formeDecoupeACommander;
    }

    public Long getFormeDecoupeId() {
        return formeDecoupeId;
    }

    public void setFormeDecoupeId(Long formeDecoupeId) {
        this.formeDecoupeId = formeDecoupeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isImpression() {
        return impression;
    }

    public void setImpression(boolean impression) {
        this.impression = impression;
    }

    public int getMandrin() {
        return mandrin;
    }

    public void setMandrin(int mandrin) {
        this.mandrin = mandrin;
    }

    public Integer getNbCouleursRecto() {
        return nbCouleursRecto;
    }

    public void setNbCouleursRecto(Integer nbCouleursRecto) {
        this.nbCouleursRecto = nbCouleursRecto;
    }

    public Integer getNbCouleursVerso() {
        return nbCouleursVerso;
    }

    public void setNbCouleursVerso(Integer nbCouleursVerso) {
        this.nbCouleursVerso = nbCouleursVerso;
    }

    public int getNbEtqSurBobine() {
        return nbEtqSurBobine;
    }

    public void setNbEtqSurBobine(int nbEtqSurBobine) {
        this.nbEtqSurBobine = nbEtqSurBobine;
    }

    public int getNbEtqSurFront() {
        return nbEtqSurFront;
    }

    public void setNbEtqSurFront(int nbEtqSurFront) {
        this.nbEtqSurFront = nbEtqSurFront;
    }

    public boolean isPlastification() {
        return plastification;
    }

    public void setPlastification(boolean plastification) {
        this.plastification = plastification;
    }

    public PoseEtq getPoseEtq() {
        return poseEtq;
    }

    public void setPoseEtq(PoseEtq poseEtq) {
        this.poseEtq = poseEtq;
    }

    public boolean isRepiquage() {
        return repiquage;
    }

    public void setRepiquage(boolean repiquage) {
        this.repiquage = repiquage;
    }

    public SensSortie getSensSortie() {
        return sensSortie;
    }

    public void setSensSortie(SensSortie sensSortie) {
        this.sensSortie = sensSortie;
    }

    public TypeImpression getTypeImpression() {
        return typeImpression;
    }

    public void setTypeImpression(TypeImpression typeImpression) {
        this.typeImpression = typeImpression;
    }

    public boolean isVernis() {
        return vernis;
    }

    public void setVernis(boolean vernis) {
        this.vernis = vernis;
    }


    public byte[] getEnregistrementAudio() {
        return enregistrementAudio;
    }

    public void setEnregistrementAudio(byte[] enregistrementAudio) {
        this.enregistrementAudio = enregistrementAudio;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
