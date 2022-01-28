import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class ReaderClass {

    public void writeInfoIntoMyFile() {
        File file = new File("text.txt");
        List<String> response = List.of("Fedor", "ignat", "Anatoly", "Anton", "Ilya", "NeIlya");
        if (file.exists()) {
            try {
                //BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file.getName()));
                for (var i : response) {
                    Files.write(file.toPath(),response);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<String> readMyFile() throws FileNotFoundException {
        File file = new File("text.txt");
        try {
            List<String> temp = Files.readAllLines(file.toPath());
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
