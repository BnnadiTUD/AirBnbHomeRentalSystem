public class Rental {
    private Home home;
    private Customer customer;
    private int days;

    public Rental(Home home, Customer customer, int days) {
        this.home = home;
        this.customer = customer;
        this.days = days;
    }

    public Home getHome() {
        return home;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}