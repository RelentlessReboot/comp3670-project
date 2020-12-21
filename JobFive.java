import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.io.IOException;

public class JobFive {
    public static void spyNeighbors(String subnet) throws IOException {
        int timeout = 1000;

        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + i;//get ip address

            if (InetAddress.getByName(host).isReachable(timeout)) {
                InetAddress otherHost = InetAddress.getByName(host);
                
                NetworkInterface otherNet = NetworkInterface.getByInetAddress(otherHost);

                //get hexadecimal MAC address from otherNet
                byte[] onetAddr = otherNet.getHardwareAddress();
                String[] ohex = new String[onetAddr.length];
                for (int j = 0; j < onetAddr.length; j++) {
                    ohex[j] = String.format("%02X", onetAddr[i]);
                }
                String otherMac = String.join("-", ohex);
                System.out.println("Host " + i + " with IP Address " + host + " and MAC Address " + otherMac + " is on the same LAN as JobSeeker.");
            }
        }
    }

    public static void main(String args[]) throws Exception {
        //get local IP and MAC addresses
        InetAddress localHost = InetAddress.getLocalHost();
        String localIp = (localHost.getHostAddress()).trim();//local IP address


        NetworkInterface localNet = NetworkInterface.getByInetAddress(localHost);
        //convert to hexadecimal MAC address string
        byte[] netAddr = localNet.getHardwareAddress();
        String[] hex = new String[netAddr.length];
        for (int i = 0; i < netAddr.length; i++) {
            hex[i] = String.format("%02X", netAddr[i]);
        }
        String localMac = String.join("-", hex);
        
        System.out.println("Local IP: " + localIp + "\nLocal MAC: " + localMac);
        //get subnet of localIP
        String subnet = localIp.substring(0, localIp.lastIndexOf("."));
        spyNeighbors(subnet);
    }
    
}







