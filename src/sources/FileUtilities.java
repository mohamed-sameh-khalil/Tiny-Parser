package sources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtilities {
    //source: https://www.journaldev.com/878/java-write-to-file
    static void writeToFile(String data, String filename){
        File file = new File(filename);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fw.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    //source: https://www.journaldev.com/875/java-read-file-to-string
    static String readFromFile(String filename){
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
