package learn.solar.models;

public enum PanelMaterial {
    MULTICRYSTALLINE_SILICON("Multicry Silicon"),
    MONOCRYSTALLINE_SILICON("Monocry Silicon"),
    AMORPHOUS_SILICON("Amorphous Silicon"),
    CADMUIM_TELLURIDE("Cadmium Telluride"),
    COPPER_INDIUM_GALLIUM_SELENIDE("CIGS");

    private String materialName;

    PanelMaterial(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
