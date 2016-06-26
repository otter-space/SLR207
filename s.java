import java.io.*;

public class s {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String[] i = br.readLine().split(" ");

            File j = new File(args[1]);
            PrintWriter w = new PrintWriter(j, "UTF-8");

            for (String word : i)
                w.println(word + ", 1");

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

