import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/9/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpotlessCarpet {

    private List<List<Integer>> matrix = new ArrayList<List<Integer>>();


    public SpotlessCarpet(String dataFilePath) throws IOException {


        Scanner sc = new Scanner(new BufferedReader(new FileReader(dataFilePath)));


        while (sc.hasNext() && sc.hasNextInt()) {
            String line = sc.nextLine();
            List<Integer> row = new ArrayList<Integer>();

            for (byte b : line.getBytes()) {
                row.add(Integer.parseInt(Character.toString((char) b)));
            }

            matrix.add(row);

            System.out.println(row);
        }
    }

    public static void main(String[] args) throws IOException {


        if (args.length == 0) {
            System.out.println("usage: " + SpotlessCarpet.class.getSimpleName() + " dataFile");
            return;
        }

        SpotlessCarpet carpet = new SpotlessCarpet(args[0]);
        carpet.calcDrops();

    }

    public void calcDrops() {


//        for (Integer[] row : matrix) {
//            for (Integer e : row) {
//                System.out.print(e);
//            }
//            System.out.println();
//        }

    }


}
