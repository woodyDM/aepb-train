package cn.aepb.train;

import cn.aepb.train.data.ReportPrinter;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingDirectorTest {

    @Test
    public void should_init_with_manager() {
        ParkingManager m1 = mock(ParkingManager.class);
        ParkingManager m2 = mock(ParkingManager.class);
        ParkingManager m3 = mock(ParkingManager.class);
        List<ParkingManager> mList = ImmutableList.of(m1, m2, m3);

        ParkingDirector director = new ParkingDirector(mList);

        assertEquals(director.getParkingManagers().size(),3);
        assertSame(director.getParkingManagers().get(0),m1);
        assertSame(director.getParkingManagers().get(1),m2);
        assertSame(director.getParkingManagers().get(2),m3);
    }

    @Test
    public void should_report() {
        ParkingManager m1 = mock(ParkingManager.class);
        ParkingManager m2 = mock(ParkingManager.class);
        ParkingManager m3 = mock(ParkingManager.class);
        List<ParkingManager> mList = ImmutableList.of(m1, m2, m3);
        when(m1.generateReport()).thenReturn(null);
        when(m2.generateReport()).thenReturn(null);
        when(m3.generateReport()).thenReturn(null);

        ParkingDirector director = new ParkingDirector(mList);
        ReportPrinter printer = mock(ReportPrinter.class);
        when(printer.report(any())).thenReturn("1", "2", "3");


        assertEquals(director.reportVia(printer),"1\n2\n3");
    }


    @Test
    public void should_report_empty() {

        List<ParkingManager> mList = Collections.emptyList();

        ParkingDirector director = new ParkingDirector(mList);
        ReportPrinter printer = new ReportPrinter("", " ", "");

        assertEquals(director.reportVia(printer),"");
    }
}