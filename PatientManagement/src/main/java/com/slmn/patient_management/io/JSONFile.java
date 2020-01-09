package com.slmn.patient_management.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class JSONFile {
    private String filename;

    private String getFilename() {
        return String.format("data/%s", this.filename);
    }

    public JSONFile(String filename) {
        this.filename = filename;
    }

    public String read() throws FileNotFoundException {
        String fileContent = (new Scanner(new File(this.getFilename())).useDelimiter("\n")).next();
        return fileContent;
    }

    public String readOrCreate(String defaultText) {
        File file = new File(this.getFilename());
        if (!file.isFile()) {
            // return early
            try {
                System.out.println("Creating a new file at " + file.getAbsolutePath());

                file.createNewFile();
                PrintWriter out = new PrintWriter(file.getAbsolutePath());
                out.println(defaultText);
                out.close();

                System.out.println(String.format("Default text: %s", defaultText));
            } catch (IOException e) {
                System.out.println("New file couldn't be created - returning default text");
                e.printStackTrace();
            }

            return defaultText;
        }

        // handle normally
        try {
            return this.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File couldn't be read - returning default text");
            return defaultText;
        }
    }

    public void write(String content) {
        /* Don't need to write or create - the file will be created when the program starts if it's missing.
           Technically, I should do another check here (for if a file is changed during program run)
           But it should be fine
        * */

        File file = new File(this.getFilename());
        try {
            PrintWriter out = new PrintWriter(file.getAbsolutePath());
            // out.println will overwrite the full file

            System.out.println(String.format("Replacing file [%s] with %s", this.getFilename(), content));
            out.println(content);
            out.close();

        } catch (FileNotFoundException e) {
            // unlikely to ever happen
            e.printStackTrace();
        }
    }
}
