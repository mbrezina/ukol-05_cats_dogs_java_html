package cz.czechitas.webapp;

import javax.validation.constraints.Pattern;
import java.util.List;

public class IndexForm {
    @Pattern(regexp = "zadna_odpoved", message = "není vyplněná odpověď")
    private List<String> obrazek;

    public List<String> getObrazek() {
        return obrazek;
    }

    public void setObrazek(List<String> obrazek) {
        this.obrazek = obrazek;
    }
}

