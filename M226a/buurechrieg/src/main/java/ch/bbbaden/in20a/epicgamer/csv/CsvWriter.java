package ch.bbbaden.in20a.epicgamer.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public static final String CSV_PATH = "C:\\Users\\nsd\\Documents\\Schule\\Informatik\\Modul 226a\\epic gamer\\src\\main\\resources\\output.csv";

    private File m_file;
    private BufferedWriter m_fileWriter;

    public CsvWriter(){
        try {
            m_fileWriter = new BufferedWriter(new FileWriter(CSV_PATH));
            m_fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line){
        try {
            m_fileWriter.append(line + "\n");
            m_fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
