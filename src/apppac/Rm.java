package apppac;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

public class Rm extends Commands {

    Search search = new Search();

    void rm(String Path, String name) //name is full command begin with rm
    {
        if ("rm ".compareTo(name.substring(0, 3)) == 0) {
            String str = name.substring(name.indexOf(" ") + 1);
            if (search.search(Path, str)) {
                delete(str, Path);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } else if ("rmdir -r ".compareTo(name.substring(0, 9)) == 0) {
            String str = name.substring(name.indexOf("-") + 3);     //str is name of directory
            if (search.search(Path, str)) {
                remove(str, Path);
                delete(str, Path);

            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } else if ("rmdir ".compareTo(name.substring(0, 6)) == 0) //to delete directory
        {
            String str = name.substring(name.indexOf(" ") + 1);
            if (search.search(Path, str)) {
                delete(str, Path);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Command not found");
        }

    }

    void delete(String str, String Path) //method to delete directiry or file
    {
        File file = new File(Path + "\\" + str);
        if (file.delete()) {
            JOptionPane.showMessageDialog(null, str + " delete successfully");
        } else {
            JOptionPane.showMessageDialog(null, str + " delete unsuccessfully");
        }

    }

    void remove(String str, String Path) {
        String newPath = Path + "//" + str;
        File f = new File(newPath);
        File[] files = f.listFiles();
        if (files.length == 0) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                remove(file.getName(), newPath);
                delete(file.getName(), newPath);
            } else {
                delete(file.getName(), newPath);
            }

        }

    }

    void unzip(String zipFilePath, String destDirectory) throws FileNotFoundException, IOException {
        int BUFFER_SIZE = 4096;
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipIn, filePath);
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
