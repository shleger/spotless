package ru.saa.carpet.service;

import Jama.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/14/13
 * Time: 23:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarpetService {


    final static Logger log = LoggerFactory.getLogger(CarpetService.class);


    private static NumberFormat nf = NumberFormat.getIntegerInstance();

    public static void printMatrix(Matrix m) {
        log.info("------------------");
        m.print(nf, m.getRowDimension());
        log.info("------------------");
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
