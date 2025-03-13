package org.example;

public class Main {
    public static void main(String[] args) {
        double[] pointA = {0.0, 0.0};
        double[] pointB = {3.0, 4.0};
        double[] pointC = {6.0, 0.0};

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        System.out.println(triangle.getCircumscribedCircleRadius());
    }
}