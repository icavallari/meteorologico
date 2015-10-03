package br.com.tcc.controller;

import java.text.ParseException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.entidade.DirecoesVento;
import br.com.tcc.model.DadosClimaModel;
import br.com.tcc.service.ChuvaService;
import br.com.tcc.service.DadosClimaService;
import br.com.tcc.service.PressaoAtmosfericaService;
import br.com.tcc.service.RotacoesService;
import br.com.tcc.service.SentidoVentoService;
import br.com.tcc.service.TemperaturaService;
import br.com.tcc.service.UmidadeService;
import br.com.tcc.service.VelocidadeVentoService;
import br.com.tcc.serviceimpl.Medicoes;

@Controller
@RequestMapping("/")
public class HomeController {

    @Inject
    private Medicoes refazer;

    @Inject
    private DadosClimaService estacaoAntigaService;

    @Inject
    private TemperaturaService temperaturaService;

    @Inject
    private UmidadeService umidadeService;

    @Inject
    private PressaoAtmosfericaService pressaoAtmosfericaService;

    @Inject
    private ChuvaService chuvaService;

    @Inject
    private SentidoVentoService sentidoVentoService;

    @Inject
    private RotacoesService rotacoesService;

    @Inject
    private VelocidadeVentoService velocidadeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getNewHome() throws ParseException {
        return new ModelAndView("home")
            .addObject("direcoes", DirecoesVento.todas())
            .addObject("dados", estacaoAntigaService.getUltimosValores());
    }

    @RequestMapping("/getjson")
    public @ResponseBody
    DadosClimaModel getJson() throws ParseException {
        return estacaoAntigaService.getUltimosValores();
    }

    @RequestMapping("/getstring")
    public @ResponseBody
    String getString() throws ParseException {

        DadosClimaModel e = estacaoAntigaService.getUltimosValores();

        return "###p" + e.getPressao() + ",a" + e.getAltitude() + ",r" + e.getRotacao() +
            ",v" + e.getVelocidade() + ",s" + e.getSentidoVento() + ",u" + e.getUmidade() +
            ",t" + e.getChuva() + ",c" + e.getChuva() + "###";
    }

    // modal de visualização rapida com as ultimas 30 horas da medicao_hora
    @RequestMapping(value = "/modal-temperatura", method = RequestMethod.GET)
    public ModelAndView getModalTemperatura() throws ParseException {
        return new ModelAndView("modal/temperatura")
            .addObject("temperaturas", temperaturaService.getUltimasHoras(30));
    }

    // modal de visualização rapida com as ultimas 30 horas da medicao_hora
    @RequestMapping(value = "/modal-umidade", method = RequestMethod.GET)
    public ModelAndView getModalUmidade() throws ParseException {
        return new ModelAndView("modal/umidade")
            .addObject("umidades", umidadeService.getUltimasHoras(30));
    }

    // modal de visualização rapida com as ultimas 30 horas da medicao_hora
    @RequestMapping(value = "/modal-pressao", method = RequestMethod.GET)
    public ModelAndView getModalPressao() throws ParseException {
        return new ModelAndView("modal/pressao")
            .addObject("pressoes", pressaoAtmosfericaService.getUltimasHoras(30));
    }

    // modal de visualização rapida com as ultimas 30 horas da medicao_hora
    @RequestMapping(value = "/modal-chuva", method = RequestMethod.GET)
    public ModelAndView getModalChuva() throws ParseException {
        return new ModelAndView("modal/chuva")
            .addObject("chuvas", chuvaService.getChuvaTemperaturaUltimasHoras(30));
    }

    // modal de visualização rapida com as ultimas 30 horas da medicao_hora
    @RequestMapping(value = "/modal-dir-vento", method = RequestMethod.GET)
    public ModelAndView getModalDirecaoVento() throws ParseException {
        return new ModelAndView("modal/dir-vento")
            .addObject("direcoes", DirecoesVento.todas())
            .addObject("ventos", sentidoVentoService.getVentosUltimasHoras(30));
    }

    @RequestMapping(value = "/modal-rotacao", method = RequestMethod.GET)
    public ModelAndView getModalRotacao() throws ParseException {
        return new ModelAndView("modal/rotacao")
            .addObject("rotacoes", rotacoesService.getUltimasHoras(30));
    }

    @RequestMapping(value = "/modal-velocidade", method = RequestMethod.GET)
    public ModelAndView getModalVelocidade() throws ParseException {
        return new ModelAndView("modal/velocidade")
            .addObject("velocidades", velocidadeService.getUltimasHoras(30));
    }

    // ----------------------------------------------------------------------------------------
    // páginas individuais

    @RequestMapping("/temperatura")
    public ModelAndView getTemperaturas() {
        return new ModelAndView("temperatura")
            .addObject("temperaturas", temperaturaService.getTemperaturasTodosOsDias());
    }

    @RequestMapping("/umidade")
    public ModelAndView getUmidades() {
        return new ModelAndView("umidade")
            .addObject("umidades", umidadeService.getUmidadesTodosOsDias());
    }

    @RequestMapping("/pressao")
    public ModelAndView getPressoes() {
        return new ModelAndView("pressao-atmosferica")
            .addObject("pressoes", pressaoAtmosfericaService.getPressoesTodosOsDias());
    }

    // @RequestMapping(value = "/tabela-auxiliar/refazer/teste", method =
    // RequestMethod.GET)
    public ModelAndView refazerTabelasAuxiliares() throws ParseException {

        refazer.insereHorasNaTabelaHora();
        refazer.insereDiaNaTabelaDia();
        refazer.deletarDados30Dias();

        return getNewHome();
    }

}
