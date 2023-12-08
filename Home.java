public class Home {
    private String homeId;
    private String city;
    private String type;
    private double basePricePerDay;
    private boolean isAvailable;

    public Home(String homeId, String city, String type, double basePricePerDay) {
        this.homeId = homeId;
        this.city = city;
        this.type = type;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }
    public String getHomeId() {
        return homeId;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnHome() {
        isAvailable = true;
    }
}