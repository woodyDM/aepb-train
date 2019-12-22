package cn.aepb.train;

public class GraduateParkingBoy extends ParkingBoy {


    public Ticket park(Car car) {
        return parkLotList.stream()
                .filter(lot -> lot.availableSize() > 0)
                .findFirst()
                .map(lot -> lot.park(car))
                .orElseThrow(ParkLotIsFullException::new);
    }


}
