package cn.aepb.train;

import org.junit.Test;

public class StringUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_null() {
        StringUtils.ensureNotEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_empty() {
        StringUtils.ensureNotEmpty("");
    }
}