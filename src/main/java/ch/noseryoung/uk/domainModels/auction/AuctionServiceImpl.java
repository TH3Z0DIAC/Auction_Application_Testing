package ch.noseryoung.uk.domainModels.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionServiceImpl implements AuctionService {

    //
    private AuctionRepository auctionRepository;

    // Injecting dependencies over Constructor
    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    // Logic to create a new auction
    @Override
    public Auction create(Auction auction) {
        return auctionRepository.save(auction);
    }

    // Logic to retrieve all auctions
    @Override
    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    // Logic to find a single auction by id
    @Override
    public Auction findById(int id) {
        Optional<Auction> optionalAuction = auctionRepository.findById(id);
        if(optionalAuction.isPresent()) {
            return optionalAuction.get();
        } else {
            throw new NoSuchElementException("Entity with ID " + id + " does not exist");
        }
    }

    @Override
    public List<Auction> findByRange(int min, int max) {
        if(min < max) {
            List<Auction> listOfAuctions = auctionRepository.findAll();
            List<Auction> listOfAuctionsInRange = new ArrayList<>();

            for(Auction auction : listOfAuctions) {
                if(auction.getPrize() >= min && auction.getPrize() <= max) {
                    listOfAuctionsInRange.add(auction);
                }
            }
            return listOfAuctionsInRange;
        } else {
            throw new IllegalArgumentException("Min cannot be lower than max");
        }

    }

    // Logic to update data of a single auction by id
    @Override
    public Auction updateById(int id, Auction auction) {
       if(auctionRepository.existsById(id)) {
           auction.setId(id);
           auctionRepository.save(auction);
           return auction;
       } else {
           throw new NoSuchElementException("No value present");
       }
    }

    // Logic to delete an auction with an id
    @Override
    public void deleteById(int id) {
        auctionRepository.deleteById(id);
        System.out.println("Entity with the following id: [" + id + "] has been removed.");
    }
}
