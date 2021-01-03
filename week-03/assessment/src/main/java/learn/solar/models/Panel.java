package learn.solar.models;

import java.util.Objects;

public class Panel {

    private int panelId;
    private String section;
    private int row;
    private int column;
    private PanelMaterial material;
    private int yearInstalled;
    private boolean isTracking;

    public Panel() {

    }

    public Panel(int panelId, String section, int row, int column, PanelMaterial material, int yearInstalled, boolean isTracking) {
        this.panelId = panelId;
        this.section = section;
        this.row = row;
        this.column = column;
        this.material = material;
        this.yearInstalled = yearInstalled;
        this.isTracking = isTracking;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public PanelMaterial getMaterial() {
        return material;
    }

    public void setMaterial(PanelMaterial material) {
        this.material = material;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return Objects.equals(section, panel.section) &&
                row == panel.row &&
                column == panel.column;
    }
}
