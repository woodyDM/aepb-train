package cn.aepb.train;

import cn.aepb.train.data.Report;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public abstract class BaseParkingBoyTest {

    protected ParkingBoy boy;
    protected ParkLot parkLot1;
    protected ParkLot parkLot2;
    protected ParkLot parkLot3;
    protected int cap1 = 1;
    protected int cap2 = 2;
    protected int cap3 = 3;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    public void init() {
        parkLot1 = new ParkLot(cap1, "lot1");
        parkLot2 = new ParkLot(cap2, "lot2");
        parkLot3 = new ParkLot(cap3, "lot3");

        boy.manage(parkLot1);
        boy.manage(parkLot2);
        boy.manage(parkLot3);
    }


    @Test
    public void should_manage_parkLots() {

        assertEquals(boy.getManagedParkLots().size(), 3);

    }

    @Test
    public void should_not_park_when_no_lot_available() {
        //given
        boy.park(new Car("SZ1230"));

        boy.park(new Car("SZ1231"));
        boy.park(new Car("SZ1232"));

        boy.park(new Car("SZ1233"));
        boy.park(new Car("SZ1234"));
        boy.park(new Car("SZ1235"));

        expectedException.expect(ParkLotIsFullException.class);
        //when
        boy.park(new Car("SZ1236"));

    }

    @Test
    public void should_unPark_when_ticket_valid() {
        Ticket ticket = boy.park(new Car("SZ2-9"));

        Car car = boy.unPark(ticket);

        assertEquals(boy.getManagedParkLots().get(0).availableSize(), cap1);
        assertEquals(boy.getManagedParkLots().get(1).availableSize(), cap2);
        assertEquals(boy.getManagedParkLots().get(2).availableSize(), cap3);
        assertNotNull(car);
        assertEquals(car.getName(), "SZ2-9");
    }


    @Test
    public void should_not_unPark_when_ticket_is_not_valid() {
        boy.park(new Car("SZ01"));
        boy.park(new Car("SZ02"));
        boy.park(new Car("SZ03"));
        boy.park(new Car("SZ04"));
        expectedException.expect(InvalidTicketException.class);

        boy.unPark(new Ticket("invalid-token", "lot1"));
    }





}
