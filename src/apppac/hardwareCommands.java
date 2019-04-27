package apppac;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class hardwareCommands extends hardwareInfo {

    String ipconfig() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hstnm = inetAddress.getHostName();
        String mc = "";
        try {
            inetAddress = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            mc = sb.toString();

        } catch (SocketException | UnknownHostException e) {
        }
        String combo = "IP ADDRESS -" + ip + System.lineSeparator() + "HOST NAME" + hstnm + System.lineSeparator() + "MAC ADDRESS -" + mc;
        return combo;
    }

    String getOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        return OS;
    }

    String osinfo() {
        String processor = "Available processors (cores): "
                + Runtime.getRuntime().availableProcessors();
        String freeMemory = "Free memory (bytes): "
                + Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        String maxMmry = "Maximum memory (bytes): " + Long.toString(maxMemory);
        return (processor + System.lineSeparator() + freeMemory + System.lineSeparator() + maxMmry);

    }

    String hrdInfo() {

        return printComputerSystemProductInfo();
    }

}
