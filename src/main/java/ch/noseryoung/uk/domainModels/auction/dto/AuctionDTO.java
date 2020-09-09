package ch.noseryoung.uk.domainModels.auction.dto;


public class AuctionDTO {

    // Representative attributes
    private int id;
    private String item;
    private double prize;


    // Empty standard constructor
    public AuctionDTO() {}


    // Classic Getter and Setter
    public int getId() {
        return id;
    }

    public AuctionDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getItem() {
        return item;
    }

    public AuctionDTO setItem(String item) {
        this.item = item;
        return this;
    }

    public double getPrize() {
        return prize;
    }

    public AuctionDTO setPrize(double prize) {
        this.prize = prize;
        return this;
    }


}
