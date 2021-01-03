package learn.solar.models;

public enum PanelMaterial {
    MULTICRYSTALLINE_SILICON("MultiSi", "Multicrystalline Silicon"),
    MONOCRYSTALLINE_SILICON("MonoSi ", "Monocrystalline Silicon"),
    AMORPHOUS_SILICON("AmorSi ", "Amorphous Silicon"),
    CADMUIM_TELLURIDE("CadTell", "Cadmiun Telluride"),
    COPPER_INDIUM_GALLIUM_SELENIDE("CIGS   ", "Copper Indium Gallium Selenide");

    private String shortHandName;
    private String materialName;


    PanelMaterial(String shortHandName, String materialName) {
        this.shortHandName = shortHandName;
        this.materialName = materialName;
    }

    public String getShortHandName() {
        return shortHandName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
