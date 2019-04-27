package apppac;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author TARUN
 */
public class Zip {

    Rm rm = new Rm();

    void Unzip(String s, String path) throws IOException {
        String name = s.substring(s.lastIndexOf(" ") + 1);
        if (!name.contains("\\")) {
            String Path = path;
        
            path += "\\";
            path += name;
            rm.unzip(path, Path);
        } else {
            String Name =path+"\\"+ s.substring(6, s.lastIndexOf(" "));
           name=path+"\\"+ s.substring(6, s.lastIndexOf("."));

                rm.unzip(Name, name);
            

        }
    }

    void move(String s, String path) //path is current path
    {
        String name = s.substring(s.lastIndexOf(" ") + 1);
        System.out.println("name  " + name);
        if (name.contains("\\")) {
            path += "\\";
            path += s.substring(3, s.lastIndexOf(" "));
            //System.out.println("path  "+path);

            String Path = s.substring(s.lastIndexOf(" ") + 1);
            Path += "\\";
            Path += s.substring(3, s.lastIndexOf(" "));
            //System.out.println("Path  "+Path);

            try {

                File afile = new File(path);

                if (afile.renameTo(new File(Path))) {
                    afile.deleteOnExit();
                    JOptionPane.showMessageDialog(null, "File is moved successful!");
                    boolean delete = afile.delete();
                } else {
                    JOptionPane.showMessageDialog(null, "File is failed to move!");
                }

            } catch (HeadlessException e) {
            }
        } else {
            String nameoffile = s.substring(3, s.lastIndexOf(" "));
            String path1 = path + "\\" + s.substring(s.lastIndexOf(" ") + 1);
            path += "\\";
            path += nameoffile;
            String check = path1;
            path1 += "\\" + nameoffile;
            File file = new File(check);

            if (file.isDirectory()) {

                File afile = new File(path);

                if (afile.renameTo(new File(path1))) {
                    System.out.println("File is moved successful!");
                    boolean delete = afile.delete();
                } else {
                    System.out.println("File is failed to move!");
                }
            } else {
                String Name = path + "\\" + s.substring(3, s.lastIndexOf(" "));
                String NewName = path + "\\" + s.substring(s.lastIndexOf(" ") + 1);
                File FILE = new File(Name);
                /* System.out.println("path1  "+path1);
            System.out.println("path  "+path);*/
                if (FILE.renameTo(new File(NewName))) {
                    JOptionPane.showMessageDialog(null, "file rename");
                    FILE.delete();
                }
            }

        }
    }

    void cp(String path, String name) throws FileNotFoundException, IOException {

        String Name = name.substring(3, name.lastIndexOf(" "));
        System.out.println(Name);
        path += "\\";
        path += Name;
        System.out.println("path" + "   " + path);
        String newPath = name.substring(name.lastIndexOf(" ") + 1);
        newPath += "\\";
        newPath += Name;
        System.out.println("newPath" + "   " + newPath);
        InputStream inStream = null;
        OutputStream outStream = null;

        File afile = new File(path);
        File bfile = new File(newPath);
        inStream = new FileInputStream(afile);
        outStream = new FileOutputStream(bfile);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inStream.read(buffer)) > 0) {

            outStream.write(buffer, 0, length);

        }
        inStream.close();
        outStream.close();
        System.out.println("File is copied successful!");
    }

    void cpdir(String s) {
        String CurrentPath = s.substring(6, s.lastIndexOf(" "));
        String DestinationPath = s.substring(s.lastIndexOf(" ") + 1);

        File srcFolder = new File(CurrentPath);
        File destFolder = new File(DestinationPath);

        //make sure source exists
        if (!srcFolder.exists()) {

            JOptionPane.showMessageDialog(null, "Directory does not exist.");
            //just exit

        } else {

            try {
                copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                //error, just exit

            }
        }

        System.out.println("Done");
    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();

            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            OutputStream out;
            try ( //if file, then copy it
                    //Use bytes stream to support all file types
                    InputStream in = new FileInputStream(src)) {
                out = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];
                int length;
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
            out.close();

        }
    }

    void zip(String s, String Path) throws FileNotFoundException, IOException //zip and s is command with zip and Path is path of file
    {
        int index, i;                                 //index is index in cmd where name of zip file end
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.' && s.charAt(i + 1) == 'z' && s.charAt(i + 2) == 'i' && s.charAt(i + 3) == 'p') {
                break;
            }
        }
        index = i + 3;
        String zipName = s.substring(s.indexOf(' ') + 1, index + 1);
        String fileName = s.substring(index + 2);
        File file = new File(Path + "\\" + fileName);
        if (!file.isDirectory()) {
            try {

                FileOutputStream fos = new FileOutputStream(Path + "\\" + zipName);
                try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                    zos.putNextEntry(new ZipEntry(file.getName()));

                    byte[] bytes = Files.readAllBytes(Paths.get(Path + "\\" + fileName));
                    zos.write(bytes, 0, bytes.length);
                    zos.closeEntry();
                }

            } catch (FileNotFoundException ex) {
                System.err.format("The file %s does not exist", Path + "\\" + fileName);
            } catch (IOException ex) {
                System.err.println("I/O error: " + ex);
            }
        } else {
            ZipDir.zip(Path, fileName, zipName);
        }
    }

}
