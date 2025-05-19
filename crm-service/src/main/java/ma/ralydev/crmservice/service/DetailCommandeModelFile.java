package ma.ralydev.crmservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import ma.ralydev.crmservice.config.CloudinaryConfig;
import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class DetailCommandeModelFile {

    @Value("${max-file-size}")
    private Long maxFileSize;

    private final DetailsCommandeService detailsCommandeService;
    private final Cloudinary cloudinary;


    public DetailCommandeModelFile(DetailsCommandeService service, CloudinaryConfig cloudConfig) {
        this.detailsCommandeService = service;

        // Add logging
        System.out.println("Cloudinary Config:");
        System.out.println("Cloud Name: " + cloudConfig.getCloudName());
        System.out.println("API Key: " + cloudConfig.getApiKey());
        System.out.println("API Secret: " + (cloudConfig.getApiSecret() != null ? "******" : "null"));

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudConfig.getCloudName(),
                "api_key", cloudConfig.getApiKey(),
                "api_secret", cloudConfig.getApiSecret()
        ));
    }

    public String uploadFile(Long id, MultipartFile file) throws IOException {
        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds the maximum allowed size");
        }

        DetailsCommandeDTO detailsCommandeDTO = detailsCommandeService.getDetailsCommandeById(id);
        String originalFileName = file.getOriginalFilename();
        String cleanFileName = null; // Nettoyage du nom
        if (originalFileName != null) {
            cleanFileName = originalFileName.replace(" ", "_");
        }

        // Extraction extension
        assert cleanFileName != null;
        int lastDot = cleanFileName.lastIndexOf('.');
        String baseName = lastDot > 0 ? cleanFileName.substring(0, lastDot) : cleanFileName;
        String publicId = "commandes/" + id + "/" + baseName + "_" + UUID.randomUUID().toString().substring(0, 8);

        // Suppression de l'ancien fichier si existe
        if (detailsCommandeDTO.getFile_name() != null) {
            try {
//                log.info("Uploading commandes file {}: deleting old one {}",publicId, detailsCommandeDTO.getFile_name());
                deleteFile(id); // Utilise la méthode existante de suppression
//                log.info("end deleiting");
            } catch (Exception e) {
//                log.error("Échec suppression ancien fichier", e);
                // On continue quand même l'upload
            }
        }

        // Upload sécurisé vers Cloudinary
        Map uploadParams = ObjectUtils.asMap(
                "public_id", "commandes/" + id + "/" + UUID.randomUUID(),
                "resource_type", "auto",
                "type", "upload", // IMPORTANT
                "access_mode", "public", // DÉBLOCAGE MANUEL
                "invalidate", true,
                "unique_filename", false // Important pour utiliser exactement le public_id fourni

        );
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        String secureUrl = (String) uploadResult.get("secure_url");
        detailsCommandeDTO.setFile_name(baseName + "|" + secureUrl);
        detailsCommandeService.updateDetailsCommande(id, detailsCommandeDTO);
//        log.info("Upload file successful");
        return "commande uploadée: " + baseName;
    }

    private boolean verifyFileExists(String publicId) {
        String[] resourceTypes = {"image", "raw"};

        for (String resourceType : resourceTypes) {
            try {
                Map resource = cloudinary.api().resource(
                        publicId.replace(".pdf", ""),
                        ObjectUtils.asMap(
                                "resource_type", resourceType,
                                "type", "upload"
                        ));
//                log.info("Fichier trouvé comme {}: {}", resourceType, resource);
                return true;
            } catch (Exception e) {
//                log.debug("Non trouvé comme {}: {}", resourceType, e.getMessage());
            }
        }
        return false;
    }

    public String deleteFile(Long id) throws IOException {
        try {
//            log.info("deleting");
            DetailsCommandeDTO detailsCommandeDTO = detailsCommandeService.getDetailsCommandeById(id);
            if (detailsCommandeDTO == null || detailsCommandeDTO.getFile_name() == null) {
                throw new FileNotFoundException("Document introuvable");
            }
//            log.info("extracting file url pour suppression");
            String cloudinaryUrl = extractCloudinaryUrl(detailsCommandeDTO.getFile_name());
            if (cloudinaryUrl == null) {
                detailsCommandeDTO.setFile_name(null);
                detailsCommandeService.updateDetailsCommande(id, detailsCommandeDTO);
                return "Référence nettoyée (format invalide)";
            }
//            log.info("debut extraction PublicID ");

            String publicId = extractPublicId(cloudinaryUrl);
//            log.info("PublicID extrait: {}", publicId);

            // Tentative de suppression directe sans vérification préalable
            boolean deleted = deleteFromCloudinary(publicId);

            if (!deleted) {
                // Double vérification si la suppression échoue
                if (verifyFileExists(publicId)) {
//                    log.warn("Le fichier existe toujours après suppression - PublicID: {}", publicId);
                    return "Échec de la suppression (contactez l'admin)";
                }
            }

            detailsCommandeDTO.setFile_name(null);
            detailsCommandeService.updateDetailsCommande(id, detailsCommandeDTO);
            return deleted ? "Fichier supprimé avec succès" : "Fichier déjà absent";

        } catch (Exception e) {
//            log.error("Erreur critique lors de la suppression - commande: {} - Erreur: {}",
//                    id, e.getMessage(), e);
            throw new IOException("Erreur technique lors de la suppression", e);
        }
    }

    private String extractPublicId(String cloudinaryUrl) {
        try {
            // Solution universelle qui gère tous les formats d'URL
            String[] parts = cloudinaryUrl.split("/upload/");
            if (parts.length < 2) {
                throw new IllegalArgumentException("URL Cloudinary invalide");
            }

            String withVersion = parts[1];
            // Supprime le versioning (v123456/) et les paramètres optionnels (?)
            return withVersion.replaceAll("v[0-9]+/", "")
                    .split("\\?")[0];
        } catch (Exception e) {
//            log.error("Erreur extraction publicId: {}", e.getMessage());
            throw new IllegalArgumentException("Impossible d'extraire le public_id", e);
        }
    }

    private boolean deleteFromCloudinary(String publicId) {
        try {
//            log.info("Tentative de suppression - PublicID: {}", publicId);

            // 1. Supprimer l'extension .pdf du public_id
            String basePublicId = publicId.replace(".pdf", "");

            // 2. Essayer avec les deux types de ressources
            String[] resourceTypes = {"image", "raw"};

            for (String resourceType : resourceTypes) {
                try {
                    Map result = cloudinary.uploader().destroy(basePublicId,
                            ObjectUtils.asMap(
                                    "resource_type", resourceType,
                                    "type", "upload",
                                    "invalidate", true
                            ));

//                    log.info("Réponse Cloudinary ({}): {}", resourceType, result);

                    if ("ok".equals(result.get("result"))) {
                        return true;
                    }
                } catch (Exception e) {
//                    log.warn("Échec suppression ({}): {}", resourceType, e.getMessage());
                }
            }

            // 3. Si les méthodes normales échouent, utiliser l'API Admin
            return adminForceDelete(basePublicId);

        } catch (Exception e) {
//            log.error("Échec critique de suppression: {}", e.getMessage());
            return false;
        }
    }

    private boolean adminForceDelete(String publicId) {
        try {
            Map result = cloudinary.api().deleteResources(
                    Collections.singletonList(publicId),
                    ObjectUtils.asMap(
                            "resource_type", "raw",
                            "type", "upload"
                    ));

//            log.info("Réponse Admin API: {}", result);
            return true;
        } catch (Exception e) {
//            log.error("Échec Admin API: {}", e.getMessage());
            return false;
        }
    }

    private String extractCloudinaryUrl(String combinedValue) {
        if (combinedValue == null) {
//            log.warn("Valeur nulle fournie");
            return null;
        }

//        log.info("Tentative d'extraction URL depuis: {}", combinedValue);

        // Cas 1: Format moderne "nom|url"
        if (combinedValue.contains("|")) {
            String[] parts = combinedValue.split("\\|");
            if (parts.length >= 2 && parts[1].startsWith("http")) {
                return parts[1];
            }
        }

        // Cas 2: Ancien format (juste le nom de fichier)
        if (combinedValue.matches(".*\\.(pdf|jpg|png|jpeg)$")) {
//            log.warn("Ancien format détecté - migration nécessaire");
            return null;
        }
//        log.warn("Format non reconnu: {}", combinedValue);
        return null;
    }


    public String getFileUrl(Long id) throws FileNotFoundException {
        DetailsCommandeDTO detailsCommandeDTO = detailsCommandeService.getDetailsCommandeById(id);
        String combined = detailsCommandeDTO.getFile_name();
        if (combined == null) {
            throw new FileNotFoundException("Fichier introuvable");
        }
        return extractCloudinaryUrl(combined);
    }
}