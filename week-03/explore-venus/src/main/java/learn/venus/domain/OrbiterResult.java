package learn.venus.domain;

import learn.venus.models.Orbiter;

import java.util.ArrayList;
import java.util.List;

public class OrbiterResult {

    private ArrayList<String> messages = new ArrayList<>();
    private Orbiter payLoad;

    public void addErrorMessages(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public Orbiter getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Orbiter payLoad) {
        this.payLoad = payLoad;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
