package ma.ralydev.crmservice.dto;


import java.util.List;

public class DetailsCommandeDTO {
    private Long id;
    private int nbEtqSurBobine;
    private int nbEtqSurFront;
    private boolean repiquage;
    private boolean vernis;
    private boolean dorure;
    private boolean plastification;
    private int mandrin;
    private String poseEtq;
    private String sensSortie;
    private String choixN;
    private boolean impression;
    private String typeImpression;
    private Integer nbCouleursRecto;
    private Integer nbCouleursVerso;
    private List<Long> couleursRectoId; // Au lieu de couleurRectoId
    private List<Long> couleursVersoId; // Au lieu de couleurVersoId
    private Long formeDecoupeId;
    private boolean formeDecoupeACommander;
    private List<Long> clicheIds;
    private boolean clicheACommander;
    private String enregistrementAudio;
    private String commentaire;
    private String file_name;
    private double espaceLaize;
    private  double espaceDeveloppe;
    private int nbPosesLaize;
    private  int nbPosesDeveloppe;
    private int cylindreZ;



    // Getters & Setters

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getCylindreZ() {
        return cylindreZ;
    }

    public void setCylindreZ(int cylindreZ) {
        this.cylindreZ = cylindreZ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isRepiquage() {
        return repiquage;
    }

    public void setRepiquage(boolean repiquage) {
        this.repiquage = repiquage;
    }

    public boolean isVernis() {
        return vernis;
    }

    public void setVernis(boolean vernis) {
        this.vernis = vernis;
    }

    public boolean isDorure() {
        return dorure;
    }

    public void setDorure(boolean dorure) {
        this.dorure = dorure;
    }

    public boolean isPlastification() {
        return plastification;
    }

    public void setPlastification(boolean plastification) {
        this.plastification = plastification;
    }

    public int getMandrin() {
        return mandrin;
    }

    public void setMandrin(int mandrin) {
        this.mandrin = mandrin;
    }

    public String getPoseEtq() {
        return poseEtq;
    }

    public void setPoseEtq(String poseEtq) {
        this.poseEtq = poseEtq;
    }

    public String getSensSortie() {
        return sensSortie;
    }

    public void setSensSortie(String sensSortie) {
        this.sensSortie = sensSortie;
    }

    public String getChoixN() {
        return choixN;
    }

    public void setChoixN(String choixN) {
        this.choixN = choixN;
    }

    public boolean isImpression() {
        return impression;
    }

    public void setImpression(boolean impression) {
        this.impression = impression;
    }

    public String getTypeImpression() {
        return typeImpression;
    }

    public void setTypeImpression(String typeImpression) {
        this.typeImpression = typeImpression;
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

    public List<Long> getCouleursRectoId() {
        return couleursRectoId;
    }

    public void setCouleursRectoId(List<Long> couleursRectoId) {
        this.couleursRectoId = couleursRectoId;
    }

    public List<Long> getCouleursVersoId() {
        return couleursVersoId;
    }

    public void setCouleursVersoId(List<Long> couleursVersoId) {
        this.couleursVersoId = couleursVersoId;
    }

    public Long getFormeDecoupeId() {
        return formeDecoupeId;
    }

    public void setFormeDecoupeId(Long formeDecoupeId) {
        this.formeDecoupeId = formeDecoupeId;
    }

    public boolean isFormeDecoupeACommander() {
        return formeDecoupeACommander;
    }

    public void setFormeDecoupeACommander(boolean formeDecoupeACommander) {
        this.formeDecoupeACommander = formeDecoupeACommander;
    }

    public List<Long> getClicheIds() {
        return clicheIds;
    }

    public void setClicheIds(List<Long> clicheIds) {
        this.clicheIds = clicheIds;
    }

    public boolean isClicheACommander() {
        return clicheACommander;
    }

    public void setClicheACommander(boolean clicheACommander) {
        this.clicheACommander = clicheACommander;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getEnregistrementAudio() {
        return enregistrementAudio;
    }

    public void setEnregistrementAudio(String enregistrementAudio) {
        this.enregistrementAudio = enregistrementAudio;
    }

    public double getEspaceLaize() {
        return espaceLaize;
    }

    public void setEspaceLaize(double espaceLaize) {
        this.espaceLaize = espaceLaize;
    }

    public double getEspaceDeveloppe() {
        return espaceDeveloppe;
    }

    public void setEspaceDeveloppe(double espaceDeveloppe) {
        this.espaceDeveloppe = espaceDeveloppe;
    }

    public int getNbPosesLaize() {
        return nbPosesLaize;
    }

    public void setNbPosesLaize(int nbPosesLaize) {
        this.nbPosesLaize = nbPosesLaize;
    }

    public int getNbPosesDeveloppe() {
        return nbPosesDeveloppe;
    }

    public void setNbPosesDeveloppe(int nbPosesDeveloppe) {
        this.nbPosesDeveloppe = nbPosesDeveloppe;
    }
}
