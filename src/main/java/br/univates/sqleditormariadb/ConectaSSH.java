/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.sqleditormariadb;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 * @author ala.klein
 */
public class ConectaSSH {

    static int lport;
    static String rhost;
    static int rport;

    public static void go() {
        String user = "sisbd";
        String password = "sisbd2021";
        String host = "177.44.248.12";
        int port = 22;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 3306;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
