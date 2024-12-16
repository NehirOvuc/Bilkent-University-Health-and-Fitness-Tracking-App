class GoalsUser {
    private String gender;
    private int age;
    private double height; // in cm
    private double weight; // in kg
    private double bodyFatPercentage; // in %
    private double targetWeight;
    private String activityLevel; // Sedentary, Lightly Active, Moderately Active, Very Active

    public GoalsUser(String gender, int age, double height, double weight, double bodyFatPercentage, double targetWeight, String activityLevel) {
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.bodyFatPercentage = bodyFatPercentage;
        this.targetWeight = targetWeight;
        this.activityLevel = activityLevel;
    }

    // Getters
    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }
}
