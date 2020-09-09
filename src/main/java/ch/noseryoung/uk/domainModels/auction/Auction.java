package ch.noseryoung.uk.domainModels.auction;

import org.hibernate.annotations.Type;

import javax.persistence.*;

// Annotation to show hibernate that this class is an entity
@Entity
// Defines Table name for Database
@Table(name = "auction")
public class Auction {

    // Regular Attributes
    // Defines the following variable as PrimaryKey in Database
    @Id
    // Annotation defines PrimaryKey as Auto Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Annotation to define name of column in table & set NOT NULL constraint
    @Column(name = "item", nullable = false)
    private String item;

    @Column
    // Annotation to define the type in the column (DB)
    @Type(type = "org.hibernate.type.DoubleType")
    private double prize;


    // Standard Constructor
    public Auction() {}


    // Getters and Setters
    public int getId() {return id;}

    public Auction setId(int id) {
        this.id = id;
        return this;
    }

    public String getItem() {
        return item;
    }

    public double getPrize() {
        return prize;
    }

    public Auction setItem(String item) {
        this.item = item;
        return this;
    }

    public Auction setPrize(double prize) {
        this.prize = prize;
        return this;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", prize=" + prize +
                '}';
    }
}
