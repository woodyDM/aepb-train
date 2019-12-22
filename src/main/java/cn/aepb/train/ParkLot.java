package cn.aepb.train;

import cn.aepb.train.data.Report;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
public class ParkLot extends NamedObject {

    private int capacity;
    private Map<Ticket, Car> carMap = new HashMap<>();

    public ParkLot(int capacity, String name) {
        super(name);
        this.capacity = capacity;
    }


    public int getCapacity() {
        return capacity;
    }


    public int availableSize() {
        return this.capacity - this.carMap.size();
    }


    public synchronized Ticket park(Car car) {
        if (this.availableSize() > 0) {
            String token = UUID.randomUUID().toString();
            Ticket ticket = new Ticket(token, name);
            carMap.put(ticket, car);
            return ticket;
        }
        throw new ParkLotIsFullException();
    }

    public synchronized Car unPark(Ticket ticket) {
        Car car = carMap.get(ticket);
        if (car != null) {
            carMap.remove(ticket);
            return car;
        } else {
            throw new InvalidTicketException();
        }
    }

    public boolean isTicketValid(Ticket ticket) {
        return carMap.containsKey(ticket);
    }


    public BigDecimal getRate() {
        double rate = availableSize() * 100.0 / capacity;
        BigDecimal result = BigDecimal.valueOf(rate);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }


    public Report.Data getReportData() {
        return new Report.Data(availableSize(), getCapacity());
    }
}
