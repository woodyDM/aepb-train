package cn.aepb.train;

import cn.aepb.train.data.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParkingManager extends GraduateParkingBoy {


    private List<ParkingBoy> boys = new ArrayList<>();

    public void manage(ParkingBoy boy) {
        boys.add(boy);
    }

    public List<ParkingBoy> getManagedBoys() {
        return boys;
    }

    public Ticket park(Car car, ParkingBoy boy) {
        checkBoy(boy);
        return boy.park(car);
    }

    public Car unPark(Ticket ticket, ParkingBoy boy) {
        checkBoy(boy);
        return boy.unPark(ticket);
    }

    private void checkBoy(ParkingBoy boy) {
        boolean exist = boys.stream().anyMatch(b -> boy == b);
        if (!exist) {
            throw new IllegalArgumentException();
        }
    }

    public Report generateReport() {
        List<Report> boyReports = getManagedBoys().stream()
                .map(ParkingBoy::generateReport)
                .collect(Collectors.toList());

        List<Report.Data> selfManageData =
                parkLotList.stream()
                .map(ParkLot::getReportData)
                .collect(Collectors.toList());

        return  Report.managerReport(selfManageData, boyReports);
    }

}
