package br.com.tcc.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.entidade.Altitude;
import br.com.tcc.entidade.Chuva;
import br.com.tcc.entidade.PressaoAtmosferica;
import br.com.tcc.entidade.Rotacao;
import br.com.tcc.entidade.SentidoVento;
import br.com.tcc.entidade.Temperatura;
import br.com.tcc.entidade.Umidade;
import br.com.tcc.entidade.Velocidade;
import br.com.tcc.service.Query;

@Controller
@RequestMapping(value = "/listagem")
public class ListagemController {

    // total de minutos por dia
    private static final Integer totalItensPagina = 300;

    @Inject
    private Query                query;

    @RequestMapping(value = "/altitude")
    public ModelAndView getAltitudes(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipo", "Altitude")
            .addObject("tipoDado", " metros")
            .addObject("objects",
                query.getListObject(new Altitude(), "select * from altitude order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/chuva")
    public ModelAndView getChuvas(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipo", "Chuva")
            .addObject("tipoDado", "")
            .addObject("objects",
                query.getListObject(new Chuva(), "select * from chuva order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/pressao-atmosferica")
    public ModelAndView getPressoes(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipoDado", " Pa")
            .addObject("tipo", "Pressao")
            .addObject(
                "objects",
                query.getListObject(new PressaoAtmosferica(), "select * from pressaoatmosferica order by data desc limit "
                    + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/rotacao")
    public ModelAndView getRotacoes(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipo", "Rotacao")
            .addObject("tipoDado", " rpm")
            .addObject("objects",
                query.getListObject(new Rotacao(), "select * from rotacao order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/direcao-do-vento")
    public ModelAndView getVentos(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipo", "Vento")
            .addObject("tipoDado", "")
            .addObject("objects",
                query.getListObject(new SentidoVento(), "select * from sentidovento order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/temperatura")
    public ModelAndView getTemperaturas(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipoDado", "º Celsius")
            .addObject("tipo", "Temperatura")
            .addObject("objects",
                query.getListObject(new Temperatura(), "select * from temperatura order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/umidade")
    public ModelAndView getUmidades(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipo", "Umidade")
            .addObject("objects",
                query.getListObject(new Umidade(), "select * from umidade order by data desc limit " + totalItensPagina + ";"));
    }

    @RequestMapping(value = "/velocidade")
    public ModelAndView getVelocidades(HttpServletRequest r) {
        return new ModelAndView("listagem")
            .addObject("tipoDado", " Km/h")
            .addObject("tipo", "Velocidade")
            .addObject("objects",
                query.getListObject(new Velocidade(), "select * from velocidade order by data desc limit " + totalItensPagina + ";"));
    }

    /**
     * Chamadas ajax para buscar mais dados
     */

    @RequestMapping(value = "/altitude", method = RequestMethod.POST)
    public ModelAndView getAltitudesPost(@RequestParam("pagina") Integer pagina) {

        return new ModelAndView("listagem-content")
            .addObject("tipo", "Altitudes")
            .addObject("tipoDado", " metros")
            .addObject(
                "objects",
                query.getListObject(new Altitude(), "select * from altitude order by data desc limit " + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/chuva", method = RequestMethod.POST)
    public ModelAndView getChuvasPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipo", "Chuvas")
            .addObject("tipoDado", "")
            .addObject("objects",
                query.getListObject(new Chuva(), "select * from chuva order by data desc limit " + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/pressao-atmosferica", method = RequestMethod.POST)
    public ModelAndView getPressoesPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipoDado", " Pa")
            .addObject("tipo", "Pressões Atmosférica")
            .addObject(
                "objects",
                query.getListObject(new PressaoAtmosferica(), "select * from pressaoatmosferica order by data desc limit "
                    + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/rotacao", method = RequestMethod.POST)
    public ModelAndView getRotacoesPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipo", "Rotações")
            .addObject("tipoDado", " rpm")
            .addObject("objects",
                query.getListObject(new Rotacao(), "select * from rotacao order by data desc limit " + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/direcao-do-vento", method = RequestMethod.POST)
    public ModelAndView getVentosPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipo", " Sentido do Vento")
            .addObject("tipoDado", "")
            .addObject(
                "objects",
                query.getListObject(new SentidoVento(), "select * from sentidovento order by data desc limit " + totalItensPagina
                    + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/temperatura", method = RequestMethod.POST)
    public ModelAndView getTemperaturasPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipoDado", "º Celsius")
            .addObject("tipo", "Temperatura")
            .addObject(
                "objects",
                query.getListObject(new Temperatura(), "select * from temperatura order by data desc limit " + totalItensPagina
                    + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/umidade", method = RequestMethod.POST)
    public ModelAndView getUmidadesPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipo", " Umidades do Ar")
            .addObject("objects",
                query.getListObject(new Umidade(), "select * from umidade order by data desc limit " + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    @RequestMapping(value = "/velocidade", method = RequestMethod.POST)
    public ModelAndView getVelocidadesPost(@RequestParam("pagina") Integer pagina) {
        return new ModelAndView("listagem-content")
            .addObject("tipoDado", " Km/h")
            .addObject("tipo", "Velocidades")
            .addObject("objects",
                query.getListObject(new Velocidade(), "select * from velocidade order by data desc limit " + totalItensPagina + " offset "
                    + calcularOffset(pagina) + ";"));
    }

    private Long calcularOffset(int pagina) {
        return new Long(pagina * totalItensPagina - totalItensPagina);
    }

}
