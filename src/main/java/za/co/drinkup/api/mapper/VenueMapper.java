package za.co.drinkup.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.drinkup.api.dto.VenueDto;
import za.co.drinkup.api.entity.Venue;

import java.util.List;
@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    VenueDto modelToDto(Venue venue);

    Venue dtoToModel(VenueDto venueDto);
}
