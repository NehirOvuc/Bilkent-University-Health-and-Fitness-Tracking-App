import java.util.List;
import java.util.ArrayList;

class ExercisePlan {
    public static List<String> generatePlan(String goal) {
        List<String> exercises = new ArrayList<>();
        switch (goal.toLowerCase()) {
            case "weight loss":
                exercises.add("30 minutes walking, 5 days a week");
                exercises.add("20 minutes strength training, 3 days a week");
                exercises.add("10 minutes stretching daily");
                break;
            case "muscle gain":
                exercises.add("45 minutes weightlifting, 4 days a week");
                exercises.add("15 minutes cardio, 2 days a week");
                exercises.add("15 minutes dynamic stretching before workouts");
                break;
            case "fat reduction and maintenance":
                exercises.add("30 minutes jogging, 3 days a week");
                exercises.add("30 minutes yoga, 2 days a week");
                exercises.add("20 minutes HIIT, 2 days a week");
                break;
        }
        return exercises;
    }
}