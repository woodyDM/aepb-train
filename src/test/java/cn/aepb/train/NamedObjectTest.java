package cn.aepb.train;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NamedObjectTest {


    @Test
    public void should_init_with_name() {
        NamedObject namedObject = new NamedObject("name");

        assertEquals(namedObject.getName(), "name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_null_name() {
        new NamedObject(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_given_empty_name() {
        new NamedObject("");
    }

    @Test
    public void should_equal_and_hashCode_same_when_name_is_same() {
        NamedObject obj1 = new NamedObject("namedObject-1");
        NamedObject obj2 = new NamedObject("namedObject-1");

        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

}
