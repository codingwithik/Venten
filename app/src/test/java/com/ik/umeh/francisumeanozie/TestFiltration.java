package com.ik.umeh.francisumeanozie;
import com.ik.umeh.francisumeanozie.models.Car;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestFiltration {

    @Test
    public void check_filtration(){

        Car car = new Car();

        // ...then the result should be the expected one.
        assertNotNull(car);

    }
}
