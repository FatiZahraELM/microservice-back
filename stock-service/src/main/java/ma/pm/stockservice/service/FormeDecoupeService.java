package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.FormeDecoupe;
import java.util.List;
import java.util.Optional;

public interface FormeDecoupeService {
    List<FormeDecoupe> getAllFormesDecoupe();
    Optional<FormeDecoupe> getFormeDecoupeById(Long id);
    FormeDecoupe saveFormeDecoupe(FormeDecoupe formeDecoupe);
    FormeDecoupe updateFormeDecoupe(Long id, FormeDecoupe formeDecoupe);
    void deleteFormeDecoupe(Long id);
}
