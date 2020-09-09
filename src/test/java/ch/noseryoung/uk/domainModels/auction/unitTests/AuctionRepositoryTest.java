package ch.noseryoung.uk.domainModels.auction.unitTests;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class AuctionRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuctionRepository auctionRepository;

    private int id;

    private Auction auctionToBeTestedAgainst1;
    private Auction auctionToBeTestedAgainst2;


    @BeforeEach
    void setUp() {
        auctionToBeTestedAgainst1 = new Auction().setItem("TestAuction");
        auctionToBeTestedAgainst2 = new Auction().setId(2).setItem("TestAuction2");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findByItem_requestAuctionsByItem_returnListOfAuctions() throws Exception {

        Optional<Auction> auction1 = auctionRepository.findById(auctionRepository.save(auctionToBeTestedAgainst1).getId());
        Auction auction = auction1.get();

        Assertions.assertThat(auctionRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat((auctionRepository.findAll()).get(0).getItem()).isEqualTo("TestAuction");
    }


}