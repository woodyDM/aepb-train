package cn.aepb.train.data;

import com.google.common.base.Strings;


/**
 * 每一行定义为：
 * linePrefix + tabString * [0-9] + gapString + [M/B/P] + " " + available + " " + capacity + "\n"
 */
public class ReportPrinter {


    private String linePrefix;  //每一行第一个字符
    private String tabString;   //缩进字符
    private String gapString;   //缩进字符和数据字符间的字符

    public ReportPrinter(String linePrefix, String tabString, String gapString) {
        this.linePrefix = linePrefix;
        this.tabString = tabString;
        this.gapString = gapString;
    }

    public String report(Report report) {
        StringBuilder sb = new StringBuilder();
        report(report, 0, sb);
        return sb.toString();
    }

    private void report(Report report, int depth, StringBuilder sb) {
        appendHeader(depth, sb);
        sb.append(convertOwner(report.getOwner()));
        appendReportData(report.getTotalData(), sb);
        if (report.getDataList() != null) {
            report.getDataList().forEach(data -> {
                appendHeader(depth + 1, sb);
                sb.append("P");
                appendReportData(data, sb);
            });
        }
        if (report.getSlaveReport() != null) {
            report.getSlaveReport().forEach(slaveReport ->
                    report(slaveReport, depth + 1, sb));
        }
    }

    private void appendHeader(int depth, StringBuilder sb) {
        sb.append(linePrefix)
                .append(getHeader(depth));
    }

    private String convertOwner(Report.DataOwner dataOwner) {
        switch (dataOwner) {
            case Boy:
                return "B";
            case Manager:
                return "M";
            default:
                throw new IllegalStateException("won't happen");
        }
    }

    private void appendReportData(Report.Data data, StringBuilder sb) {
        sb.append(" ")
                .append(data.getAvailable())
                .append(" ")
                .append(data.getCap())
                .append("\n");
    }

    private String getHeader(int depth) {
        return Strings.repeat(tabString, depth) + gapString;

    }
}
