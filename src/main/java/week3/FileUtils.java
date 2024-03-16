package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static IPAddress[] readFile(String filePath) {
        //read all lines from the file and return an array containing the IPAddress object
        try{
            BufferedReader reader = new BufferedReader( new FileReader(filePath));
            List<String> lines = reader.lines().toList();

            IPAddress[] ipAddressObjects = new IPAddress[lines.size()];

            for (int i = 0; i<lines.size(); i++){
                String[] splitedLine = lines.get(i).split(";");

                IPAddress temp = new IPAddress(
                        Long.parseLong(splitedLine[0]),
                        Long.parseLong(splitedLine[1]),
                        splitedLine[2],
                        splitedLine[3],
                        splitedLine[4],
                        splitedLine[5]
                );

                ipAddressObjects[i] = temp;
            }

            reader.close();
            return ipAddressObjects;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
