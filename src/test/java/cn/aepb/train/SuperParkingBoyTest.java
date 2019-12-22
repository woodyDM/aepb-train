package cn.aepb.train;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SuperParkingBoyTest {


    @Mock
    private ParkLot lot1;
    @Mock
    private ParkLot lot2;
    @Mock
    private ParkLot lot3;
    private SuperParkingBoy boy;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        boy = new SuperParkingBoy();
        boy.manage(lot1);
        boy.manage(lot2);
        boy.manage(lot3);
    }


    @Test
    public void should_park_to_max_rate() {
        when(lot1.getRate()).thenReturn(BigDecimal.valueOf(32.32));
        when(lot2.getRate()).thenReturn(BigDecimal.valueOf(16.32));
        when(lot3.getRate()).thenReturn(BigDecimal.valueOf(14.32));
        when(lot1.availableSize()).thenReturn(3);
        when(lot2.availableSize()).thenReturn(3);
        when(lot3.availableSize()).thenReturn(3);
        when(lot1.park(any(Car.class))).thenReturn(new Ticket("t1", "1"));
        when(lot2.park(any(Car.class))).thenReturn(new Ticket("t2", "2"));
        when(lot3.park(any(Car.class))).thenReturn(new Ticket("t3", "3"));

        boy.park(new Car("1"));

        verify(lot1, times(1)).park(any(Car.class));
    }

    @Test
    public void should_park_to_first_when_rate_same() {
        when(lot1.getRate()).thenReturn(BigDecimal.valueOf(12.32));
        when(lot2.getRate()).thenReturn(BigDecimal.valueOf(32.32));
        when(lot3.getRate()).thenReturn(BigDecimal.valueOf(14.32));
        when(lot1.availableSize()).thenReturn(3);
        when(lot2.availableSize()).thenReturn(3);
        when(lot3.availableSize()).thenReturn(3);
        when(lot1.park(any(Car.class))).thenReturn(new Ticket("t1", "1"));
        when(lot2.park(any(Car.class))).thenReturn(new Ticket("t2", "2"));
        when(lot3.park(any(Car.class))).thenReturn(new Ticket("t3", "3"));

        boy.park(new Car("1"));

        verify(lot2, times(1)).park(any(Car.class));
    }

}
