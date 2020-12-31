package learn.solar.models;

public enum PanelType {
    MULTICRYSTALLINE_SILICON("Multicrystalline Silicon"),
    MONOCRYSTALLINE_SILICON("Monocrystalline Silicon"),
    AMORPHOUS_SILICON("Amorphous Silicon"),
    CADMUIM_TELLURIDE("Cadmium Telluride"),
    COPPER_INDIUM_GALLIUM_SELENIDE("Copper Indium Gallium Selenide");

    private String materialName;

    PanelType(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
