package cz.czechitas.webapp;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.*;

@Controller
public class HlavniController {
    private List<fotkaZvirete> souboryKockyPsi;
    int pocitadlo = 0;

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
        //přidávám objekty fotka zvířete do seznamu:
        for (Resource cesta : cestyKSouborum) {
            String druhZvirete = urciZvire(cesta.getFilename());
            System.out.println(druhZvirete);
            souboryKockyPsi.add(new fotkaZvirete(cesta.getFilename(), druhZvirete, true, false, false));
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

        List<String> seznamOdpovedi = new ArrayList<>();
        for (String item : vstup.getObrazek()) {
            seznamOdpovedi.add(item);
        }

        List<Hodnoceni> konecnyVysledek = new ArrayList<>();
        //hranaté závorky se používají pouze u polí, u listů je to .get(index)

        for (int j = 0; j < souboryKockyPsi.size(); j++) {

            System.out.println("tisknu odpověď ze seznamu seznamOdpovedi: " + seznamOdpovedi.get(j));
            System.out.println("jaké má být správné zvíře: " + souboryKockyPsi.get(j).getZvire());

            if (seznamOdpovedi.get(j).equals("zadna_odpoved")) {
                konecnyVysledek.add(new Hodnoceni(seznamOdpovedi.get(j), souboryKockyPsi.get(j).getZvire(), "NO_ANSWER"));
                pocitadlo++;

            } else {
                souboryKockyPsi.get(j).setZaskrtnuto_nic(false);
                if (seznamOdpovedi.get(j).equals("kocka")) {
                    souboryKockyPsi.get(j).setZaskrtnuta_kocka(true);
                } else {
                    souboryKockyPsi.get(j).setZaskrtnut_pes(true);
                }

                if (seznamOdpovedi.get(j).equals(souboryKockyPsi.get(j).getZvire())) {
                    konecnyVysledek.add(new Hodnoceni(seznamOdpovedi.get(j), souboryKockyPsi.get(j).getZvire(), "CORRECT"));
                } else {
                    konecnyVysledek.add(new Hodnoceni(seznamOdpovedi.get(j), souboryKockyPsi.get(j).getZvire(), "WRONG"));
                }
                System.out.println(konecnyVysledek);
            }

        }
        if (pocitadlo > 0) {
            ModelAndView oprava = new ModelAndView("redirect:/");
            oprava.addObject("seznamFotekKocekPsu", souboryKockyPsi);
            
            oprava.addObject("oprava_nutna", "Nezadali jste všechny odpovědi, zkuste to znovu");
            return oprava;
        } else {
            ModelAndView data = new ModelAndView("vysledek");
            data.addObject("zaver", konecnyVysledek);
            return data;
        }

    }
}

