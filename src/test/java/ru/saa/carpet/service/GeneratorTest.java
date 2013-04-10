package ru.saa.carpet.service;

import Jama.Matrix;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/4/13
 * Time: 23:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorTest {


    @Test
    public void normalDistribution() {

        Random r = new Random();

        for (int i = 0; i < 10; i++) {

//        System.out.print(r.nextInt(10) + " ");
//            System.out.println();
            System.out.print((int) (10 * r.nextGaussian()) + " "); //TODO saa rm

        }


    }


    @Test
    public void multipleMatrix1() {

        double[][] mat = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 0, 0}};

        Matrix a = new Matrix(mat, 5, 5);
        Matrix fin = null;
        printMatrix(a);

        Matrix a2 = a.times(a);
        printMatrix(a2);

        Matrix a3 = a2.times(a);
        printMatrix(a3);

        Matrix a4 = a3.times(a);
        printMatrix(a4);

        Matrix a5 = a4.times(a);
        printMatrix(a5);


        fin = a.plus(a2).plus(a3).plus(a4).plus(a5);



        printMatrix(fin);

    }

    @Test
    public void matrixOneToThree() {

        double[][] mat = {{0, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix fin = new Matrix(4, 4);
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
        Matrix fin = new Matrix(4, 4);
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

        double[][] mat = {{0, 1, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {0, 0, 1, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix b = new Matrix(mat, 4, 4);
        Matrix fin = new Matrix(4, 4);
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

   private static NumberFormat nf = NumberFormat.getIntegerInstance();
    private void printMatrix(Matrix m) {
        System.out.println("------------------");
        m.print(nf,m.getRowDimension());
        System.out.println("------------------");
    }
}
