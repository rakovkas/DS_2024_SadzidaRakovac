package homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static RedBlackTree<Entry> readFile(String filePath) throws FileNotFoundException {

        RedBlackTree<Entry> tree = new RedBlackTree<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(";");

            String name = fields[0];
            String streetAddress = fields[1];
            String city = fields[2];
            String postcode = fields[3];
            String country = fields[4];
            String phoneNumber = fields[5];

            Entry entry = new Entry(name, streetAddress, city, postcode, country, phoneNumber);
            tree.put(name, entry);
        }

        scanner.close();
        return tree;
    }
}

