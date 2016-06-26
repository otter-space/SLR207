import java.io.*;

public class c {

    public static void main(String[] args) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(
                "hostname");
            p.waitFor();
            BufferedReader r = 
                new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = "";
            while ((s = r.readLine()) != null)
                System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

