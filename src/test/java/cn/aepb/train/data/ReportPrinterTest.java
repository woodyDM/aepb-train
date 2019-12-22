package cn.aepb.train.data;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportPrinterTest {

    private Report report;
    private ReportPrinter markdownPrinter;
    private ReportPrinter simplePrinter;


    @Before
    public void setUp() throws Exception {
        Report.Data d1 = new Report.Data(2, 5);
        List<Report.Data> data = ImmutableList.of(d1);
        Report reportB1 = Report.boyReport(data);

        Report.Data d21 = new Report.Data(0, 3);
        Report.Data d22 = new Report.Data(1, 2);
        List<Report.Data> data2 = ImmutableList.of(d21, d22);
        Report reportB2 = Report.boyReport(data2);

        List<Report> boyReports = ImmutableList.of(reportB1, reportB2);
        Report.Data d32 = new Report.Data(2, 10);
        List<Report.Data> selfManagedData = ImmutableList.of(d32);

        report = Report.managerReport(selfManagedData, boyReports);
        markdownPrinter = new ReportPrinter("#", "#", " ");
        simplePrinter = new ReportPrinter("", " ", "");

    }

    @Test
    public void should_report_manager_with_space() {
        //when
        String result = simplePrinter.report(report);
        //then
        assertEquals(result, "M 5 20\n" +
                " P 2 10\n" +
                " B 2 5\n" +
                "  P 2 5\n" +
                " B 1 5\n" +
                "  P 0 3\n" +
                "  P 1 2\n");
    }

    @Test
    public void should_report_manager_with_markdown() {
        //when
        String result = markdownPrinter.report(report);
        //then
        assertEquals(result, "# M 5 20\n" +
                "## P 2 10\n" +
                "## B 2 5\n" +
                "### P 2 5\n" +
                "## B 1 5\n" +
                "### P 0 3\n" +
                "### P 1 2\n");
    }

    @Test
    public void should_report_boy_with_markdown() {
        Report.Data d21 = new Report.Data(0, 3);
        Report.Data d22 = new Report.Data(1, 2);
        List<Report.Data> data2 = ImmutableList.of(d21, d22);
        Report report = Report.boyReport(data2);
        //when
        String result = markdownPrinter.report(report);
        //then
        assertEquals(result, "# B 1 5\n" +
                "## P 0 3\n" +
                "## P 1 2\n");
    }
}