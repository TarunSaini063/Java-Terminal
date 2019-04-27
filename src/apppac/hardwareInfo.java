package apppac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class MyUtility {

    public static String makeVbScript(String vbClassName, String[] propNames) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < propNames.length; i++) {
            if (i < propNames.length - 1) {
                sb.append(propNames[i]).append(",");
            } else {
                sb.append(propNames[i]);
            }
        }
        String colNameString = sb.toString();
        sb.setLength(0);

        sb.append("Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")").append("\n");
        sb.append("Set colItems = objWMIService.ExecQuery _ ").append("\n");
        sb.append("(\"Select ").append(colNameString).append(" from ").append(vbClassName).append("\") ").append("\n");
        sb.append("For Each objItem in colItems ").append("\n");
        for (String propName : propNames) {
            sb.append("    Wscript.Echo objItem.").append(propName).append("\n");
        }
        sb.append("Next ").append("\n");
        return sb.toString();
    }

}

class hardwareInfo {

    public static String printComputerSystemProductInfo() {

        String vbClassName = "Win32_ComputerSystemProduct";
        String[] propNames = new String[]{"Name", "UUID", "Vendor", "Version"};

        String vbScript = MyUtility.makeVbScript(vbClassName, propNames);

        try {
            // Create temporary file.
            File file = File.createTempFile("vbsfile", ".vbs");

            try ( // Write script content to file.
                    FileWriter fw = new FileWriter(file)) {
                fw.write(vbScript);
            }

            // Execute the file.
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

            Map<String, String> map;
            try ( // Create Input stream to read data returned after execute vb script file.
                    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                map = new HashMap<>();
                String line;
                int i = 0;
                while ((line = input.readLine()) != null) {
                    if (i >= propNames.length) {
                        break;
                    }
                    String key = propNames[i];
                    map.put(key, line);
                    i++;
                }
            }
            //
            String hinfo = "";
            for (String propName : propNames) {
                hinfo += System.lineSeparator() + propName + " : " + map.get(propName);
                //System.out.println(propName + " : " + map.get(propName));
            }
            return hinfo;
        } catch (IOException e) {
        }
        return null;
    }

}
