import java.io.*;

public class s {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String[] i = br.readLine().split(" ");

            for (String word : i)
                System.out.println(word + ", 1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

