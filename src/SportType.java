public class SportType {
    private static String sportTitle;
    private static String sportPhoto;
    final private double sportLowTempoBurnedCaloriesPerHour;
    final private double sportMediumTempoBurnedCaloriesPerHour;
    final private double sportHighTempoBurnedCaloriesPerHour;

    public SportType(String sportTitle, String sportPhoto, double sportLowTempoBurnedCaloriesPerHour, double sportMediumTempoBurnedCaloriesPerHour, double sportHighTempoBurnedCaloriesPerHour) {
        this.sportTitle = sportTitle;
        this.sportPhoto = sportPhoto;
        this.sportLowTempoBurnedCaloriesPerHour = sportLowTempoBurnedCaloriesPerHour;
        this.sportMediumTempoBurnedCaloriesPerHour = sportMediumTempoBurnedCaloriesPerHour;
        this.sportHighTempoBurnedCaloriesPerHour = sportHighTempoBurnedCaloriesPerHour;
    }

    public static String getSportPhoto() {
        return sportPhoto;
    }

    public static String getSportTitle() {
        return sportTitle;
    }

    public double getSportLowTempoBurnedCaloriesPerHour() {
        return sportLowTempoBurnedCaloriesPerHour;
    }

    public double getSportMediumTempoBurnedCaloriesPerHour() {
        return sportMediumTempoBurnedCaloriesPerHour;
    }

    public double getSportHighTempoBurnedCaloriesPerHour() {
        return sportHighTempoBurnedCaloriesPerHour;
    }

    public static void setSportPhoto(String sportPhoto) {
        SportType.sportPhoto = sportPhoto;
    }

    public static void setSportTitle(String sportTitle) {
        SportType.sportTitle = sportTitle;
    }

}
