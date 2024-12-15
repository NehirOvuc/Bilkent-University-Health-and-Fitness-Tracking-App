import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TXThandler {
    public ArrayList<Meal> meals;

    public TXThandler(String resName) {
        if(!resName.isEmpty()) {meals = new ArrayList<>();
            String fileName = "src/TXT/"+resName+".txt";  // specify the path to your file
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                // Skip the first line
                String line1 = br.readLine();
                Meal.setResPhoto(line1.split("#")[0]);
                Meal.setResTitle(line1.split("#")[1]);

                String line;
                while ((line = br.readLine()) != null) {
                    String[] temp = line.split("#");
                    meals.add(new Meal(temp[0],temp[1],temp[2],Double.parseDouble(temp[3])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }}

    }

}
