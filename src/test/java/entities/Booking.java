package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Booking {
    @Setter @Getter
    private String firstname;
    @Setter @Getter
    private String lastname;
    @Setter @Getter
    private int totalprice;
    @Setter @Getter
    private String depositpaid;
    @Setter @Getter
    private BookingDates bookingdates;
    @Setter @Getter
    private String additionalneeds;

    public Booking(List<String> data)
    {
        BookingDates bookingDates = new BookingDates(data.get(4),data.get(5));
        this.firstname = data.get(0);
        this.lastname = data.get(1);
        this.totalprice = Integer.valueOf(data.get(2));
        this.depositpaid = data.get(3);
        this.bookingdates = bookingDates;
        this.additionalneeds = data.get(6);
    }
}
