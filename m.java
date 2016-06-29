import java.io.*;
import java.util.*;

public class m {

    public static void main(String[] args) {
        long t_start = System.currentTimeMillis();

        int i;
        String ip = "";
        String in = "";
        ArrayList<sm> a_sm = new ArrayList<sm>();
        ArrayList<sm> b_sm = new ArrayList<sm>();
        ArrayList<String> sx = new ArrayList<String>();
        HashMap<String, ArrayList<Integer>> k =
            new HashMap<String, ArrayList<Integer>>();
        ArrayList<String> h = new ArrayList<String>();

        try {
            FileInputStream fis_lh = new FileInputStream("list_hosts");
            InputStreamReader isr_lh = new InputStreamReader(fis_lh, "UTF-8");
            BufferedReader br_lh = new BufferedReader(isr_lh);

            FileInputStream fis_i = new FileInputStream(args[0]);
            InputStreamReader isr_i = new InputStreamReader(fis_i, "UTF-8");
            BufferedReader br_i = new BufferedReader(isr_i);

            long t_s = System.currentTimeMillis();
            /* Splitting */
            System.out.println("Splitting");
            i = 0;
            while ((in = br_i.readLine()) != null) {
                if (in.matches("\\s*"))
                    continue;
                sx.add("s_" + i);
                PrintWriter mw = new PrintWriter(new File("s_" + i), "UTF-8");
                mw.println(in);
                mw.close();
                i++;

                System.out.println(in);
            }
            
            while ((ip = br_lh.readLine()) != null)
                h.add(ip);

            long t_m = System.currentTimeMillis();
            /* Mapping */
            System.out.println("Mapping");
            for (i = 0; i < sx.size(); i++) {
                String[] arg = {"um_" + i, sx.get(i)};
                a_sm.add(new sm(h.get(i % h.size()), arg));
            }

            int sz = a_sm.size();

            for (i = 0; i < sz; i++)
                a_sm.get(i).start();

            for (i = 0; i < sz; i++)
                a_sm.get(i).join();

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
            
            long t_sr = System.currentTimeMillis();
            /* Shuffling & Reducing */
            System.out.println("Shuffling & Reducing");
            i = 0;
            for (String key : k.keySet()) {
                ArrayList<String> arg = new ArrayList<String>();
                arg.add(key);
                arg.add(key);
                for (Integer i_tmp : k.get(key))
                    arg.add("um_" + i_tmp);
                String[] as_arg = new String[arg.size()];
                arg.toArray(as_arg);
                b_sm.add(new sm(h.get(i++ % h.size()), as_arg));
            }

            sz = b_sm.size();

            for (i = 0; i < sz; i++)
                b_sm.get(i).start();

            for (i = 0; i < sz; i++)
                b_sm.get(i).join();

            long t_as = System.currentTimeMillis();
            /* Assembling */
            System.out.println("Assembling");
            File as = new File("output.txt");
            PrintWriter pw_as = new PrintWriter(as);
            for (String key : k.keySet()) {
                FileInputStream fis = new FileInputStream(key);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                pw_as.println(key + ", " + br.readLine());
                br.close();
            }
            pw_as.close();
            
            long t_end = System.currentTimeMillis();
            System.out.println("Done");

            System.out.println("Start: " + (t_s - t_start));
            System.out.println("Splitting: " + (t_m - t_s));
            System.out.println("Mapping: " + (t_sr - t_m));
            System.out.println("Shuffling & Reducing: " + (t_as - t_sr));
            System.out.println("Assembling: " + (t_end - t_as));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

