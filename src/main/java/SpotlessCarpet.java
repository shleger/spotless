/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/9/13
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpotlessCarpet {


    public SpotlessCarpet(String dataFile){

    }

    public static void main(String[] args) {


        if (args.length == 0){
            System.out.println("usage: " + SpotlessCarpet.class.getSimpleName() + " dataFile");
            return;
        }


        SpotlessCarpet carpet = new SpotlessCarpet(args[0]);
        carpet.calcDrops();

    }

    public void calcDrops() {
    }


}
