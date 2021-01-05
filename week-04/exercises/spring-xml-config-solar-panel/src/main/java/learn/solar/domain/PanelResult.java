package learn.solar.domain;

import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelResult {

    private List<String> messages = new ArrayList<>();
    private Panel panel;

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess(){
        return messages.size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelResult that = (PanelResult) o;
        return Objects.equals(messages, that.messages) &&
                Objects.equals(panel, that.panel);
    }


}