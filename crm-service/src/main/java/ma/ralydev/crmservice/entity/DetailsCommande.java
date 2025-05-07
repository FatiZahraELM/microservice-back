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
    private PoseEtq poseEtq;
    private SensSortie sensSortie;
    private ChoixN choixN;

    private boolean impression;
    private TypeImpression typeImpression;
    private Integer nbCouleursRecto;
    private Integer nbCouleursVerso;
    @ManyToOne
    private Couleur couleursRecto;
    @ManyToOne
    private Couleur couleursVerso;

    private Long formeDecoupeId;
    private boolean formeDecoupeACommander;

    @ElementCollection
    private List<Long> clicheIds;
    private boolean clicheACommander;

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

    public Couleur getCouleursRecto() {
        return couleursRecto;
    }

    public void setCouleursRecto(Couleur couleursRecto) {
        this.couleursRecto = couleursRecto;
    }

    public Couleur getCouleursVerso() {
        return couleursVerso;
    }

    public void setCouleursVerso(Couleur couleursVerso) {
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
}
