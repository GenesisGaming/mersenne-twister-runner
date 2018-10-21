import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.math3.random.MersenneTwister;

public class MersenneTwisterRunner {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            showUsage();
            return;
        }
        String arg1 = args[0];
        if (!isNumeric(arg1)) {
            System.err.println("\n"+arg1 + " is not a nubmer, please input number only\n\n");
            showUsage();
            return;
        }
        String arg2 = args[1];
        if (!isNumeric(arg2)) {
            System.err.println("\n"+arg2 + " is not a nubmer, please input number only\n\n");
            showUsage();
            return;
        }
        int max = Integer.parseInt(arg1) + 1;
        int loop = Integer.parseInt(arg2);
        if (loop <1 || loop > 10_000_000) {
            System.err.println("\nfor count, please input number from 1 and 10000000\n\n");
            showUsage();
            return;
        }
        
        String filename = args[2];
        FileWriter out = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(out);
        MersenneTwister rng = new MersenneTwister();
        for (int i = 0; i < loop; i++) {
            int random = rng.nextInt(max);
            writer.append(Integer.toString(random));
            writer.append("\n");
        }
        writer.close();
        System.out.println("data written to " + filename);
    }

    public static void showUsage() {
        System.out.println("Usage:\n\t$ java -jar {pathToJar} {maxValue} {count} {filename}");
        System.out.println("\n\t\t-pathToJar: location of the mersenne-twister-runner.jar");
        System.out.println("\n\t\t-maxValue: maximum value of the random number to be generated");
        System.out.println("\n\t\t-count: defines how many times a random number will be generated");
        System.out.println("\n\t\t-filename: file where the numbers generated will appended");
        System.out.println("\nexample:");
        System.out.println("java -jar target/mersenne-twister-runner.jar 100 100 ~/random.txt");
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

}