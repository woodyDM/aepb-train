package cn.aepb.train;

import cn.aepb.train.data.ReportPrinter;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ParkingDirector {

    private List<ParkingManager> parkingManagers;

    public ParkingDirector(List<ParkingManager> parkingManagers) {
        this.parkingManagers = parkingManagers;
    }

    public String reportVia(ReportPrinter printer) {
        if (parkingManagers != null && parkingManagers.size() > 0) {
            return parkingManagers.stream()
                    .map(ParkingManager::generateReport)
                    .map(printer::report)
                    .collect(Collectors.joining("\n"));
        }
        return "";
    }
}
