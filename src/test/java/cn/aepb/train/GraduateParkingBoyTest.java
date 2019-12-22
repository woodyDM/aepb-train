package cn.aepb.train;

import cn.aepb.train.data.Report;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraduateParkingBoyTest extends BaseParkingBoyTest {

    @Before
    public void setUp() {
        boy = new GraduateParkingBoy();
        super.init();
    }


    @Test
    public void should_park_car_to_first_lot_when_first_lot_available() {

        Ticket ticket = boy.park(new Car("SZ123"));

        assertEquals(boy.getManagedParkLots().get(0).availableSize(), cap1 - 1);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3);
        assertNotNull(ticket);
        assertEquals(ticket.getParkLotName(), "lot1");
    }

    @Test
    public void should_park_car_to_second_lot_when_first_lot_not_available() {
        boy.park(new Car("SZ1230"));

        Ticket ticket = boy.park(new Car("SZ1233"));

        assertEquals(boy.getManagedParkLots().get(0).availableSize(), 0);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2 - 1);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3);
        assertEquals(ticket.getParkLotName(), "lot2");
    }

    @Test
    public void should_park_car_to_third_lot_when_the_first_two_lot_not_available() {
        boy.park(new Car("SZ1230"));
        boy.park(new Car("SZ1231"));
        boy.park(new Car("SZ1232"));

        Ticket ticket = boy.park(new Car("SZ1233"));

        assertEquals(boy.getManagedParkLots().get(0).availableSize(), 0);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), 0);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3 - 1);
        assertEquals(ticket.getParkLotName(), "lot3");
    }


    @Test
    public void should_unPark_when_use_valid_ticket_to_the_lot() {
        Ticket ticket = boy.park(new Car("NN"));
        ParkLot parkLot = boy.getManagedParkLots().get(0);

        Car car = parkLot.unPark(ticket);

        assertEquals("NN", car.getName());
    }

    @Test
    public void should_not_unPark_when_use_invalid_ticket_to_some_lot() {
        boy.park(new Car("NN1"));

        Ticket ticket = boy.park(new Car("NN"));
        ParkLot parkLot = boy.getManagedParkLots().get(0);
        expectedException.expect(InvalidTicketException.class);

        parkLot.unPark(ticket);
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
        assertEquals(report.getDataList().get(0).getAvailable(),0);
        assertEquals(report.getDataList().get(0).getCap(),1);

        assertEquals(report.getDataList().get(1).getAvailable(),2);
        assertEquals(report.getDataList().get(1).getCap(),2);
        assertEquals(report.getDataList().get(2).getAvailable(),3);
        assertEquals(report.getDataList().get(2).getCap(),3);
    }
}
