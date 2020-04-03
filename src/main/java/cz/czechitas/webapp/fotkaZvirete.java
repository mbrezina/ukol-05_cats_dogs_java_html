package cz.czechitas.webapp;

public class fotkaZvirete {
    private String nazevSouboru;
    private String zvire;
    private Boolean zaskrtnuto_nic;
    private Boolean zaskrtnut_pes;
    private Boolean zaskrtnuta_kocka;

    public void setZaskrtnut_pes(Boolean zaskrtnut_pes) {
        this.zaskrtnut_pes = zaskrtnut_pes;
    }

    public void setZaskrtnuta_kocka(Boolean zaskrtnuta_kocka) {
        this.zaskrtnuta_kocka = zaskrtnuta_kocka;
    }

    public Boolean getZaskrtnut_pes() {
        return zaskrtnut_pes;
    }

    public Boolean getZaskrtnuta_kocka() {
        return zaskrtnuta_kocka;
    }

    public fotkaZvirete(String nazevSouboru, String zvire, Boolean zaskrtnuto_nic, Boolean zaskrtnut_pes, Boolean zaskrtnuta_kocka) {
        this.nazevSouboru = nazevSouboru;
        this.zvire = zvire;
        this.zaskrtnuto_nic = zaskrtnuto_nic;
        this.zaskrtnut_pes = zaskrtnut_pes;
        this.zaskrtnuta_kocka = zaskrtnuta_kocka;

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

    public void setNazevSouboru(String nazevSouboru) {
        this.nazevSouboru = nazevSouboru;
    }
    public void setZvire(String zvire) {
        this.zvire = zvire;
    }
    public void setZaskrtnuto_nic(Boolean zaskrtnuto_nic) {
        this.zaskrtnuto_nic = zaskrtnuto_nic;
    }

}
