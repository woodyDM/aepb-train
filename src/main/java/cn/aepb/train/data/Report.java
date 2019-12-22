package cn.aepb.train.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 自身总的数据+自身直接管辖的数据+自身间接管辖的数据
 */
@Getter
public class Report {


    private List<Data> dataList;
    private List<Report> slaveReport;
    private Data totalData;
    private DataOwner owner;

    public static Report boyReport(List<Data> parkLotData) {
        Report report = new Report();
        report.owner = DataOwner.Boy;
        report.dataList = parkLotData;
        report.totalData = summaryData(parkLotData);
        return report;
    }

    private static Data summaryData(List<Data> parkLotData) {
        int available = parkLotData.stream().mapToInt(Data::getAvailable).sum();
        int total = parkLotData.stream().mapToInt(Data::getCap).sum();
        return new Data(available, total);
    }



    public static Report managerReport(List<Data> selfManageData, List<Report> boyReports) {
        Report report = new Report();
        report.owner = DataOwner.Manager;
        report.dataList = selfManageData;
        report.slaveReport = boyReports;
        Data selfData = summaryData(selfManageData);
        Data boyReport = summaryReport(boyReports);
        report.totalData = selfData.plus(boyReport);
        return report;
    }

    private static Data summaryReport(List<Report> list) {
        int available = list.stream().mapToInt(r -> r.totalData.available).sum();
        int total = list.stream().mapToInt(r -> r.totalData.cap).sum();
        return new Data(available, total);
    }


    public enum DataOwner {
        Manager,
        Boy
    }

    @EqualsAndHashCode
    @ToString
    public static class Data {
        private int available;
        private int cap;

        public int getAvailable() {
            return available;
        }

        public int getCap() {
            return cap;
        }

        public Data(int available, int cap) {
            this.available = available;
            this.cap = cap;
        }

        public Data plus(Data data) {
            return new Data(this.available + data.available, this.cap + data.cap);
        }

    }
}
