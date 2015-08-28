package br.com.tcc.controller;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class MeteorologicoMock {

    Random                     random  = new Random();

    List<String>               chuvas  = Arrays.asList(
                                           "sem-chuva",
                                           "baixa-intensidade",
                                           "intensidade-moderada",
                                           "alta-intensidade");

    List<String>               ventos  = Arrays.asList(
                                           "NORTE",
                                           "NOR-NORDESTE",
                                           "NORDESTE",
                                           "ES-NORDESTE",
                                           "LESTE",
                                           "ES-SUDESTE",
                                           "SUDESTE",
                                           "SU-SUDESTE",
                                           "SUL",
                                           "SU-SUDOESTE",
                                           "SUDOESTE",
                                           "OES-SUDOESTE",
                                           "OESTE",
                                           "OES-NOROESTE",
                                           "NOROESTE",
                                           "NOR-NOROESTE");

    private static final Float tempMin = 10f;
    private static final Float tempMax = 42f;

    private static final Float velMin  = 5f;
    private static final Float velMax  = 120f;

    private static final Float rotMin  = 01f;
    private static final Float rotMax  = 12f;

    private static final Float umiMin  = 20f;
    private static final Float umiMax  = 60f;

    /**
     * Simula dados aleatóriamente
     *
     * @return
     */
    @RequestMapping("/teste/protocolo")
    public ModelAndView getProtocolo() {
        return new ModelAndView("protocolo")
            .addObject(
                "string",
                "###p" + random.nextInt(90000)
                    + ",a455.61,r"
                    + format(random.nextFloat() * (rotMax - rotMin) + rotMin) + ",v"
                    + format(random.nextFloat() * (velMax - velMin) + velMin) + ",s"
                    + ventos.get(random.nextInt(16)) + ",u"
                    + format(random.nextFloat() * (umiMax - umiMin) + umiMin) + ",t"
                    + format(random.nextFloat() * (tempMax - tempMin) + tempMin) + ",c"
                    + chuvas.get(random.nextInt(4)) + "###");
    }

    public Float format(float f) {
        DecimalFormat df = new DecimalFormat("#######");
        return Float.parseFloat(df.format(f));
    }
}
