package files;

import java.io.*;

/**
 * Handles writing to file and reading from file.
 *
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public class FileManager {
    /**
     * Creates file and writes text to it if it doesn't exist.
     *
     * @param name Name of the file.
     * @param text Text to be written into file.
     */
    public static void create_file(String name, String text) {
        try {
            File file = new File(name);
            if(file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);
                bufferedWriter.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Opens file and writes text to it.
     * This method overrides original text.
     *
     * @param path Path to file.
     * @param text Text to be written into file.
     */
    public static void set(String path, String text) {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads line from the file and returns it as a String.
     *
     * @param path Path to the file.
     * @return Line of text from the file.
     */
    public static String get_line(String path) {
        String text;
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            text = bufferedReader.readLine();
            bufferedReader.close();
        } catch(IOException e) {
            text = null;
            e.printStackTrace();
            System.exit(1);
        }

        return text;
    }

    public static int get_integer(String path) {
        String text = get_line(path);
        return Integer.parseInt(text);
    }
}
