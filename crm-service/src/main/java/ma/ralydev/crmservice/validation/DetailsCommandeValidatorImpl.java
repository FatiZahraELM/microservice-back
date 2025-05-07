package ma.ralydev.crmservice.validation;

import ma.ralydev.crmservice.entity.*;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
@Component
public class DetailsCommandeValidatorImpl implements DetailsCommandeValidator {

    @Override
    public void validate(DetailsCommande commande) {
        validateChoixN(commande.getSensSortie(), commande.getChoixN());
        validateImpression(commande);
        validateCouleur(commande);
    }

    private void validateChoixN(SensSortie sens, ChoixN choix) {
        if (sens == null || choix == null) return;

        if (sens == SensSortie.EXTERNE &&
                !EnumSet.of(ChoixN.N1, ChoixN.N2, ChoixN.N3, ChoixN.N4).contains(choix)) {
            throw new IllegalArgumentException("ChoixN invalide pour EXTERNE");
        }

        if (sens == SensSortie.INTERNE &&
                !EnumSet.of(ChoixN.N5, ChoixN.N6, ChoixN.N7, ChoixN.N8).contains(choix)) {
            throw new IllegalArgumentException("ChoixN invalide pour INTERNE");
        }
    }

    private void validateImpression(DetailsCommande commande) {
        if (commande.isImpression()) {
            if (commande.getTypeImpression() == null) {
                throw new IllegalArgumentException("Type d'impression requis si impression = true");
            }

            if (commande.getNbCouleursRecto() == null &&
                    commande.getTypeImpression() != TypeImpression.VERSO) {
                throw new IllegalArgumentException("NbCouleursRecto requis sauf si VERSO seul");
            }

            if (commande.getTypeImpression() == TypeImpression.RECTO_VERSO &&
                    commande.getNbCouleursVerso() == null) {
                throw new IllegalArgumentException("NbCouleursVerso requis pour RECTO_VERSO");
            }

            if (commande.getTypeImpression() == TypeImpression.RECTO &&
                    commande.getNbCouleursVerso() != null) {
                throw new IllegalArgumentException("NbCouleursVerso doit être null si impression est RECTO");
            }

            if (commande.getTypeImpression() == TypeImpression.VERSO &&
                    commande.getNbCouleursRecto() != null) {
                throw new IllegalArgumentException("NbCouleursRecto doit être null si impression est VERSO");
            }
        } else {
            if (commande.getTypeImpression() != null ||
                    commande.getNbCouleursRecto() != null ||
                    commande.getNbCouleursVerso() != null) {
                throw new IllegalArgumentException("Les champs liés à l'impression doivent être null si impression = false");
            }
        }
    }

    private void validateCouleur(DetailsCommande commande) {
        if (commande.isImpression()) {
            if (commande.getNbCouleursRecto() != null &&
                    (commande.getCouleursRecto() == null /*
                            ||
                          //  commande.getCouleursRecto().size() != commande.getNbCouleursRecto()*/)) {
                throw new IllegalArgumentException("NbCouleursRecto ne correspond pas à la taille de la liste couleursRecto");
            }

            if (commande.getNbCouleursVerso() != null &&
                    (commande.getCouleursVerso() == null /*||
                            commande.getCouleursVerso().size() != commande.getNbCouleursVerso()*/)) {
                throw new IllegalArgumentException("NbCouleursVerso ne correspond pas à la taille de la liste couleursVerso");
            }
        }
    }

}
