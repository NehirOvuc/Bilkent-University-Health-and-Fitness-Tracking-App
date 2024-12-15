public class Meal {
    private String mealPhoto;
    private String mealTitle;
    private String mealDescription;
    private double mealCal;
    private static String resPhoto;
    private static String resTitle;

    public Meal(String mealPhoto, String mealTitle, String mealDescription, double mealCal) {
        this.mealPhoto = mealPhoto;
        this.mealTitle = mealTitle;
        this.mealDescription = mealDescription;
        this.mealCal = mealCal;
    }

    public static String getResPhoto() {
        return resPhoto;
    }

    public static void setResPhoto(String resPhoto) {
        Meal.resPhoto = resPhoto;
    }

    public static String getResTitle() {
        return resTitle;
    }

    public static void setResTitle(String resTitle) {
        Meal.resTitle = resTitle;
    }

    public String getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(String mealPhoto) {
        this.mealPhoto = mealPhoto;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public double getMealCal() {
        return mealCal;
    }

    public void setMealCal(double mealCal) {
        this.mealCal = mealCal;
    }
}
