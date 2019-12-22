package cn.aepb.train;

import cn.aepb.train.data.Report;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SmartParkingBoyTest extends BaseParkingBoyTest {


    @Before
    public void setUp() throws Exception {
        boy = new SmartParkingBoy();
        super.init();
    }


    @Test
    public void should_park_to_max_cap_lot3_when_park() {
        Ticket ticket = boy.park(new Car("SZ123"));

        assertEquals(ticket.getParkLotName(), "lot3");
        assertEquals(boy.getManagedParkLots().get(0).availableSize(), cap1);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3 - 1);

    }

    @Test
    public void should_park_in_order_when_the_last_two_lot_has_same_available() {
        boy.park(new Car("SZ123"));
        Ticket ticket = boy.park(new Car("SZ122"));

        assertEquals(ticket.getParkLotName(), "lot2");
        assertEquals(boy.getManagedParkLots().get(0).availableSize(), cap1);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2 - 1);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3 - 1);

    }

    @Test
    public void should_park_in_order_when_all_lot_has_same_available() {
        boy.park(new Car("SZ123")); //3
        boy.park(new Car("SZ124")); //2
        boy.park(new Car("SZ125")); //3
        Ticket ticket = boy.park(new Car("SZ120"));

        assertEquals(ticket.getParkLotName(), "lot1");
        assertEquals(boy.getManagedParkLots().get(0).availableSize(), 0);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2 - 1);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3 - 2);

    }

    @Test
    public void should_unPark_when_valid_ticket_given_to_lot() {
        Ticket ticket = boy.park(new Car("SZ123"));

        Car car = boy.getManagedParkLots().get(2).unPark(ticket);

        assertEquals(car.getName(), "SZ123");
    }

    @Test
    public void should_unPark_when_invalid_ticket_given_to_lot1() {
        Ticket ticket = boy.park(new Car("SZ123"));
        expectedException.expect(InvalidTicketException.class);

        boy.getManagedParkLots().get(0).unPark(ticket);
    }

    @Test
    public void should_unPark_when_invalid_ticket_given_to_lot2() {
        Ticket ticket = boy.park(new Car("SZ123"));
        expectedException.expect(InvalidTicketException.class);

        boy.getManagedParkLots().get(1).unPark(ticket);
    }

    @Test
    public void should_return_print_string() {
        boy.park(new Car("SZ01"));

        Report report = boy.generateReport();

        assertEquals(report.getOwner(), Report.DataOwner.Boy);
        assertEquals(report.getDataList().size(),3);
        assertNull(report.getSlaveReport());
        assertEquals(report.getTotalData().getAvailable(),5);
        assertEquals(report.getTotalData().getCap(),6);
        assertEquals(report.getDataList().get(0).getAvailable(),1);
        assertEquals(report.getDataList().get(0).getCap(),1);

        assertEquals(report.getDataList().get(1).getAvailable(),2);
        assertEquals(report.getDataList().get(1).getCap(),2);
        assertEquals(report.getDataList().get(2).getAvailable(),2);
        assertEquals(report.getDataList().get(2).getCap(),3);
    }


}