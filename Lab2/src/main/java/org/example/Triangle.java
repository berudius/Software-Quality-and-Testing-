package org.example;

import java.util.HashMap;
import java.util.Map;


public class Triangle {
    public static final int DIMENSION = 2;// тільки 2D вимір
    final private double[] pointA = {0.0, 0.0};
    final private double[] pointB = {0.0, 0.0};
    final private double[] pointC = {0.0, 0.0};
    final private Map<String, Double> triangleSides = new HashMap<>();

    // 1
    public double getPerimeter(){
        return triangleSides.values().stream().reduce(Double::sum).orElse(0.0);
    }

    // 2. За формулою Герона
    public double getArea(){
        double halfPerimeter = getPerimeter()/2;
        double area = Math.sqrt(
                halfPerimeter
                    * (halfPerimeter - triangleSides.get("AB"))
                    * (halfPerimeter - triangleSides.get("BC"))
                    * (halfPerimeter - triangleSides.get("CA"))
        );
        return area;
    }

    // 3. Визначити висоту трикутника від вершини до протилежної сторони
    public double getHeight(char vertex){
        double height = 0.0;
        double[] pointM = {0,0};
        switch (vertex){
            case 'A':
                return 2 * getArea()/triangleSides.get("BC");
            case 'B':
                return 2 * getArea()/triangleSides.get("CA");
            case 'C':
                return 2 * getArea()/triangleSides.get("AB");
            default:
                throw new IllegalArgumentException("Invalid vertex name: " + vertex + "only A, B, C, is allowed");
        }
    }

    // 4. Рівносторонній?
    public boolean isEquilateral(){
        return triangleSides.get("AB").equals(triangleSides.get("BC"))
               && triangleSides.get("CA").equals(triangleSides.get("AB"));
    }

    // 5. рівнобедрений?
    public boolean isIsosceles(){
        return triangleSides.get("AB").equals(triangleSides.get("BC"))
                || triangleSides.get("AB").equals(triangleSides.get("CA"))
                || triangleSides.get("BC").equals(triangleSides.get("CA"));
    }

    // 6. прямокутний?
    public boolean isRightAngled(){
        String largestSide = triangleSides.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        String[] smallestSides = triangleSides
                .keySet()
                .stream()
                .filter(key -> !key.equals(largestSide))
                .toArray(String[]::new);


        return Math.pow(triangleSides.get(largestSide), 2)
                ==
               Math.pow(triangleSides.get(smallestSides[0]), 2)
                +
               Math.pow(triangleSides.get(smallestSides[1]), 2);
    }



    

    // 7. Перевірити, чи існує такий трикутник за заданими координатами
    //Якщо площа дорівнює нулю, то три точки лежать на одній прямій, і трикутника не існує.
    public static boolean isValidTriangle(double[] pointA, double[] pointB, double[] pointC){
        double area = getArea(pointA, pointB, pointC);
        return area > 0;
    }


    //8.
    public double getInscribedCircleRadius(){
       return getArea()/(getPerimeter()/2);
    }

    //9.
    public double getCircumscribedCircleRadius(){
        return  triangleSides.get("AB")
                * triangleSides.get("BC")
                * triangleSides.get("CA")
            /(4*getArea());
    }

    // 10
    public double[] getMidpoint(String side) {
        double[] midpoint;
        switch (side) {
            case "AB":
                midpoint = getMidpoint(pointA, pointB);
                break;
            case "BA":
                midpoint = getMidpoint(pointA, pointB);
            case "BC":
                midpoint = getMidpoint(pointB, pointC);
                break;
            case "CB":
                midpoint = getMidpoint(pointB, pointC);
                break;
            case "CA":
                midpoint = getMidpoint(pointC, pointA);
                break;
            case "AC":
                midpoint = getMidpoint(pointC, pointA);
                break;
            default:
                throw new IllegalArgumentException("Invalid side name. Choose from 'AB', 'BA', 'BC', 'CB', 'CA', 'AC'.");
        }
        return midpoint;
    }









    public Triangle(double[] pointA, double[] pointB, double[] pointC) {
        if(pointA.length == DIMENSION && pointB.length == DIMENSION && pointC.length == DIMENSION) {
            if(isAnyPointEqualOther(pointA, pointB, pointC)) {
                throw new IllegalArgumentException("All points must be not equal");
            }
            if( ! isValidTriangle(pointA, pointB, pointC)) {
                throw new IllegalArgumentException("All points lie on the same line!");
            }
            this.pointA[0] = pointA[0];
            this.pointA[1] = pointA[1];

            this.pointB[0] = pointB[0];
            this.pointB[1] = pointB[1];

            this.pointC[0] = pointC[0];
            this.pointC[1] = pointC[1];

            intializeTriangleSides();
        }
        else {throw new IllegalArgumentException("All points must be of the 2D dimension");}
    }

    private void intializeTriangleSides() {
        triangleSides.put("AB", getSegmentLength(pointA, pointB));
        triangleSides.put("BC", getSegmentLength(pointB, pointC));
        triangleSides.put("CA", getSegmentLength(pointC, pointA));
    }

    private static boolean isAnyPointEqualOther(double[] pointA, double[] pointB, double[] pointC){
        boolean a = isPointsEqual(pointA, pointB);
        boolean b = isPointsEqual(pointB, pointC);
        boolean c = isPointsEqual(pointC, pointA);

        return a || b || c;
    }
    private static boolean isPointsEqual(double[] point1, double[] point2){
        return point1[0] == point2[0] && point1[1] == point2[1];
    }

    public static double[] getMidpoint(double[] pointA, double[] pointB) {
        if(pointA.length == DIMENSION && pointB.length == DIMENSION) {
            double[] midpoint = new double[2];
            midpoint[0] = (pointA[0] + pointB[0]) / 2;
            midpoint[1] = (pointA[1] + pointB[1]) / 2;
            return midpoint;
        }
        throw new IllegalArgumentException("All points must be of the 2D dimension");
    }
    
    public static double getSegmentLength(double[] pointStart, double[] pointEnd){
        if(pointStart.length == DIMENSION && pointEnd.length == DIMENSION ){

            double blockXSquared = Math.pow(pointEnd[0] - pointStart[0], 2);
            double blockYSquared = Math.pow(pointEnd[1] - pointStart[1], 2);

            return Math.sqrt(blockXSquared + blockYSquared);//Length between 2 points
        }
        throw new IllegalArgumentException("All points must be of the 2D dimension");
    }

    public static double getArea(double[] pointA, double[] pointB, double[] pointC) {
        if(pointA.length == DIMENSION && pointB.length == DIMENSION && pointC.length == DIMENSION) {
            double area = 0.5 * Math.abs(
                    pointA[0] * (pointB[1] - pointC[1])
                            + pointB[0] * (pointC[1] - pointA[1])
                            + pointC[0] * (pointA[1] - pointB[1])
            );

            return area;
        }

        throw new IllegalArgumentException("All points must be the 2D dimension");
    }

}
