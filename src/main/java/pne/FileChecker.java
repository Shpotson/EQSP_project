package pne;

import org.cactoos.text.TextOf;

import java.io.File;

public class FileChecker {

    public static String GetFileText(String Filepath) throws Exception {
        String text = new TextOf(
                new File("/code/a.txt")
        ).asString();

        return text;
    }
}
