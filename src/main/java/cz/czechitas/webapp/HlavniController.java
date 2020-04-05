package cz.czechitas.webapp;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class HlavniController {
    private List<FotkaZvirete> souboryKockyPsi;

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
        for (Resource cesta : cestyKSouborum) {
            String druhZvirete = urciZvire(cesta.getFilename());
            System.out.println(druhZvirete);
            souboryKockyPsi.add(new FotkaZvirete(cesta.getFilename(), druhZvirete));
            System.out.println(cesta.getFilename());
        }
        Collections.shuffle(souboryKockyPsi);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex(ModelMap predvyplnenyDrzakNaData) {
        predvyplnenyDrzakNaData.putIfAbsent("formular", new IndexForm());
        ModelAndView data = new ModelAndView("index");
        data.addObject("seznamFotekKocekPsu", souboryKockyPsi);
        return data;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujIndex(@Valid @ModelAttribute("formular") IndexForm vstup,
                                     BindingResult validacniChyby,
                                     RedirectAttributes flashScope) {
        if (validacniChyby.hasErrors()) {
            ModelAndView data = new ModelAndView("redirect:/");
            flashScope.addFlashAttribute("formular", vstup);
            flashScope.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formular", validacniChyby);
            flashScope.addFlashAttribute("oprava_nutna", "Nezadali jste všechny odpovědi, doplňte to.");
            return data;
        }

        ModelAndView data = new ModelAndView("vysledek");
        List<Hodnoceni> konecnyVysledek = new ArrayList<>();
        for (int j = 0; j < souboryKockyPsi.size(); j++) {
            if (vstup.getObrazek().get(j).equals(souboryKockyPsi.get(j).getZvire())) {
                konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "CORRECT"));
            } else if (vstup.getObrazek().get(j).equals("zadna_odpoved")) {
                konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "NO_ANSWER"));
            } else {
                konecnyVysledek.add(new Hodnoceni(vstup.getObrazek().get(j), souboryKockyPsi.get(j).getZvire(), "WRONG"));
            }
            System.out.println(konecnyVysledek);
        }
        data.addObject("zaver", konecnyVysledek);
        return data;
    }
}
