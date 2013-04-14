package ru.saa.carpet.service;

import Jama.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.saa.carpet.util.CarpetUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/14/13
 * Time: 11:06 AM
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


    /**
     * Map for founded  vertices
     */
    private TreeSet<Integer> vertices = new TreeSet<Integer>();

    /**
     * Array for founded spots
     */
    private List<List<Integer>> spots = new ArrayList<List<Integer>>();


    public SpotlessCarpet(String dataFilePath) throws IOException {

        log.info("____SpotfullCarpet______");

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



    public int calcDrops(Matrix m) {

        List<Integer> maxPaths = new ArrayList<Integer>();



        for(double [] rows: m.getArray()){
            int sum = 0;
            for (double  d: rows){
                sum+=d;
            }
            maxPaths.add(sum);
        }

        log.info("sums={}" + maxPaths);

        int maxIndex = maxPaths.indexOf(Collections.max(maxPaths));
        int minIndex = maxPaths.indexOf(Collections.min(maxPaths));

        log.info("max={}, min={}" ,maxIndex,minIndex );


        return maxIndex - minIndex;
    }

    public Matrix calcSpots() {

        log.info("____calcSpots___________");
        markSpots();
        replaceDuplicatedSpots();

        log.info("vertices={}", vertices);
        Matrix adjMatrix = buildAdjustmentMatrix();

        if (log.isDebugEnabled())
            CarpetUtils.printMatrix(adjMatrix);
        return adjMatrix;
    }

    private Matrix buildAdjustmentMatrix() {
        Matrix adjMatrix = new Matrix(vertices.size(), vertices.size());

        int first, vertex = 0;

        List<Integer> verticesList = new ArrayList<Integer>(vertices);


        for (Integer k : verticesList) {
            for (int r = 0; r < spots.size(); r++) {

                log.debug("k={},  row={}", k, spots.get(r));

                List<Integer> jam = getSettedRow(spots.get(r));

                log.debug("gem:{}", jam);

                first = jam.indexOf(k);


                if (first > 0 && jam.size() > first - 1) {
                    adjMatrix.set(vertex, verticesList.indexOf(jam.get(first - 1)), 1);
                }

                if (first >= 0 && jam.size() > first + 1) {
                    adjMatrix.set(vertex, verticesList.indexOf(jam.get(first + 1)), 1);

                }


                if (jam.size() == 1)
                    if (r < spots.size() - 1 && k.equals(jam.get(0))) {
                        List<Integer> jamFul = getSettedRow(spots.get(r + 1));
                        jamFul.remove(k);
                        for (Integer point : jamFul) {
                            adjMatrix.set(vertex, verticesList.indexOf(point), 1);

                        }
                    } else if (r == spots.size() - 1 && k.equals(jam.get(0))) {
                        List<Integer> jamFul = getSettedRow(spots.get(r - 1));
                        jamFul.remove(k);
                        for (Integer point : jamFul) {
                            adjMatrix.set(vertex, verticesList.indexOf(point), 1);

                        }

                    }

            }

            vertex++;


        }
        return adjMatrix;
    }

    private void replaceDuplicatedSpots() {
        log.info("____replacedSpots_______");
        //replace duplicated marked spots
        for (List<Integer> row : spots) {
            for (Map.Entry<Integer, Integer> en : dupSpots.entrySet()) {
                Collections.replaceAll(row, en.getKey(), en.getValue());
            }
            vertices.addAll(row);
            log.info("{}", row);
        }
    }

    private void markSpots() {
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
                    if (integers.get(j)
                            .equals(preIntegers.get(j))) {
                        if (j > 0
                                && integers.get(j)
                                .equals(integers.get(j - 1))) {
                            v.add(v.get(j - 1));
                            if (!v.get(j).equals(spots.get(i - 1).get(j))) {
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
                        && !integers.get(j)
                        .equals(integers.get(j - 1))) {
                    v.add(++counter);
                } else {
                    v.add(v.get(j - 1));
                }


            }
            spots.add(v);
            log.info("{}", v);

        }
    }

    private List<Integer> getSettedRow(List<Integer> row) {
        return new ArrayList<Integer>(new LinkedHashSet<Integer>(row));

    }

}

