/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppac;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Shinigami
 */
public class Rm extends Commands
{
    Search search=new Search();
    void rm(String Path,String name)                            //name is full command begin with rm
    {
        if("rm ".compareTo(name.substring(0,3))==0)             
        {
            String str=name.substring(name.indexOf(" ")+1); 
            if(search.search(Path,str))
            {
               delete(str,Path);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"File does not exist");
            }
        }
        
        else if("rmdir -r ".compareTo(name.substring(0,9))==0)
        {
            String str=name.substring(name.indexOf("-")+3);     //str is name of directory
            if(search.search(Path, str))
            {
               remove(str,Path);
			   delete(str,Path);
               
            }
            else
            {
                JOptionPane.showMessageDialog(null,"File does not exist");
            }
        }
        
        else if("rmdir ".compareTo(name.substring(0,6))==0)     //to delete directory
        {
             String str=name.substring(name.indexOf(" ")+1);
             if(search.search(Path,str))
             {
                     delete(str,Path);
             }
             else
             {
                JOptionPane.showMessageDialog(null,"File does not exist");
             }
        }
        
        else
        {
            JOptionPane.showMessageDialog(null,"Command not found");
        }
            
    }
        void delete(String str,String Path)                 //method to delete directiry or file
        {
            File file=new File(Path+"\\"+str);
                if(file.delete())
                {
                    JOptionPane.showMessageDialog(null,str+" delete successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,str+" delete unsuccessfully");
                }
                    
        }
        void remove(String str,String Path)
        {
            String newPath=Path+"//"+str;
            File f=new File(newPath);
            File[] files=f.listFiles();
            if(files.length==0)
                return;
            for(File file:files)
            {
                if(file.isDirectory())
                {
                    remove(file.getName(),newPath);
                    delete(file.getName(),newPath);
                }
                else
                {
                    delete(file.getName(),newPath);
                }
                
            }
            
        }
          
    
}
