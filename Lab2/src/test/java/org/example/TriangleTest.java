package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TriangleTest {

    double[] pointA = {0.0, 0.0};
    double[] pointB = {3.0, 4.0};
    double[] pointC = {6.0, 0.0};
    Triangle triangle = new Triangle(pointA, pointB, pointC);

    @Test
    public void testGetPerimeter() {
        Assertions.assertEquals(16.0, triangle.getPerimeter(), 0.0001);
    }

    @Test
    public void testGetArea(){
       Assertions.assertEquals(12.0, triangle.getArea(), 0.0001);
    }

    @Test
    public void testGetHeight(){
        Assertions.assertEquals(4.0, triangle.getHeight('B'), 0.0001);
    }

    @Test
    public void testIsEquilateral(){
        Assertions.assertFalse(triangle.isEquilateral());
    }

    @Test
    public void testIsIsosceles(){
        Assertions.assertTrue(triangle.isIsosceles());
    }

    @Test
    public void testIsRightAngled(){
        Assertions.assertFalse(triangle.isRightAngled());
    }

    @Test
    public void testIsValidTriangle(){
        Assertions.assertFalse(Triangle.isValidTriangle(pointA, pointB, pointA));
    }

    @Test
    public void testGetInscribedCircleRadius(){
        Assertions.assertEquals(1.5, triangle.getInscribedCircleRadius(), 0.0001);
    }

    @Test
    public void testGetCircumscribedCircleRadius(){
       Assertions.assertEquals(3.125, triangle.getCircumscribedCircleRadius(), 0.0001);
    }

    @Test
    public void testGetMidpoint(){
        Assertions.assertArrayEquals(new double[]{1.5, 2.0}, triangle.getMidpoint("AB"), 0.0001);
    }



}
