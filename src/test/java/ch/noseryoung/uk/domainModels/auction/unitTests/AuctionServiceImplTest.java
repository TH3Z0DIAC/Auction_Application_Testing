package ch.noseryoung.uk.domainModels.auction.unitTests;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuctionServiceImplTest {

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository auctionRepository;

    private List<Auction> auctionsToBeTested;
    private int min;
    private int max;

    @BeforeEach
    void setUp() {
        auctionsToBeTested = new ArrayList<>(Arrays.asList(
                new Auction()
                        .setId(1234)
                        .setItem("Lenses")
                        .setPrize(29),
                new Auction()
                        .setId(1235)
                        .setItem("Lenses")
                        .setPrize(30),
                new Auction()
                        .setId(1236)
                        .setItem("Lenses")
                        .setPrize(0),
                new Auction()
                        .setId(1238)
                        .setItem("Lenses")
                        .setPrize(1),
                new Auction()
                        .setId(1237)
                        .setItem("Lenses")
                        .setPrize(31)

        ));
        min = 0;
        max = 30;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findByRange_requestAuctionsByRange_returnListOfAuctions() throws Exception {
        given(auctionRepository.findAll()).willReturn(auctionsToBeTested);

        List<Auction> listOfAuctionsReturnedByRange = auctionService.findByRange(min, max);

        Assertions.assertThat(listOfAuctionsReturnedByRange.get(0).getId()).isEqualTo(auctionsToBeTested.get(0).getId());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(1).getId()).isEqualTo(auctionsToBeTested.get(1).getId());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(0).getItem()).isEqualTo(auctionsToBeTested.get(0).getItem());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(1).getItem()).isEqualTo(auctionsToBeTested.get(1).getItem());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(0).getPrize()).isEqualTo(auctionsToBeTested.get(0).getPrize());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(1).getPrize()).isEqualTo(auctionsToBeTested.get(1).getPrize());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(2).getId()).isEqualTo(auctionsToBeTested.get(2).getId());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(3).getId()).isEqualTo(auctionsToBeTested.get(3).getId());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(2).getItem()).isEqualTo(auctionsToBeTested.get(2).getItem());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(3).getItem()).isEqualTo(auctionsToBeTested.get(3).getItem());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(2).getPrize()).isEqualTo(auctionsToBeTested.get(2).getPrize());
        Assertions.assertThat(listOfAuctionsReturnedByRange.get(3).getPrize()).isEqualTo(auctionsToBeTested.get(3).getPrize());

        verify(auctionRepository, times(1)).findAll();


    }
}