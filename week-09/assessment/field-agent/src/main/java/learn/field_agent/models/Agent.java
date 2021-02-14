package learn.field_agent.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

public class Agent {

    private int agentId;
    @NotBlank(message = "first name is required")
    private String firstName;
    private String middleName;
    @NotBlank(message = "last name is required")
    private String lastName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dob;
    @Min(value = 36, message = "Height must be between 36 and 96 inches")
    @Max(value = 96, message = "Height must be between 36 and 96 inches")
    private int heightInInches;
    private List<AgentAgency> agencies = new ArrayList<>();

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public List<AgentAgency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public void setAgencies(List<AgentAgency> agencies) {
        this.agencies = agencies;
    }
}
