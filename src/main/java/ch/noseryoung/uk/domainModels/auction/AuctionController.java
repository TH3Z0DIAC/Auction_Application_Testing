package ch.noseryoung.uk.domainModels.auction;

import ch.noseryoung.uk.domainModels.auction.dto.AuctionDTO;
import ch.noseryoung.uk.domainModels.auction.dto.AuctionMapper;
import ch.noseryoung.uk.domainModels.authority.dto.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    // Service to be Injected
    private AuctionService auctionService;

    // Mapper to be Injected
    private AuctionMapper auctionMapper;

    // Injecting dependencies over Constructor
    @Autowired
    public AuctionController(AuctionService auctionService, AuctionMapper auctionMapper) {
        this.auctionService = auctionService;
        this.auctionMapper = auctionMapper;
    }

    // Endpoint retrieving a single Auction by it's id
    @GetMapping("/{id}")
    public ResponseEntity<AuctionDTO> getById(@PathVariable int id) {
        return new ResponseEntity<>(auctionMapper.toDTO(auctionService.findById(id)), HttpStatus.OK);
    }

    @GetMapping({"/{min}/{max}"})
    public ResponseEntity<List<AuctionDTO>> getByRange(@PathVariable int min, @PathVariable int max) {
        return new ResponseEntity<>(auctionMapper.toDTOs(auctionService.findByRange(min, max)), HttpStatus.OK);
    }

    // Endpoint retrieving all Auctions as a List
    @GetMapping({"/", ""})
    public ResponseEntity<List<AuctionDTO>> getAll() {
        return new ResponseEntity<>(auctionMapper.toDTOs(auctionService.findAll()), HttpStatus.OK);
    }

    // Endpoint retrieving a new Auction with the given data
    @PostMapping({"/", ""})
    public ResponseEntity<AuctionDTO> create(@RequestBody AuctionDTO auctionDTO) {
        return new ResponseEntity<>(auctionMapper.toDTO(auctionService.create(auctionMapper.fromDTO(auctionDTO))), HttpStatus.CREATED);
    }

    // Endpoint updating an Auction by its id with the given data
    @PutMapping("/{id}")
    public ResponseEntity<AuctionDTO> updateById(@PathVariable int id, @RequestBody AuctionDTO auctionDTO) {
        return new ResponseEntity<>(auctionMapper.toDTO(auctionService.updateById(id, auctionMapper.fromDTO(auctionDTO))), HttpStatus.OK);
    }

    // Endpoint deleting an auction by its id
    @DeleteMapping("/{id}")
    public ResponseEntity<AuctionDTO> deleteById(@PathVariable int id) {
        auctionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
