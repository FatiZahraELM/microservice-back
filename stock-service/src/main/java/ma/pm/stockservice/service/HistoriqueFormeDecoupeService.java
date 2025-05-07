package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.HistoriqueFormeDecoupe;
import java.util.List;
import java.util.Optional;

public interface HistoriqueFormeDecoupeService {
    List<HistoriqueFormeDecoupe> getAllHistoriqueFormeDecoupes();
    Optional<HistoriqueFormeDecoupe> getHistoriqueFormeDecoupeById(Long id);
    HistoriqueFormeDecoupe saveHistoriqueFormeDecoupe(HistoriqueFormeDecoupe historiqueFormeDecoupe);
    HistoriqueFormeDecoupe updateHistoriqueFormeDecoupe(Long id, HistoriqueFormeDecoupe historiqueFormeDecoupe);
    void deleteHistoriqueFormeDecoupe(Long id);
}
