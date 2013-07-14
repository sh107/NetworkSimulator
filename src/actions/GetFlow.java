/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import communication.AccessDatabase;

/**
 * get packet flow
 * @author Sijin
 */
public class GetFlow {

    public String getResult(String source) {

        AccessDatabase ad = new AccessDatabase();

        String json = ad.getPacketString(source);

        return json;

    }

    public static void main(String[] args) throws Exception {


        GetFlow gf = new GetFlow();
        System.out.println(gf.getResult("146.169.6.76"));

    }
}
