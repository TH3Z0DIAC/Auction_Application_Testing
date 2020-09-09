package ch.noseryoung.uk.domainModels.auction.integrationTests;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@DirtiesContext(classMode = AFTER_CLASS)             Cleans up database after each class
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD) // Cleans up database after each method
public class AuctionIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    public void findAll_requestAllAuctions_returnsAllAuctions() throws Exception {
        Auction auction1 = new Auction().setItem("BMW").setPrize(200000);
        Auction auction2 = new Auction().setItem("Tesla").setPrize(170000);
        Auction auction1return = auctionRepository.save(auction1);
        Auction auction2return = auctionRepository.save(auction2);

        mvc.perform(
                MockMvcRequestBuilders.get("/auctions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.containsInAnyOrder(auction1return.getId(),auction2return.getId(),500)));

//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].item").value(auction1return.getItem()))
  //              .andExpect(MockMvcResultMatchers.jsonPath("$[1].item").value(auction2return.getItem()))
    //            .andExpect(MockMvcResultMatchers.jsonPath("$[0].prize").value(auction1return.getPrize()))
      //          .andExpect(MockMvcResultMatchers.jsonPath("$[1].prize").value(auction2return.getPrize()));


    }


}
