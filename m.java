import java.io.*;
import java.util.*;

public class m {

    public static void main(String[] args) {
        int i;
        String ip = "";
        String in = "";
        ArrayList<sm> a_sm = new ArrayList<sm>();
        ArrayList<String> sx = new ArrayList<String>();
        HashMap<String, ArrayList<Integer>> k =
            new HashMap<String, ArrayList<Integer>>();

        try {
            FileInputStream fis_lh = new FileInputStream("list_hosts");
            InputStreamReader isr_lh = new InputStreamReader(fis_lh, "UTF-8");
            BufferedReader br_lh = new BufferedReader(isr_lh);

            FileInputStream fis_i = new FileInputStream(args[0]);
            InputStreamReader isr_i = new InputStreamReader(fis_i, "UTF-8");
            BufferedReader br_i = new BufferedReader(isr_i);

            i = 0;
            while ((in = br_i.readLine()) != null) {
                if (in == "\n" || in == null)
                    break;
                sx.add("s_" + i);
                PrintWriter mw = new PrintWriter(new File("s_" + i), "UTF-8");
                mw.println(in);
                mw.close();
                i++;

                System.out.println(in);
            }
            
            i = 0;
            while ((ip = br_lh.readLine()) != null) {
                a_sm.add(new sm(ip, sx.get(i), i));
                i++;
            }

            int sz = a_sm.size();

            for (i = 0; i < sz; i++)
                a_sm.get(i).start();

            for (i = 0; i < sz; i++)
                a_sm.get(i).join();

            /* Sx - Umx */
            for (i = 0; i < sz; i++) {
                FileInputStream fis = new FileInputStream("s_" + i + "_dico");
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String s = "";
                while ((s = br.readLine()) != null) {
                    if (!k.containsKey(s))
                        k.put(s, new ArrayList<Integer>());
                    k.get(s).add(i);
                }
                br.close();
            }

            System.out.println("Sx - Umx");
            System.out.println(k);
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done.\n");
    }
}

