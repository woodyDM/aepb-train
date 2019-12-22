package cn.aepb.train;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket park(Car car) {
        return parkLotList.stream()
                .filter(lot -> lot.availableSize() > 0)
                .max(Comparator.comparingInt(ParkLot::availableSize))
                .map(lot -> lot.park(car))
                .orElseThrow(ParkLotIsFullException::new);
    }


}
