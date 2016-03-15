package com.bit.learning.java.designpatterns.strategy;

import java.io.*;

/**
 * Created by yuzt on 16-3-15.
 */
public class FileStorageService implements IStorageService {

    private static final String STORAGE_FILE_PATH = "/home/yuzt/storage.txt";


    public void save(String data) {
        PrintWriter writer = null;
        try {
            File storageFile = new File(STORAGE_FILE_PATH);
            if (storageFile.exists()) {
                storageFile.delete();
            }
            storageFile.createNewFile();

            writer = new PrintWriter(new FileWriter(storageFile));
            writer.write(data);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public String read() {
        BufferedReader reader = null;
        try {
            File storageFile = new File(STORAGE_FILE_PATH);
            if (!storageFile.exists()) {
                throw new FileNotFoundException("file is not found");
            }

            reader = new BufferedReader(new FileReader(storageFile));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append(System.lineSeparator());
                }
                sb.append(line);
            }
            return sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
