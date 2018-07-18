package dxexwxexy.activities.Tuition;

public class Tuition {
    static final int GRADUATE = 0;
    static final int UNDERGRADUATE = 1;
    static final int IN_STATE = 2;
    static final int OUT_STATE = 3;

    private int hours;
    private int residency;
    private int level;

    Tuition(int hours, int residency, int level) {
        this.hours = hours;
        this.residency = residency;
        this.level = level;
    }

    public String getTuition() {
        return String.valueOf(hours * getTuitionPrice());
    }

    public String getFees() {
        return String.valueOf(hours * getFeesPrice());
    }

    public String getTotal() {
        return String.valueOf(getFeesPrice() + getTuitionPrice());
    }

    private double getTuitionPrice() {
        if (residency == IN_STATE && level == GRADUATE) {
            return hours * 120;
        } else if (residency == OUT_STATE && level == GRADUATE) {
            return hours * 130;
        } else if (residency == IN_STATE && level == UNDERGRADUATE) {
            return hours * 100;
        } else if (residency == OUT_STATE && level == UNDERGRADUATE) {
            return hours * 110;
        } else {
            return -1;
        }
    }

    private double getFeesPrice() {
        return residency == IN_STATE ? 20 : 30;
    }


}
