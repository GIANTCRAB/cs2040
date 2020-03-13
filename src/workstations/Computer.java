package workstations;

public class Computer {
    private Integer expiry;

    public Computer(Integer expiry) {
        this.setExpiry(expiry);
    }

    public Integer getExpiry() {
        return expiry;
    }

    public void setExpiry(Integer expiry) {
        this.expiry = expiry;
    }
}
