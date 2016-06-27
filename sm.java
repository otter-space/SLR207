import java.io.*;
import java.util.*;

public class sm extends Thread {

    String ip;
    File s;
    File um;
    //File h; // Used to get a return, like "$ hostname"

    sm(String ip, File s, int i) {
        this.ip = ip;
        this.s = s;
        um = new File("um_" + i);
        //this.h = h;
    }

    public void run() {
        try {
            System.out.println("start " + ip);
            sleep(1000);

            File s = new File("s" + ip);
            PrintWriter w = new PrintWriter(s, "UTF-8");
            w.println("#!/bin/bash");
            // useless on the school's cumputers
            // w.println("scp sm.jar decrevoisier@" + ip + ":");
            w.println("ssh decrevoisier@" + ip + " java -jar s.jar " + s);
            w.close();
    
            ProcessBuilder pb = new ProcessBuilder("bash", s.toString());
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(um));
            Process p = pb.start();
            p.waitFor();

            s.delete();
            System.out.println("end " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

