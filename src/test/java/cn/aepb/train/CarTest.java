package cn.aepb.train;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void should_create_car() {
        Car car = new Car("SZ123456");

        Assert.assertEquals(car.getName(), "SZ123456");
    }


}
