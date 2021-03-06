import java.io.*;
import java.util.*;
public class Sort {

    public static void main(String[] args) throws IOException {
        String path;
        Scanner awal = new Scanner(System.in);

        System.out.println("Masukkan File :");
        path = awal.nextLine();

        BufferedReader reader = null;
        PrintWriter writer = null;

        ArrayList<String> rows = new ArrayList<String>();

        
        try {
            
            reader = new BufferedReader(new FileReader(path));
            String file;
            System.out.println("sebelum diurutkan:");
            while ((file = reader.readLine()) != null) {
                rows.add(file);
                System.out.println(file);
            }


            boolean finished = true;

            while (finished) {
                finished = false;
                for (int i = 0; i < rows.size() - 1; i++) {
                    String nama1 = rows.get(i);
                    String nama2 = rows.get(i + 1);
                    if (nama1.compareTo(nama2) > 0) {
                        String temp = rows.get(i);
                        rows.set(i, rows.get(i + 1));
                        rows.set(i + 1, temp);
                        finished = true;
                    }
                }
            }

                writer = new PrintWriter(new FileWriter(path));
                String[] stringArr = rows.toArray(new String[0]);
                System.out.println("Setelah diurutkan");
                for (String urut : stringArr) {
                    writer.println(urut);
                    System.out.println(urut);
                }
            

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
