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
                        {0,1,1,0},
                        {0,0,0,1},
                        {0,0,0,1},
                        {0,0,0,0},
        };

        double[][] m9x9 =
                {      // 1 2 3 4 5 6 7 8 9
                  /*1*/  {0,1,0,1,0,0,0,0,0},
                  /*2*/  {0,0,1,0,1,0,0,0,0},
                  /*3*/  {0,0,0,0,0,1,0,0,0},
                  /*4*/  {0,0,0,0,1,0,1,0,0},
                  /*5*/  {0,0,0,0,0,1,0,1,0},
                  /*6*/  {0,0,0,0,0,0,0,0,1},
                  /*7*/  {0,0,0,0,0,0,0,1,0},
                  /*8*/  {0,0,0,0,0,0,0,0,1},
                  /*9*/  {0,0,0,0,0,0,0,0,0},
                };

        Matrix m  = new Matrix(m4x4);

        //TODO rewrite static  buildReachabilityMatrix to dynamic
//        Matrix out = CarpetUtils.buildReachabilityMatrix(m);
//
//
//        System.out.println("4x4: ");
//        CarpetUtils.printMatrix(out);

        System.out.println("9x9: ");

        CarpetUtils.printMatrix(CarpetUtils.buildReachabilityMatrix(new Matrix(m9x9)));
    }



}
