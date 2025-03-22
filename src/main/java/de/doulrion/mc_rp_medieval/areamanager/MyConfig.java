package de.doulrion.mc_rp_medieval.areamanager;

import de.doulrion.mc_rp_medieval.McRpMedieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyConfig {

    // Don't know if this is right, will try it out
    private static String path = "config\\" + McRpMedieval.MOD_ID + "_config.txt";

    public MyConfig() {
        File file = new File(path);
        McRpMedieval.LOGGER.info(file.getAbsolutePath());
        try {
            file.createNewFile();
        } catch (IOException e) {
        }

    }

    public List<String> read(){
        List<String> content = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                content.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            content.add("ERROR");
            content.add("Error while reading data from: " + path);
        }
        return content;
    }

    public void write(List<String> list){
        try {
            StringBuilder content = new StringBuilder();
            for (String s : list){
                content.append(s).append("\n");
            }
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(content.toString());
            myWriter.close();
        } catch (IOException e) {
        }
    }
}

