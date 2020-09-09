package ch.noseryoung.uk.domainModels.auction;

import java.util.List;

// This is an example service with method signatures for CRUD logic
public interface AuctionService {

    Auction create(Auction auction);

    List<Auction> findAll();

    List<Auction> findByRange(int min, int max);

    Auction findById(int id);

    Auction updateById(int id, Auction auction);

    void deleteById(int id);
}
