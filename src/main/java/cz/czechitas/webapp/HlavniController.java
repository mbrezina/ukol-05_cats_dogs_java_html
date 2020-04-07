package cz.czechitas.webapp;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HlavniController {
    private List<FotkaZvirete> souboryKockyPsi;
    int pocitadlo = 0;
    public static final ArrayList<ArrayList<String>> FOTKYZVIRAT = new ArrayList<ArrayList<String>>();

    public String urciZvire(String nazevSouboru) {
        String regex = "pes_.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nazevSouboru);
        if (matcher.find()) {
            return "pes";
        } else {
            return "kočka";
        }
    }

    public HlavniController() throws IOException {
        ResourcePatternResolver prohledavacSlozek = new PathMatchingResourcePatternResolver();
        List<Resource> cestyKSouborum = Arrays.asList(prohledavacSlozek.getResources("classpath:/static/images/animals/*"));
        souboryKockyPsi = new ArrayList<>(cestyKSouborum.size());

        ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleList = new ArrayList<String>();
        singleList.add("hello");
        singleList.add("world");
        listOLists.add(singleList);

        for (Resource cesta : cestyKSouborum) {
            String druhZvirete = urciZvire(cesta.getFilename());
            System.out.println(druhZvirete);

            souboryKockyPsi.add(new FotkaZvirete(cesta.getFilename(), druhZvirete));
            System.out.println(cesta.getFilename());
        }
        Collections.shuffle(souboryKockyPsi);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        ModelAndView data = new ModelAndView("index");
        data.addObject("seznamFotekKocekPsu", souboryKockyPsi);
        return data;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujIndex(IndexForm vstup) {
        List<Hodnoceni> konecnyVysledek = new ArrayList<>();
        for (int j = 0; j < souboryKockyPsi.size(); j++) {
            if (vstup.getObrazek().get(j).equals("zadna_odpoved")) {
                konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "NO_ANSWER"));
                pocitadlo++;
            } else {
                if (vstup.getObrazek().get(j).equals(souboryKockyPsi.get(j).getZvire())) {
                    konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "CORRECT"));
                } else {
                    konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "WRONG"));
                }
                System.out.println(konecnyVysledek);
            }
        }
        if (pocitadlo > 0) {
            ModelAndView oprava = new ModelAndView("index");
            oprava.addObject("oprava_nutna", "Nezadali jste všechny odpovědi, doplňte to.");
            oprava.addObject("zadaneOdpovedi", vstup.getObrazek());
            oprava.addObject("seznamFotekKocekPsu", souboryKockyPsi);
            pocitadlo = 0;
            return oprava;

        } else {
            ModelAndView data = new ModelAndView("vysledek");
            data.addObject("zaver", konecnyVysledek);
            return data;
        }

    }
}



