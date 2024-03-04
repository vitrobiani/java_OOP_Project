package javaOOPProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class fileFunctions {
    public static final String dir = System.getProperty("user.dir") + "\\";

    public void saveToFile(String fn, String str) throws FileNotFoundException {
        File testFile = new File(fn);
        PrintWriter pw = new PrintWriter(testFile);
        pw.print(str);
        pw.close();
    }

    public <T> void saveRepository(Repository<T> rep) throws IOException {
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(dir + "Rep.dat"));
        outFile.writeObject(rep);
        outFile.close();
    }

    public Repository<?> loadRepository() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(dir + "Rep.dat"));
        Repository<?> rep = (Repository<?>) inFile.readObject();
        inFile.close();
        return rep;
    }
}
