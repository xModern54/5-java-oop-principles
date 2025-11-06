package com.example.task03;

import org.junit.Assert;
import org.junit.Test;

public class Tests {

    private void testTimeUnit(TimeUnit timeUnit, long expectedMillis, long expectedSeconds, long expectedMinutes, long expectedHours) {
        long actualMillis = timeUnit.toMillis();
        long actualSeconds = timeUnit.toSeconds();
        long actualMinutes = timeUnit.toMinutes();
        long actualHours = timeUnit.getHours();
        Assert.assertEquals("Количество миллисекунд не соответствует ожидаемому", expectedMillis, actualMillis);
        Assert.assertEquals("Количество секунд не соответствует ожидаемому", expectedSeconds, actualSeconds);
        Assert.assertEquals("Количество минут не соответствует ожидаемому", expectedMinutes, actualMinutes);
        Assert.assertEquals("Количество часов не соответствует ожидаемому", expectedHours, actualHours);
    }

    @Test
    public void testMilliseconds() {
        testTimeUnit(new Milliseconds(1000), 1000, 1, 0, 0);
    }

    @Test
    public void testMilliseconds2() {
        testTimeUnit(new Milliseconds(29999), 29999, 30, 0, 0);
    }

    @Test
    public void testMilliseconds3() {
        testTimeUnit(new Milliseconds(30000), 30000, 30, 1, 0);
    }

    @Test
    public void testMilliseconds4() {
        testTimeUnit(new Milliseconds(1000000), 1000000, 1000, 17, 0);
    }

    @Test
    public void testSeconds() {
        testTimeUnit(new Seconds(60), 60000, 60, 1, 0);
    }

    @Test
    public void testSeconds2() {
        testTimeUnit(new Seconds(29), 29000, 29, 0, 0);
    }

    @Test
    public void testSeconds3() {
        testTimeUnit(new Seconds(3900), 3_900_000, 3900, 65, 1);
    }

    @Test
    public void testMinutes() {
        testTimeUnit(new Minutes(10), 600000, 600, 10, 0);
    }

    @Test
    public void testMinutes2() {
        testTimeUnit(new Minutes(70), 4_200_000, 4200, 70, 1);
    }

    @Test
    public void testHours() {
        testTimeUnit(new Hours(3), 10_800_000, 10_800, 180, 3);
    }

    @Test
    public void testUtilsSeconds() {
        Seconds seconds = TimeUnitUtils.toSeconds(new Milliseconds(1500));
        testTimeUnit(seconds, 2000, 2, 0, 0);
    }

    @Test
    public void testUtilsSeconds2() {
        Seconds seconds = TimeUnitUtils.toSeconds(new Milliseconds(1499));
        testTimeUnit(seconds, 1000, 1, 0, 0);
    }

    @Test
    public void testUtilsMillis() {
        Milliseconds millis = TimeUnitUtils.toMillis(new Seconds(29));
        testTimeUnit(millis, 29000, 29, 0, 0);
    }

    @Test
    public void testUtilsMillis2() {
        Milliseconds millis = TimeUnitUtils.toMillis(new Seconds(30));
        testTimeUnit(millis, 30000, 30, 1, 0);
    }

    @Test
    public void testUtilsMinutes() {
        Minutes minutes = TimeUnitUtils.toMinutes(new Seconds(3600));
        testTimeUnit(minutes, 3_600_000, 3600, 60, 1);
    }

    @Test
    public void testUtilsHours() {
        Hours hours = TimeUnitUtils.toHours(new Minutes(195));
        testTimeUnit(hours, 10_800_000, 10_800, 180, 3);
    }
}
