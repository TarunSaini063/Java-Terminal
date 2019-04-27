/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppac;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 *
 * @author Shinigami
 */
public class Search {

    String LswithOneSpace(String path) throws IOException {
        String s, str;
        String ans = "";
        File f = new File(path); // current directory                           }
        FileFilter directoryFilter = File::isDirectory;
        File[] files = f.listFiles(directoryFilter);
        for (File file : files) {
            s = file.getCanonicalPath();
            str = s.substring(s.lastIndexOf("\\") + 1);
            ans += str;
            ans += "_|_";

        }

        return ans;
    }

    boolean search(String path, String word) //search file in given path
    {                                            //word if name of file
        File f = new File(path);
        File[] files = f.listFiles();
        for (File file : files) {
            if (word.compareTo(file.getName()) == 0) {
                return true;
            }

        }
        return false;
    }
}
