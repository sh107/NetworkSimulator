/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import attacktype.NetworkProperties;
import communication.AccessDatabase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * get packet size
 * @author Sijin
 */
public class GetPacket {

    public GetPacket() {
    }

    public int getPacketSize(String letter) {

        int packet = -1;
        

        

        if (letter != null) {
            letter = letter.toLowerCase();
            letter = letter.replace(" ", "%20");
        }
        AccessDatabase ad = new AccessDatabase();
        http(letter);
//        try {
//            Thread.sleep(1 * 1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        packet = ad.checkPacket(letter.replace("%20", " "));
       // packet = ad.getPacket(letter);

        while (packet == -1) {
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
            }
            // AccessDatabase adb = new AccessDatabase();
            //packet = ad.getPacket(letter);
            packet = ad.checkPacket(letter.replace("%20", " "));
        }

//        http(letter);
//        packet = ad.checkPacket(letter);
//
//        while (packet == -1) {
//            try {
//                Thread.sleep(1 * 1000);
//                http(letter);
//                packet = ad.checkPacket(letter);
//                //         packet = obtainPacket(letter);
//                //            if (packet != -1) {
//                //                update = true;
//                //            }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

//        if (update) {
//            try {
//                //write to db;
//                ad.writeNetworkTyping(letter, packet);
//            } catch (Exception ex) {
//                Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        return packet;

    }

    public static void main(String[] args) throws Exception {


        GetPacket gp = new GetPacket();
        System.out.println(gp.getPacketSize("abcddfghicjkdld"));

    }

    private int obtainPacket(String letter) {
        int packet = -1;

        try {

            int time = 10;
            //turn on tshark
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("/monitor/samplenetwork/network.sh " + time);

            //send request
            http(letter);

            if (p.waitFor() == 0) {
                NetworkProperties np = new NetworkProperties();
                List<NetworkProperties> listnp = np.convertNetworkTable("/monitor/samplenetwork/test.csv");

                for (int i = 0; i < listnp.size(); i++) {

                    System.out.println(listnp.get(i).getUri());
                    if (listnp.get(i).getUri().equalsIgnoreCase("/NetworkSample/NetworkExample?term=" + letter)) {
                        packet = listnp.get(i).getPacket();
                    }

                }
            }
//            //
        } catch (InterruptedException ex) {
            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return packet;

    }

    private void http(String letter) {
        String https_url = "http://146.169.35.102:55000/NetworkSample/NetworkExample?term=" + letter;
        URL url;
        try {

            url = new URL(https_url);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));

//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
