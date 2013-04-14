package ru.saa.carpet.service;

import Jama.Matrix;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/14/13
 * Time: 23:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorTest {

    final Logger log = LoggerFactory.getLogger(GeneratorTest.class);

    @Test
    public void normalDistribution() {

        Random r = new Random();

        for (int i = 0; i < 10; i++) {

//        log.info(r.nextInt(10) + " ");
//            log.infoln();
            log.info((int) (10 * r.nextGaussian()) + " "); //TODO saa rm

        }


    }


    @Test
    public void multipleMatrix1() {

//        double[][] mat = {{0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 0, 0}};
//        double[][] mat = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 0, 0}}; //OR
//        double[][] mat = {
//                {0, 1, 0, 0, 0},
//                {1, 0, 1, 0, 0},
//                {0, 1, 0, 1, 0},
//                {0, 0, 1, 0, 1},
//                {0, 0, 0, 1, 0}};  //LINE
        double[][] mat = {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
        };  //LINE2

        Matrix a = new Matrix(mat, mat.length, mat.length);
        Matrix fin;
        printMatrix(a);

        Matrix a2 = a.times(a);
        printMatrix(a2);

        Matrix a3 = a2.times(a);
        printMatrix(a3);

        Matrix a4 = a3.times(a);
        printMatrix(a4);

        Matrix a5 = a4.times(a);
        printMatrix(a5);

        Matrix a6 = a5.times(a);
        printMatrix(a6);


        fin = a.plus(a2).plus(a3).plus(a4).plus(a5).plus(a6);
        printMatrix(fin);

    }

    @Test
    public void multipleMatrixSpoon() {


        double[][] mat = {
                {0, 1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
        };  //SPOON

        Matrix a = new Matrix(mat, mat.length, mat.length);
        Matrix fin;
        printMatrix(a);

        Matrix a2 = a.times(a);
        printMatrix(a2);

        Matrix a3 = a2.times(a);
        printMatrix(a3);

        Matrix a4 = a3.times(a);
        printMatrix(a4);

        Matrix a5 = a4.times(a);
        printMatrix(a5);

        Matrix a6 = a5.times(a);
        printMatrix(a6);

        Matrix a7 = a6.times(a);
        printMatrix(a7);


        fin = a.plus(a2).plus(a3).plus(a4).plus(a5).plus(a6).plus(a7);
        printMatrix(fin);

    }

    @Test
    public void matrixOneToThree() {

        double[][] mat = {{0, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix fin ;
        printMatrix(a);

        Matrix a2 = a.times(a);
        printMatrix(a2);

        Matrix a3 = a2.times(a);
        printMatrix(a3);

        Matrix a4 = a3.times(a);
        printMatrix(a4);


        fin = a.plus(a2).plus(a3).plus(a4);

        printMatrix(fin);

    }

    @Test
    public void matrixMultiplyNotOrGraph() {

        double[][] mat = {{0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix b = new Matrix(mat, 4, 4);
        Matrix fin ;
        printMatrix(a);

        Matrix a2 = a.times(b);
        printMatrix(a2);

        Matrix a3 = a2.times(b);
        printMatrix(a3);

        Matrix a4 = a3.times(b);
        printMatrix(a4);


        fin = a.plus(a2).plus(a3).plus(a4);

        printMatrix(fin);

    }

    @Test
    public void matrixMultiplyOrGraph() {
        log.info("start");

        double[][] mat = {{0, 1, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {0, 0, 1, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix b = new Matrix(mat, 4, 4);
        Matrix fin;
        printMatrix(a);

        Matrix a2 = a.times(b);
        printMatrix(a2);

        Matrix a3 = a2.times(b);
        printMatrix(a3);

        Matrix a4 = a3.times(b);
        printMatrix(a4);


        fin = a.plus(a2).plus(a3).plus(a4);

        printMatrix(fin);

        fin = buildReachabilityMatrix(a);

        printMatrix(fin);

    }

    private static NumberFormat nf = NumberFormat.getIntegerInstance();

    private void printMatrix(Matrix m) {
        System.out.println("------------------");
        m.print(nf, m.getRowDimension());
        System.out.println("------------------");
    }

    private int exp = 0;
    private Matrix mul = null;
    private Matrix sum = null;
    private Matrix multiplier = null;

    public Matrix buildReachabilityMatrix(Matrix m) {

        if (exp == 0) {
            sum = multiplier = m;
        }

//        printMatrix(m); //debug print
        if (exp != m.getRowDimension() - 1) {
            exp++;
            mul = m.times(multiplier);
            sum = sum.plus(mul);
            buildReachabilityMatrix(mul);
        }
        return sum;

    }

}
