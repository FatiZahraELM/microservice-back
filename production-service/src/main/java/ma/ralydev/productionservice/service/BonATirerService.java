package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.BonATirer;

import java.util.List;

public interface BonATirerService {
    List<BonATirer> getAllBonATirer();
    BonATirer getBonATirerById(Long id);
    BonATirer saveBonATirer(BonATirer bonATirer);
    BonATirer updateBonATirer(Long id, BonATirer bonATirer);
    void deleteBonATirer(Long id);
}
