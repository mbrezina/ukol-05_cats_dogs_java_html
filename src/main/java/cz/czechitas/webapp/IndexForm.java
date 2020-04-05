package cz.czechitas.webapp;

import javax.validation.constraints.Pattern;
import java.util.List;

public class IndexForm {

    private List<@Pattern(regexp = "(pes|kočka)") String> obrazek;

    public List<String> getObrazek() {
        return obrazek;
    }

    public void setObrazek(List<String> obrazek) {
        this.obrazek = obrazek;
    }
}

