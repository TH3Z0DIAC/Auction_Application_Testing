package ch.noseryoung.uk.domainModels.auction.unitTests;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionService auctionService;

    private Auction auctionToBeTestedAgainst;
    private List<Auction> listOfAuctionsToBeTested;
    private int min;
    private int max;

    @BeforeEach
    public void setUp() {
        auctionToBeTestedAgainst = new Auction()
                .setId(180819)
                .setItem("Chocolate")
                .setPrize(1.95);

        listOfAuctionsToBeTested = Arrays.asList(
                new Auction()
                        .setId(1234)
                        .setItem("Lenses")
                        .setPrize(39.95),
                new Auction()
                        .setId(1122)
                        .setItem("Tomatoes")
                        .setPrize(2.95)
        );

        min = 1;
        max = 5;
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    // This methods contains all to be done for first A -> Arrange (Creating Mock -> Perfect working logic)
    public void findById_requestAuctionById_returnAuction() throws Exception {
        given(auctionService.findById(anyInt())).will(invocation -> {
            //TODO refactor Exception to BadRequestException
            if ("non-existent".equals(invocation.getArgument(0))) {
                throw new Exception();
            }
            return auctionToBeTestedAgainst;
        });

        mvc.perform(
                MockMvcRequestBuilders.get("/auctions/{id}", auctionToBeTestedAgainst.getId())
                        .accept(MediaType.APPLICATION_JSON))    // second A -> Act (Like Request written in Postman)
                .andExpect(MockMvcResultMatchers.status().isOk())
                // From now on: Third A -> Assert (Is DTO from JSON what it is expected to be)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(auctionToBeTestedAgainst.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item").value(auctionToBeTestedAgainst.getItem()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prize").value(auctionToBeTestedAgainst.getPrize()));

        // ONLY FOR PARAMETER IN METHOD TO TEST
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(auctionService, times(1)).findById(integerArgumentCaptor.capture());
        Assertions.assertEquals(auctionToBeTestedAgainst.getId(), integerArgumentCaptor.getValue());
    }

    @Test
    public void findAll_requestAllAuctions_returnListOfAuctions() throws Exception {
        given(auctionService.findAll()).willReturn(listOfAuctionsToBeTested);

        mvc.perform(
                MockMvcRequestBuilders.get("/auctions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(listOfAuctionsToBeTested.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(listOfAuctionsToBeTested.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].item").value(listOfAuctionsToBeTested.get(0).getItem()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].item").value(listOfAuctionsToBeTested.get(1).getItem()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prize").value(listOfAuctionsToBeTested.get(0).getPrize()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].prize").value(listOfAuctionsToBeTested.get(1).getPrize()));

        verify(auctionService, times(1)).findAll();

    }

    @Test
    public void findByRange_requestAuctionsById_returnListOfAuctions() throws Exception {
        given(auctionService.findByRange(min, max)).willReturn(listOfAuctionsToBeTested);

        mvc.perform(
        MockMvcRequestBuilders.get("/auctions/{min}/{max}", min, max)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(listOfAuctionsToBeTested.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].item").value(listOfAuctionsToBeTested.get(0).getItem()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prize").value(listOfAuctionsToBeTested.get(0).getPrize()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(listOfAuctionsToBeTested.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].item").value(listOfAuctionsToBeTested.get(1).getItem()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].prize").value(listOfAuctionsToBeTested.get(1).getPrize()));


        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> integerArgumentCaptorTwo = ArgumentCaptor.forClass(Integer.class);
        verify(auctionService, times(1)).findByRange(integerArgumentCaptor.capture(), integerArgumentCaptorTwo.capture());
        Assertions.assertEquals(min, integerArgumentCaptor.getValue());
        Assertions.assertEquals(max, integerArgumentCaptorTwo.getValue());
    }
}
