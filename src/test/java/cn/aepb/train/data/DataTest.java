package cn.aepb.train.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataTest {

    @Test
    public void should_init_with_param() {
        Report.Data data = new Report.Data(2, 5);

        assertEquals(data.getAvailable(), 2);
        assertEquals(data.getCap(), 5);
    }

    @Test
    public void should_plus() {
        Report.Data data1 = new Report.Data(2, 5);
        Report.Data data2 = new Report.Data(6, 15);

        Report.Data result = data1.plus(data2);

        assertEquals(result.getAvailable(), 2 + 6);
        assertEquals(result.getCap(), 5 + 15);

    }
}