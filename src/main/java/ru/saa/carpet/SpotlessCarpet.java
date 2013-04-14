package ru.saa.carpet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/14/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpotlessCarpet {

    Logger log = LoggerFactory.getLogger(SpotlessCarpet.class);

    /**
     * Array for init data
     */
    private List<List<Integer>> matrix = new ArrayList<List<Integer>>();

    /**
     * Array for founded vertices
     */
    private List<List<Integer>> vertices = new ArrayList<List<Integer>>();


    public SpotlessCarpet(String dataFilePath) throws IOException {

        log.info("____SpotlessCarpet______");

        Scanner sc = new Scanner(new BufferedReader(new FileReader(dataFilePath)));


        while (sc.hasNext() && sc.hasNextInt()) {
            String line = sc.nextLine();
            List<Integer> row = new ArrayList<Integer>();

            for (byte b : line.getBytes()) {
                row.add(Integer.parseInt(Character.toString((char) b)));
            }

            matrix.add(row);

            log.info("{}", row);
        }
    }

    public static void main(String[] args) throws IOException {


        if (args.length == 0) {
            System.out.println("usage: " + SpotlessCarpet.class.getSimpleName() + " dataFile");
            return;
        }

        SpotlessCarpet carpet = new SpotlessCarpet(args[0]);
        carpet.calcSpots();

    }

    public Map<Integer, Integer> calcSpots() {

        log.info("____calcSpots___________");
        int counter = 1;
        List<Integer> preIntegers = null;

        for (int i = 0; i < matrix.size(); i++) {

            List<Integer> integers = matrix.get(i);

            if (i > 0)
                preIntegers = matrix.get(i - 1);

            List<Integer> v = new ArrayList<Integer>();

            for (int j = 0; j < integers.size(); j++) {

                // assign first value
                if (j == 0 && i == 0) {
                    v.add(counter);
                    continue;
                }

                //compare if we have previous rows then  compare  values
                if (i > 0)
                    if (integers.get(j) == preIntegers.get(j)) {
                        if (j > 0
                                && integers.get(j)
                                == integers.get(j - 1)) {
                            v.add(v.get(j - 1));
                            if (v.get(j) != vertices.get(i - 1).get(j)) {

                                System.out.println("recon: " + vertices.get(i - 1).get(j) + ", conn: " + v.get(j));
                            }

                        } else {
                            v.add(vertices.get(i - 1).get(j));
                        }
                        continue;
                    }

                if (j == 0)
                    v.add(++counter);
                else if (j > 0
                        && integers.get(j)
                        != integers.get(j - 1)) {
                    v.add(++counter);
                    continue;
                } else {
                    v.add(v.get(j - 1));
                }


            }
            vertices.add(v);

            log.info("{}", v);

        }


        return null; //TODO saa fill

    }
}
