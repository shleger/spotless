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

    private BufferedReader bufferedReader;
    private List<Integer[]> matrix;


    public SpotlessCarpet(String dataFile) throws IOException {

        bufferedReader = new BufferedReader(new FileReader(dataFile));
        matrix = new ArrayList<Integer[]>();

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            Integer[] row = new Integer[line.length()];
            for (int i = 0; i < line.length(); i++) {
                row[i] = Integer.valueOf(line.charAt(i));
            }
            matrix.add(row);

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

    public void calcDrops()  {

        System.out.println(matrix);

    }


}
