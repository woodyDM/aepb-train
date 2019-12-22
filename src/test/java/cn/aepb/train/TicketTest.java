package cn.aepb.train;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void should_init_with_parkLotName() {
        Ticket ticket = new Ticket("token", "lotName");

        assertEquals(ticket.getParkLotName(), "lotName");
        assertEquals(ticket.getToken(), "token");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_null_lot_name() {
        new Ticket("t", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_empty_lot_name() {
        new Ticket("t", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_null_token() {
        new Ticket(null, "lotName");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_empty_token() {
        new Ticket("", "lotName");
    }
}
