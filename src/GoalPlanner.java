class GoalPlanner {
    public static double calculateIdealWeight(double height, String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return 50 + 2.3 * ((height / 2.54) - 60);
        } else {
            return 45.5 + 2.3 * ((height / 2.54) - 60);
        }
    }

    public static double calculateBMR(GoalsUser user) {
        if (user.getGender().equalsIgnoreCase("male")) {
            return 66 + (13.7 * user.getWeight()) + (5 * user.getHeight()) - (6.8 * user.getAge());
        } else {
            return 655 + (9.6 * user.getWeight()) + (1.8 * user.getHeight()) - (4.7 * user.getAge());
        }
    }

    public static double calculateCalorieNeeds(GoalsUser user) {
        double bmr = calculateBMR(user);
        switch (user.getActivityLevel().toLowerCase()) {
            case "sedentary":
                return bmr * 1.2;
            case "lightly active":
                return bmr * 1.375;
            case "moderately active":
                return bmr * 1.55;
            case "very active":
                return bmr * 1.725;
            default:
                throw new IllegalArgumentException("Invalid activity level");
        }
    }

    public static String determineGoal(GoalsUser user) {
        if (user.getWeight() > user.getTargetWeight() && user.getBodyFatPercentage() > 25) {
            return "Weight Loss";
        } else if (user.getWeight() < user.getTargetWeight() && user.getBodyFatPercentage() > 20) {
            return "Muscle Gain";
        } else {
            return "Fat Reduction and Maintenance";
        }
    }
}