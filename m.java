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
        HashMap<String, Integer> out_sort = new HashMap<String, Integer>();
        ArrayList<String> common_w = new ArrayList<String>();

        String[] tmp1 = {"je", "tu", "il", "elle", "nous", "vous", "ils", "elles"};
        String[] tmp2 = {"le", "la", "l'", "lui", "les", "leur", "eux"};
        String[] tmp3 = {"celui", "celui-ci", "celui-]à", "ceci", "cela", "ça"};
        String[] tmp4 = {"celle", "celle-ci", "celle-là"};
        String[] tmp5 = {"ceux", "ceux-ci", "ceux-là"};
        String[] tmp6 = {"celles", "celles-ci", "celles-là"};
        String[] tmp7 = {"mien", "tien", "sien", "nôtre", "vôtre"};
        String[] tmp8 = {"mienne", "tienne", "sienne"};
        String[] tmp9 = {"miens", "tiens", "siens", "nôtres", "vôtres"};
        String[] tmp10 = {"miennes", "tiennes", "siennes"};
        String[] tmp11 = {"on", "personne", "rien", "aucun", "aucune", "nul"};
        String[] tmp12 = {"nulle", "un", "une", "autre", "tout"};
        String[] tmp13 = {"qui", "que", "quoi", "dont", "où"};
        String[] tmp14 = {"lequel", "laquelle", "duquel", "auquel"};
        String[] tmp15 = {"lesquels", "desquels", "auxquels"};
        String[] tmp16 = {"lesquelles", "desquelles", "auxquelles"};

        i = 0;
        for (i = 0; i < 10; i++) {
            if (tmp1.length > i)
                common_w.add(tmp1[i]);
            if (tmp2.length > i)
                common_w.add(tmp2[i]);
            if (tmp3.length > i)
                common_w.add(tmp3[i]);
            if (tmp4.length > i)
                common_w.add(tmp4[i]);
            if (tmp5.length > i)
                common_w.add(tmp5[i]);
            if (tmp6.length > i)
                common_w.add(tmp6[i]);
            if (tmp7.length > i)
                common_w.add(tmp7[i]);
            if (tmp8.length > i)
                common_w.add(tmp8[i]);
            if (tmp9.length > i)
                common_w.add(tmp9[i]);
            if (tmp10.length > i)
                common_w.add(tmp10[i]);
            if (tmp11.length > i)
                common_w.add(tmp11[i]);
            if (tmp12.length > i)
                common_w.add(tmp12[i]);
            if (tmp13.length > i)
                common_w.add(tmp13[i]);
            if (tmp14.length > i)
                common_w.add(tmp14[i]);
            if (tmp15.length > i)
                common_w.add(tmp15[i]);
            if (tmp16.length > i)
                common_w.add(tmp16[i]);
        }

        try {
            FileInputStream fis_lh = new FileInputStream("list_hosts");
            InputStreamReader isr_lh = new InputStreamReader(fis_lh, "UTF-8");
            BufferedReader br_lh = new BufferedReader(isr_lh);

            FileInputStream fis_i = new FileInputStream(args[0]);
            InputStreamReader isr_i = new InputStreamReader(fis_i, "UTF-8");
            BufferedReader br_i = new BufferedReader(isr_i);

            /* Splitting */
            long t_s = System.currentTimeMillis();
            System.out.println("### Splitting ###");
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

            /* Mapping */
            long t_m = System.currentTimeMillis();
            System.out.println("### Mapping ###");
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

            System.out.print("Sx - Umx: ");
            System.out.println(k);
            
            /* Shuffling & Reducing */
            long t_sr = System.currentTimeMillis();
            System.out.println("### Shuffling & Reducing ###");
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

            /* Assembling */
            long t_as = System.currentTimeMillis();
            System.out.println("### Assembling ###");
            File as = new File("output.txt");
            PrintWriter pw_as = new PrintWriter(as);
            for (String key : k.keySet()) {
                FileInputStream fis = new FileInputStream(key);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String kr = "";
                kr = br.readLine();
                pw_as.println(key + ", " + kr);
                if (!common_w.contains(key))
                    out_sort.put(key, new Integer(kr));
                br.close();
            }
            pw_as.close();
            
            long t_end = System.currentTimeMillis();
            System.out.println("Done");

            System.out.println("Start:                " + (t_s - t_start));
            System.out.println("Splitting:            " + (t_m - t_s));
            System.out.println("Mapping:              " + (t_sr - t_m));
            System.out.println("Shuffling & Reducing: " + (t_as - t_sr));
            System.out.println("Assembling:           " + (t_end - t_as));

            /* Sort */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

