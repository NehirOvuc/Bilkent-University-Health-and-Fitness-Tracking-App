import java.util.Map;
import java.util.HashMap;

class DietPlan {
    public static Map<String, Double> generateMacros(double calorieNeeds, String goal) {
        Map<String, Double> macros = new HashMap<>();
        switch (goal.toLowerCase()) {
            case "weight loss":
                macros.put("Protein", calorieNeeds * 0.4 / 4);
                macros.put("Carbohydrates", calorieNeeds * 0.3 / 4);
                macros.put("Fats", calorieNeeds * 0.3 / 9);
                break;
            case "muscle gain":
                macros.put("Protein", calorieNeeds * 0.4 / 4);
                macros.put("Carbohydrates", calorieNeeds * 0.4 / 4);
                macros.put("Fats", calorieNeeds * 0.2 / 9);
                break;
            case "fat reduction and maintenance":
                macros.put("Protein", calorieNeeds * 0.35 / 4);
                macros.put("Carbohydrates", calorieNeeds * 0.4 / 4);
                macros.put("Fats", calorieNeeds * 0.25 / 9);
                break;
        }
        return macros;
    }
}