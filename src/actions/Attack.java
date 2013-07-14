/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Simulate an attack
 * @author Sijin
 */
public class Attack {

    public Attack() {
    }

    public static void findPwd(char[] pw, int pos, Random ran) {
        if (pos < 0) {

            try {
                int randomInt = ran.nextInt(1000);
                String letter = new String(pw);
                GetPacket gp = new GetPacket();
                int packet = gp.getPacketSize(letter);
                System.out.println(new String(pw));
                System.out.println(packet);
                Thread.sleep(randomInt);
            } catch (InterruptedException ex) {
                Logger.getLogger(Attack.class.getName()).log(Level.SEVERE, null, ex);
            }


            return;
        }
        for (pw[pos] = 'a'; pw[pos] <= 'z'; pw[pos]++) {
            findPwd(pw, pos - 1, ran);
        }
    }

    public static void main(String[] args) {
        //   char[] pw = new char[8];
        Random randomGenerator = new Random();
        //   Attack.findPwd(pw, 7, randomGenerator);

        long start = System.nanoTime();
        int letters = 26;
        int count = 6;
        final int combinations = (int) Math.pow(letters, count);
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < combinations; i++) {
            sb.setLength(0);
            for (int j = 0, i2 = i; j < count; j++, i2 /= letters) {
                sb.insert(0, (char) ('a' + i2 % letters));
            }

            try {
                int randomInt = randomGenerator.nextInt(1000);
                String letter = sb.toString();
                GetPacket gp = new GetPacket();
                int packet = gp.getPacketSize(letter);
                System.out.println(sb);
              //  System.out.println(packet);
                Thread.sleep(randomInt);
            } catch (InterruptedException ex) {
                Logger.getLogger(Attack.class.getName()).log(Level.SEVERE, null, ex);
            }


          //  System.out.println(sb);
        }
        long time = System.nanoTime() - start;
        System.out.printf("Took %.3f seconds to generate %,d combinations%n", time / 1e9, combinations);


    }
}
