package learn.solar.models;

public enum PanelMaterial {
    MULTICRYSTALLINE_SILICON(1,"MultiSi", "Multicrystalline Silicon"),
    MONOCRYSTALLINE_SILICON(2,"MonoSi ", "Monocrystalline Silicon"),
    AMORPHOUS_SILICON(3,"AmorSi ", "Amorphous Silicon"),
    CADMUIM_TELLURIDE(4,"CadTell", "Cadmiun Telluride"),
    COPPER_INDIUM_GALLIUM_SELENIDE(5,"CIGS   ", "Copper Indium Gallium Selenide");

    private int value;
    private String shortHandName;
    private String materialName;


    PanelMaterial(int value, String shortHandName, String materialName) {
        this.value = value;
        this.shortHandName = shortHandName;
        this.materialName = materialName;
    }

    public int getValue() {
        return value;
    }

    public String getShortHandName() {
        return shortHandName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public static PanelMaterial findByValue(int value){
        for (PanelMaterial material : PanelMaterial.values()){
            if (material.getValue() == value){
                return material;
            }
        }

        String message = String.format("No material with value: %s", value);
        throw new RuntimeException(message);
    }
}
