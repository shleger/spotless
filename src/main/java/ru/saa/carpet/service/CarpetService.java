package ru.saa.carpet.service;

import Jama.Matrix;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/4/13
 * Time: 23:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarpetService {




    private static NumberFormat nf = NumberFormat.getIntegerInstance();

    public void printMatrix(Matrix m) {
        System.out.println("------------------");
        m.print(nf, m.getRowDimension());
        System.out.println("------------------");
    }

    private int exp = 0;
    Matrix mul = null;
    Matrix sum = null;
    Matrix multiplier = null;

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
