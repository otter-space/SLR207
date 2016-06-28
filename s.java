import java.io.*;
import java.util.*;

public class s {

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                Set<String> k = new HashSet<String>();
    
                FileInputStream fis = new FileInputStream(args[0]);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
    
                File s = new File(args[0] + "_dico");
                PrintWriter pw = new PrintWriter(s, "UTF-8");
    
                String[] i = br.readLine().split(" ");
                
                for (String word : i) {
                    /* Hack: it is useless to write the 1 since it always
                     * appears. This makes for easier counting.
                     */
                    //System.out.println(word + " 1");
                    System.out.println(word);
                    k.add(word);
                }
                for (String word : k)
                    pw.println(word);
                pw.close();
            } else {
                int n = 0;
                String key = args[0];
                for (int i = 1; i < args.length; i++) {
                    FileInputStream fis = new FileInputStream(args[i]);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);

                    String rl = "";
                    while ((rl = br.readLine()) != null)
                        if (rl.contains(key))
                            n++;
                }
                System.out.println(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

