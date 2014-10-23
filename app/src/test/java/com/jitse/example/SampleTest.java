package com.jitse.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by jitse on 10/22/14.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SampleTest {

    @Test
    public void test1() {
        assertEquals("test", "test");
    }

}
