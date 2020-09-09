package ch.noseryoung.uk.domainModels.auction.dto;

import ch.noseryoung.uk.domainModels.auction.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.Set;

// Annotation that defines interface as mapper
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuctionMapper {

    // Method to generate a representative DTO from a Business Object
    AuctionDTO toDTO(Auction dm);

    // Method to generate a representative List of DTO's from a List of Business Objects
    List<AuctionDTO> toDTOs(List<Auction> dms);

    // Method to generate a representative Set of DTO's from a Set of Business Objects
    Set<AuctionDTO> toDTOs(Set<Auction> dms);

    // Method to generate a Business Object from a representative DTO
    Auction fromDTO(AuctionDTO dto);

    // Method to generate a List of Business Object from a representative DTO List
    List<Auction> fromDTOs(List<AuctionDTO> dtoList);

    // Method to generate a Set of Business Object from a representative DTO Set
    Set<Auction> fromDTOs(Set<AuctionDTO> dtoSet);

}
