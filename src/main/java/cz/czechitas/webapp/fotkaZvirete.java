package cz.czechitas.webapp;

public class fotkaZvirete {
    private String nazevSouboru;
    private String zvire;
    private String zaskrtnuto_nic;
    private String zaskrtnut_pes;
    private String zaskrtnuta_kocka;

    public void setZaskrtnut_pes(String zaskrtnut_pes) {
        this.zaskrtnut_pes = zaskrtnut_pes;
    }

    public void setZaskrtnuta_kocka(String zaskrtnuta_kocka) {
        this.zaskrtnuta_kocka = zaskrtnuta_kocka;
    }

    public String getZaskrtnut_pes() {
        return zaskrtnut_pes;
    }

    public String getZaskrtnuta_kocka() {
        return zaskrtnuta_kocka;
    }

    public fotkaZvirete(String nazevSouboru, String zvire, String zaskrtnuto_nic, String zaskrtnut_pes, String zaskrtnuta_kocka) {
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
    public String getZaskrtnuto_nic() {
        return zaskrtnuto_nic;
    }

    public void setNazevSouboru(String nazevSouboru) {
        this.nazevSouboru = nazevSouboru;
    }
    public void setZvire(String zvire) {
        this.zvire = zvire;
    }
    public void setZaskrtnuto_nic(String zaskrtnuto_nic) {
        this.zaskrtnuto_nic = zaskrtnuto_nic;
    }

}
