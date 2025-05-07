package ma.ralydev.productionservice.mapper;


import ma.ralydev.productionservice.dto.DevisDto;
import ma.ralydev.productionservice.entity.Devis;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DevisMapper {

    DevisMapper INSTANCE = Mappers.getMapper(DevisMapper.class);

     DevisDto toDto(Devis devis);

     Devis toEntity(DevisDto devisDTO);
}
