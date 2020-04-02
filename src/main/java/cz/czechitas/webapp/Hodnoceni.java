package cz.czechitas.webapp;

public class Hodnoceni {

    private String odpoved;
    private String spravna_odpoved;
    private String status;


    public Hodnoceni(String odpoved, String spravna_odpoved, String status) {
        this.odpoved = odpoved;
        this.spravna_odpoved = spravna_odpoved;
        this.status = status;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getOdpoved() {
        return odpoved;
    }

    public String getSpravna_odpoved() {
        return spravna_odpoved;
    }

    public void setOdpoved(String odpoved) {
        this.odpoved = odpoved;
    }

    public void setSpravna_odpoved(String spravna_odpoved) {
        this.spravna_odpoved = spravna_odpoved;
    }
}
