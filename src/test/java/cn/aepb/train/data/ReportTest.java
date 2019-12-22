package cn.aepb.train.data;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReportTest {


    @Test
    public void should_generate_boy_report() {
        Report.Data d1 = new Report.Data(2, 4);
        Report.Data d2 = new Report.Data(5, 14);
        Report.Data d3 = new Report.Data(7, 10);
        List<Report.Data> data = ImmutableList.of(d1, d2, d3);

        Report report = Report.boyReport(data);

        assertEquals(report.getOwner(), Report.DataOwner.Boy);
        assertNull(report.getSlaveReport());
        assertEquals(report.getTotalData().getAvailable(), 2 + 5 + 7);
        assertEquals(report.getTotalData().getCap(), 4 + 14 + 10);
        assertEquals(report.getDataList().size(), data.size());
        for (int i = 0; i < report.getDataList().size(); i++) {
            assertEquals(data.get(i), report.getDataList().get(i));
        }
    }

    @Test
    public void should_generate_manager_report() {
        Report.Data d1 = new Report.Data(2, 4);
        Report.Data d2 = new Report.Data(5, 14);
        Report.Data d3 = new Report.Data(7, 10);
        List<Report.Data> data = ImmutableList.of(d1, d2, d3);
        Report reportb1 = Report.boyReport(data);

        Report.Data d21 = new Report.Data(2, 3);
        Report.Data d22 = new Report.Data(5, 10);
        List<Report.Data> data2 = ImmutableList.of(d21, d22);
        Report reportb2 = Report.boyReport(data2);

        List<Report> boyReports = ImmutableList.of(reportb1, reportb2);
        Report.Data d31 = new Report.Data(1, 4);
        Report.Data d32 = new Report.Data(2, 10);
        List<Report.Data> selfManagedData = ImmutableList.of(d31, d32);

        Report report = Report.managerReport(selfManagedData, boyReports);

        assertEquals(report.getOwner(), Report.DataOwner.Manager);
        assertSame(report.getSlaveReport(), boyReports);
        assertEquals(report.getTotalData().getAvailable(), 24);
        assertEquals(report.getTotalData().getCap(), 55);
        assertEquals(report.getDataList().size(), selfManagedData.size());
        for (int i = 0; i < report.getDataList().size(); i++) {
            assertEquals(selfManagedData.get(i), report.getDataList().get(i));
        }
    }
}