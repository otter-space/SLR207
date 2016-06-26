import java.io.*;
import java.util.ArrayList;

public class m {

    public static void main(String[] args) {
        int i = 0;
        String ip = "";
        String in = "";
        File h = new File("h");
        ArrayList<sm> a_sm = new ArrayList<sm>();
        ArrayList<File> sx = new ArrayList<File>();
        try {
            FileInputStream fis_lh = new FileInputStream("list_hosts");
            InputStreamReader isr_lh = new InputStreamReader(fis_lh, "UTF-8");
            BufferedReader br_lh = new BufferedReader(isr_lh);

            FileInputStream fis_i = new FileInputStream(args[0]);
            InputStreamReader isr_i = new InputStreamReader(fis_i, "UTF-8");
            BufferedReader br_i = new BufferedReader(isr_i);

            while ((in = br_i.readLine()) != null) {
                sx.add(new File("sx_" + i));
                PrintWriter w = new PrintWriter(sx.get(i), "UTF-8");
                w.println(in);
                w.close();
                i++;

                System.out.println(in);
            }
            
            i = 0;
            while ((ip = br_lh.readLine()) != null) {
                a_sm.add(new sm(ip, sx.get(i), i));
                i++;
            }
            for (i = 0; i < a_sm.size(); i++)
                a_sm.get(i).start();
            for (i = 0; i < a_sm.size(); i++)
                a_sm.get(i).join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");
    }
}

