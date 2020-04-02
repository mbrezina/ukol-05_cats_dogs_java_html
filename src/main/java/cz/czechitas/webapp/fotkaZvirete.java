package cz.czechitas.webapp;

public class fotkaZvirete {
    private String nazevSouboru;
    private String zvire;

    public fotkaZvirete(String nazevSouboru, String zvire) {
        this.nazevSouboru = nazevSouboru;
        this.zvire = zvire;
    }

    public String getNazevSouboru() {
        return nazevSouboru;
    }

    public String getZvire() {
        return zvire;
    }

    public void setNazevSouboru(String nazevSouboru) {
        this.nazevSouboru = nazevSouboru;
    }
    public void setZvire(String zvire) {
        this.zvire = zvire;
    }
}
