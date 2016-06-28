import java.io.*;
import java.util.*;

public class sm extends Thread {

    String ip;
    String[] arg;
    File ret;

    sm(String ip, String[] arg) {
        this.ip = ip;
        this.arg = arg;
        ret = new File(arg[0]);
    }

    /* ret args */
    public void run() {
        try {
            System.out.println("start " + ip + " " + ret.getName());

            File s = new File("script_" + ret.getName());
            PrintWriter w = new PrintWriter(s, "UTF-8");
            w.println("#!/bin/bash");
            // useless on the school's cumputers
            // w.println("scp sm.jar decrevoisier@" + ip + ":");
            w.println("ssh decrevoisier@" + ip + " java -jar s.jar ");
            for (int i = 1; i < arg.length; i++)
                w.print(arg[i] + " ");
            w.close();
    
            ProcessBuilder pb = new ProcessBuilder("bash", s.toString());
            pb.redirectOutput(ProcessBuilder.Redirect.to(ret));
            Process p = pb.start();
            p.waitFor();

            s.delete();
            System.out.println("end " + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

