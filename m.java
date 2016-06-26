import java.io.*;
import java.util.ArrayList;

public class m {

    public static void main(String[] args) {
        String ip = "";
        File h = new File("h");
        ArrayList<sm> a_sm = new ArrayList<sm>();
        try {
            FileInputStream fis = new FileInputStream("list_hosts");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            while ((ip = br.readLine()) != null) {
                // TODO define j
                String j = "j";
                a_sm.add(new sm(ip, j, h));
            }
            for (int i = 0; i < a_sm.size(); i++)
                a_sm.get(i).start();
            for (int i = 0; i < a_sm.size(); i++)
                a_sm.get(i).join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");
    }
}

