package ru.saa.carpet;

import Jama.Matrix;
import ru.saa.carpet.service.SpotlessCarpet;
import ru.saa.carpet.util.CarpetUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/15/13
 * Time: 01:18 AM
 */
public class Main {

    public static void main(String[] args) throws IOException {


        if (args.length == 0) {
            System.out.println("usage: " + SpotlessCarpet.class.getSimpleName() + " dataFile");
            return;
        }

        SpotlessCarpet carpet = new SpotlessCarpet(args[0]);


        Matrix m =  CarpetUtils.buildReachabilityMatrix(carpet.calcSpots());

        int drops = carpet.calcDrops(m);

        //        CarpetUtils.printMatrix(m);


        System.out.println("Minimum number of drops needed to remove all stains " +
                "from the carpet are: "  + drops );



    }
}
