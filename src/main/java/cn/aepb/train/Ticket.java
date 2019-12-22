package cn.aepb.train;

import java.util.Objects;

public class Ticket {

    private String token;
    private String parkLotName;

    public Ticket(String token, String lotName) {
        StringUtils.ensureNotEmpty(token);
        StringUtils.ensureNotEmpty(lotName);
        this.token = token;
        this.parkLotName = lotName;
    }

    public String getParkLotName() {
        return parkLotName;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return parkLotName.equals(ticket.parkLotName) &&
                token.equals(ticket.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkLotName, token);
    }
}
