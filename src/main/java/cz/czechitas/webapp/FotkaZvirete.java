package cz.czechitas.webapp;

public class FotkaZvirete {
    private String nazevSouboru;
    private String zvire;
    private Boolean zaskrtnuto_nic;


    public FotkaZvirete(String nazevSouboru, String zvire) {
        this.nazevSouboru = nazevSouboru;
        this.zvire = zvire;
        this.zaskrtnuto_nic = true;

    }

    public String getNazevSouboru() {
        return nazevSouboru;
    }
    public String getZvire() {
        return zvire;
    }
    public Boolean getZaskrtnuto_nic() {
        return zaskrtnuto_nic;
    }

}
