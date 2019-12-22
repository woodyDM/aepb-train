package cn.aepb.train;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy {

    @Override
    public Ticket park(Car car) {
        return parkLotList.stream().filter(lot -> lot.availableSize() > 0)
                .max(Comparator.comparingDouble(lot -> lot.getRate().doubleValue()))
                .map(lot -> lot.park(car))
                .orElseThrow(ParkLotIsFullException::new);
    }

}
