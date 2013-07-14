/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networksimulator;

import actions.GetFlow;
import actions.GetPacket;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Network Simulator
 * @author Sijin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here





        Options options = new Options();
        options.addOption("c", true, "options: typing, sniffer");
        options.addOption("o", true, "options: output to a file: filename");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        //System.out.println(cmd.getOptionValue("u"));
        String name = cmd.getOptionValue("c");

        if (name.equalsIgnoreCase("typing")) {

            while (true) {
                Console c = System.console();
                if (c == null) {
                    System.err.println("No console.");
                    System.exit(1);
                }

                String letter = c.readLine("Enter a word or a letter: ");

                GetPacket gp = new GetPacket();

                int packet = gp.getPacketSize(letter);
                System.out.println(packet);

                try {
                    // Create file
                    FileWriter fstream = new FileWriter(cmd.getOptionValue("o") + ".csv", true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(letter + ", " + packet);
                    out.newLine();
                    //Close the output stream
                    out.close();
                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }

            }
        }

        if (name.equalsIgnoreCase("sniffer")) {


            while (true) {
                Console c = System.console();
                if (c == null) {
                    System.err.println("No console.");
                    System.exit(1);
                }

                String ip = c.readLine("Enter an IP you wish to monitor: ");

                GetFlow gf = new GetFlow();
                String text = gf.getResult(ip);
                System.out.println(text);
                try {
                    // Create file
                    FileWriter fstream = new FileWriter(cmd.getOptionValue("o") + ".csv", true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(text);
                    out.newLine();
                    //Close the output stream
                    out.close();
                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }

            }


        }






    }
}
