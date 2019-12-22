package cn.aepb.train;

import cn.aepb.train.data.Report;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParkingManagerTest {


    @Spy
    private ParkLot lot1;
    @Spy
    private ParkLot lot2;
    @Spy
    private ParkLot lot3;
    @Spy
    private ParkLot lot4;
    @Mock
    private SuperParkingBoy boy4;
    @Mock
    private SuperParkingBoy someBoy;
    private ParkingManager manager;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        manager = new ParkingManager();
        manager.manage(boy4);
    }

    @Test
    public void manager_should_manage_boys() {

        assertTrue(manager.getManagedBoys().size() == 1);

    }

    @Test
    public void should_manager_boy_to_park() {

        manager.park(new Car("1"), boy4);

        verify(boy4, times(1)).park(any(Car.class));

    }

    @Test
    public void should_manage_boy_to_unPark() {
        manager.unPark(new Ticket("t", "l1"), boy4);

        verify(boy4, times(1)).unPark(any(Ticket.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_manage_unknown_boy_to_park() {
        manager.park(new Car("1"), someBoy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_manage_unknown_boy_to_unPark() {
        manager.unPark(new Ticket("t", "l1"), someBoy);
    }

    @Test
    public void should_return_report_string() {

        GraduateParkingBoy boy1 = new GraduateParkingBoy();
        SmartParkingBoy boy2 = new SmartParkingBoy();
        ParkingManager manager = new ParkingManager();
        manager.manage(boy1);
        manager.manage(boy2);
        manager.manage(lot4);
        boy1.manage(lot1);
        boy2.manage(lot2);
        boy2.manage(lot3);

        when(lot1.availableSize()).thenReturn(2);
        when(lot2.availableSize()).thenReturn(0);
        when(lot3.availableSize()).thenReturn(1);
        when(lot4.availableSize()).thenReturn(2);
        when(lot1.getCapacity()).thenReturn(5);
        when(lot2.getCapacity()).thenReturn(3);
        when(lot3.getCapacity()).thenReturn(2);
        when(lot4.getCapacity()).thenReturn(10);

        Report report = manager.generateReport();

        assertEquals(report.getOwner(), Report.DataOwner.Manager);
        assertEquals(report.getTotalData().getAvailable(), 5);
        assertEquals(report.getTotalData().getCap(), 20);
        assertEquals(report.getDataList().size(),1);
        assertEquals(report.getDataList().get(0).getAvailable(),2);
        assertEquals(report.getDataList().get(0).getCap(),10);
        assertEquals(report.getSlaveReport().size(),2);

    }
}
