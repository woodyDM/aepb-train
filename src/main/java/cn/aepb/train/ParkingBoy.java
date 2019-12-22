package cn.aepb.train;

import cn.aepb.train.data.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ParkingBoy {

    protected List<ParkLot> parkLotList = new ArrayList<>();

    abstract protected Ticket park(Car car);

    public void manage(ParkLot parkLot) {
        parkLotList.add(parkLot);
    }

    public List<ParkLot> getManagedParkLots() {
        return parkLotList;
    }

    public Car unPark(Ticket ticket) {
        return parkLotList.stream()
                .filter(lot -> lot.isTicketValid(ticket))
                .findFirst()
                .map(lot -> lot.unPark(ticket))
                .orElseThrow(InvalidTicketException::new);

    }

    public Report generateReport() {
        List<Report.Data> data = parkLotList.stream()
                .map(ParkLot::getReportData)
                .collect(Collectors.toList());
        return Report.boyReport(data);
    }

}
