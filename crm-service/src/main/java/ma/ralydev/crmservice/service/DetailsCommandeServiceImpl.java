package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.mapper.DetailsCommandeMapper;
import ma.ralydev.crmservice.repository.DetailsCommandeRepository;
import ma.ralydev.crmservice.validation.DetailsCommandeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DetailsCommandeServiceImpl implements DetailsCommandeService {

    private final DetailsCommandeValidator validator;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final DetailsCommandeMapper detailsCommandeMapper;

    public DetailsCommandeServiceImpl(DetailsCommandeValidator validator,
                                      DetailsCommandeRepository detailsCommandeRepository,
                                      DetailsCommandeMapper detailsCommandeMapper) {
        this.validator = validator;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.detailsCommandeMapper = detailsCommandeMapper;
    }

    public void saveDetailsCommande(DetailsCommande detailsCommande) {
        validator.validate(detailsCommande);
    }

    @Override
    public List<DetailsCommandeDTO> getAllDetailsCommandes() {
        return detailsCommandeRepository.findAll()
                .stream()
                .map(detailsCommandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetailsCommandeDTO getDetailsCommandeById(Long id) {
        DetailsCommande detailsCommande = detailsCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailsCommande not found with id: " + id));
        return detailsCommandeMapper.toDto(detailsCommande);
    }

    @Override
    public DetailsCommandeDTO saveDetailsCommande(DetailsCommandeDTO detailsCommandeDTO) {
        DetailsCommande detailsCommande = detailsCommandeMapper.toEntity(detailsCommandeDTO);
        if(detailsCommandeDTO.getEnregistrementAudio() != null) {
            String base64Data = detailsCommandeDTO.getEnregistrementAudio().split(",")[1];
            detailsCommande.setEnregistrementAudio(Base64.getDecoder().decode(base64Data));
        }
        validator.validate(detailsCommande);
        DetailsCommande savedDetails = detailsCommandeRepository.save(detailsCommande);
        return detailsCommandeMapper.toDto(savedDetails);
    }

    @Override
    public DetailsCommandeDTO updateDetailsCommande(Long id, DetailsCommandeDTO detailsCommandeDTO) {
        DetailsCommande existing = detailsCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailsCommande not found with id: " + id));

        detailsCommandeMapper.updateDetailsFromDto(detailsCommandeDTO, existing);
        validator.validate(existing);

        DetailsCommande updatedDetails = detailsCommandeRepository.save(existing);
        return detailsCommandeMapper.toDto(updatedDetails);
    }

    @Override
    public void deleteDetailsCommande(Long id) {
        if (!detailsCommandeRepository.existsById(id)) {
            throw new RuntimeException("DetailsCommande not found with id: " + id);
        }
        detailsCommandeRepository.deleteById(id);
    }
}