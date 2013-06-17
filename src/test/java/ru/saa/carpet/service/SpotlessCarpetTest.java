package ru.saa.carpet.service;

import Jama.Matrix;
import junit.framework.Assert;
import org.junit.Test;
import ru.saa.carpet.util.CarpetUtils;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/14/13
 * Time: 10:02 AM
 */
public class SpotlessCarpetTest {


    @Test
    public void matrixMultiplyNotOrGraph() {

        double[][] mat = {{0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};

        Matrix a = new Matrix(mat, 4, 4);
        Matrix b = new Matrix(mat, 4, 4);
        Matrix fin;
        CarpetUtils.printMatrix(a);

        Matrix a2 = a.times(b);
        CarpetUtils.printMatrix(a2);

        Matrix a3 = a2.times(b);
        CarpetUtils.printMatrix(a3);

        Matrix a4 = a3.times(b);
        CarpetUtils.printMatrix(a4);


        fin = a.plus(a2).plus(a3).plus(a4);

        CarpetUtils.printMatrix(fin);

        Matrix m = CarpetUtils.buildReachabilityMatrix(a);

//        CarpetUtils.printMatrix(m);

        double[][] finArr = fin.getArray();
        double[][] mArr = m.getArray();

        for (int i = 0; i < mArr.length; i++) {
            double[] d1 = finArr[i];
            double[] d2 = mArr[i];

            for (int j = 0; j < d2.length; j++) {
                Assert.assertEquals(d1[j], d1[j]);
            }
        }
    }


    @Test
    public void problem1() {
        int sum = 0;

        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }

        }

        System.out.println(sum);
    }


    @Test
    public void problem15() {


        double[][] m4x4 =
                {
                        {0, 1, 1, 0},
                        {0, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 0},
                };

        double[][] m9x9 =
                {      // 1 2 3 4 5 6 7 8 9
                  /*1*/  {0, 1, 0, 1, 0, 0, 0, 0, 0},
                  /*2*/  {0, 0, 1, 0, 1, 0, 0, 0, 0},
                  /*3*/  {0, 0, 0, 0, 0, 1, 0, 0, 0},
                  /*4*/  {0, 0, 0, 0, 1, 0, 1, 0, 0},
                  /*5*/  {0, 0, 0, 0, 0, 1, 0, 1, 0},
                  /*6*/  {0, 0, 0, 0, 0, 0, 0, 0, 1},
                  /*7*/  {0, 0, 0, 0, 0, 0, 0, 1, 0},
                  /*8*/  {0, 0, 0, 0, 0, 0, 0, 0, 1},
                  /*9*/  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                };

        Matrix m = new Matrix(m4x4);

        //TODO rewrite static  buildReachabilityMatrix to dynamic
//        Matrix out = CarpetUtils.buildReachabilityMatrix(m);
//
//
//        System.out.println("4x4: ");
//        CarpetUtils.printMatrix(out);


        // CarpetUtils.printMatrix(CarpetUtils.buildReachabilityMatrix(new Matrix(m9x9)));

        int mSize = 3;
        System.out.println(mSize + "x" + mSize + ": ");

        int counter = 0;
        int[][] arr = new int[mSize][mSize];

        for (int i = 0; i < mSize; i++)
            for (int j = 0; j < mSize; j++) {
                arr[i][j] = counter++;

            }


        int doubleSize = mSize * mSize;

        double[][] mArr = new double[doubleSize][doubleSize];


        for (int i = 0; i < mSize; i++)
            for (int j = 0; j < mSize; j++) {

                if (j > 0)
                    mArr[arr[i][j-1]][  arr[i][j]  ] = 1;
                if(i>0)
                    mArr[arr[i-1][j]][  arr[i][j]  ] = 1;


            }


//        CarpetUtils.printMatrix(new Matrix(mArr));

//        CarpetUtils.printMatrix(CarpetUtils.buildReachabilityMatrix(new Matrix(mArr)));

        Matrix mMatrix  = CarpetUtils.buildReachabilityMatrix(new Matrix(mArr));

        double[][] arrAnswer =   mMatrix.getArray();

        System.out.println("All Paths= " + arrAnswer[0][doubleSize-1]);



    }


}
