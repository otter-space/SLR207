import java.io.*;
import java.util.ArrayList;

public class a {

    public static void main(String[] args) {
        String ip = "";
        File h = new File("h");
        ArrayList<Process> p = new ArrayList<Process>();
        try {
            FileInputStream fis = new FileInputStream("list_hosts");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            while ((ip = br.readLine()) != null) {
                // TODO define j
                String j = "j";
                sm sm = new sm(ip, j);
                sm.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");
    }
}

