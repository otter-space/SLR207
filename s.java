import java.io.*;
import java.util.*;

public class s {

    public static void main(String[] args) {
        try {
            Set<String> k = new HashSet<String>();

            FileInputStream fis = new FileInputStream(args[0]);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            File s = new File(args[0] + "_dico");
            PrintWriter pw = new PrintWriter(s, "UTF-8");

            String[] i = br.readLine().split(" ");

            for (String word : i) {
                System.out.println(word + " 1");
                k.add(word);
            }
            for (String word : k)
                pw.println(word);
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

