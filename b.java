import java.io.*;

public class b {

    public static void main(String[] args) {
        File s = new File("s.sh");

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", s.toString());
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();
            BufferedReader r =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
            String l = "";
            while ((l = r.readLine()) != null)
                System.out.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

