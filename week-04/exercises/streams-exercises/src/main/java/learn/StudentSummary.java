package learn;

import java.util.Objects;

public class StudentSummary {
    private String country;
    private String major;
    private double iq;

    public StudentSummary(String country, String major, double iq) {
        this.country = country;
        this.major = major;
        this.iq = iq;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry() {
        this.country = country;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getIq() {
        return iq;
    }

    public void setIq(double iq) {
        this.iq = iq;
    }

}
