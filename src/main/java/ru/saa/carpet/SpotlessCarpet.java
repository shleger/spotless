package ru.saa.carpet;

import Jama.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.saa.carpet.service.CarpetService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
     * Map for marked duplicated spots
     */
    private TreeMap<Integer, Integer> dupSpots = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    private TreeSet<Integer> vertices = new TreeSet<Integer>();

    /**
     * Array for founded spots
     */
    private List<List<Integer>> spots = new ArrayList<List<Integer>>();


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

    public Matrix calcSpots() {

        log.info("____calcSpots___________");
        int counter = 0;
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
                            if (v.get(j) != spots.get(i - 1).get(j)) {
                                log.debug("equal spots {}=={} ", v.get(j), spots.get(i - 1).get(j));
                                dupSpots.put(v.get(j), spots.get(i - 1).get(j));
                            }

                        } else {
                            v.add(spots.get(i - 1).get(j));
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
            spots.add(v);
            log.info("{}", v);

        }


        //replace duplicated marked spots
        for (List<Integer> row : spots) {
            for (Map.Entry<Integer, Integer> en : dupSpots.entrySet()) {
                Collections.replaceAll(row, en.getKey(), en.getValue());
            }
            vertices.addAll(row);
            System.out.println(row);
        }

        log.info("vertices={}", vertices);

        //todo saa :  equal spots={1=3, 5=4, 8=5}
        Matrix adjMatrix = new Matrix(vertices.size(), vertices.size());

        int first, last, vertex = 0;

        for (Iterator<Integer> iterator = vertices.iterator(); iterator.hasNext(); ) {
            Integer k = iterator.next();

            for (List<Integer> row : spots) {
                if ((first = row.indexOf(k)) >= 0 | (last = row.lastIndexOf(k)) >= 0) {


                    log.info("k={}, first={}, last ={},", k, first, last);

                    log.info("next={}", row.get(first + 1));
                   adjMatrix.set(vertex, row.get(first + 1), 1);


                }
            }

            vertex++;

            CarpetService.printMatrix(adjMatrix);


        }
        return adjMatrix;
    }

}

