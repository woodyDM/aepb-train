package cn.aepb.train;

import cn.aepb.train.data.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ParkLotTest {

    private ParkLot parkLot;
    private int partLotCapacity;
    private String partLotName;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        partLotCapacity = 3;
        partLotName = "partLotName";
        parkLot = new ParkLot(partLotCapacity, partLotName);
    }

    @Test
    public void should_init_with_right_parameters() {
        assertEquals(parkLot.getCapacity(), partLotCapacity);
        assertEquals(parkLot.getName(), partLotName);
        assertEquals(parkLot.availableSize(), partLotCapacity);
    }


    @Test
    public void should_park_when_park_lot_is_not_full() {
        Ticket ticket = parkLot.park(new Car("SZ123"));

        assertEquals(parkLot.availableSize(), partLotCapacity - 1);
        assertNotNull(ticket);
        assertEquals(ticket.getParkLotName(), partLotName);
        assertNotNull(ticket.getToken());
    }


    @Test
    public void should_unPark_when_give_valid_ticket() {
        Ticket ticket = parkLot.park(new Car("SZ123"));

        Car car = parkLot.unPark(ticket);

        assertEquals(partLotCapacity, parkLot.availableSize());
        Assert.assertEquals(car.getName(), "SZ123");
    }


    @Test
    public void should_not_park_when_parkLot_is_full() {

        parkLot.park(new Car("SJ123"));
        parkLot.park(new Car("SJ124"));
        parkLot.park(new Car("SJ125"));

        expectedException.expect(ParkLotIsFullException.class);
        parkLot.park(new Car("SZ126"));

    }

    @Test
    public void should_not_unPark_when_given_invalid_ticket() {
        Ticket ticket = new Ticket("some-token", partLotName);
        expectedException.expect(InvalidTicketException.class);

        parkLot.unPark(ticket);
    }

    @Test
    public void should_return_true_if_ticket_is_valid() {
        Ticket ticket = parkLot.park(new Car("SZ123"));

        assertTrue(parkLot.isTicketValid(ticket));
    }


    @Test
    public void should_return_false_if_ticket_is_invalid() {
        parkLot.park(new Car("SZ123"));
        parkLot.park(new Car("SZ124"));
        parkLot.park(new Car("SZ125"));

        assertFalse(parkLot.isTicketValid(new Ticket("some-invalid-token", partLotName)));
    }

    @Test
    public void should_return_available_rate() {
        parkLot.park(new Car("SZ123"));
        parkLot.park(new Car("SZ124"));

        BigDecimal rate = parkLot.getRate();

        assertEquals(rate, BigDecimal.valueOf(33.33));
    }


    @Test
    public void should_return_print_string() {
        parkLot.park(new Car("SZ123"));

        Report.Data reportData = parkLot.getReportData();

        assertEquals(reportData.getAvailable(),2);
        assertEquals(reportData.getCap(),3);

    }
}
