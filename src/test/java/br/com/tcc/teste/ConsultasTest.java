package br.com.tcc.teste;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tcc.model.VariacaoNumberModel;
import br.com.tcc.model.VariacaoStringModel;
import br.com.tcc.service.ChuvaService;
import br.com.tcc.service.PressaoAtmosfericaService;
import br.com.tcc.service.RotacoesService;
import br.com.tcc.service.SentidoVentoService;
import br.com.tcc.service.TemperaturaService;
import br.com.tcc.service.UmidadeService;
import br.com.tcc.service.VelocidadeVentoService;

@Ignore
public class ConsultasTest extends SpringTest {

    @Inject
    private ChuvaService              chuvaService;

    @Inject
    private UmidadeService            umidadeService;

    @Inject
    private RotacoesService           rotacoesService;

    @Inject
    private TemperaturaService        temperaturaService;

    @Inject
    private SentidoVentoService       sentidoVentoService;

    @Inject
    private VelocidadeVentoService    velocidadeVentoService;

    @Inject
    private PressaoAtmosfericaService pressaoAtmosfericaService;

    private static final Integer      DIAS_ = 41;

    @Test
    public void testeHoras() {

        System.out.println("\n\n-----------------------sentido do vento");
        for (VariacaoStringModel vsm : sentidoVentoService.getVentosHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vsm.getModa());
        }

        System.out.println("\n\n-----------------------chuva");
        for (VariacaoStringModel vsm : chuvaService.getChuvasHorasDiaAnteriorEspecificoDiaAtras(DIAS_)) {
            System.out.print(" " + vsm.getModa());
        }

        System.out.println("\n\n-----------------------umidade");
        for (VariacaoNumberModel vnm : umidadeService.getUmidadesHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vnm.getMax());
        }

        System.out.println("\n\n-----------------------temperatura");
        for (VariacaoNumberModel vnm : temperaturaService.getTemperaturasHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vnm.getMax());
        }

        System.out.println("\n\n-----------------------rotacoes");
        for (VariacaoNumberModel vnm : rotacoesService.getRotacoesHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vnm.getMax());
        }

        System.out.println("\n\n-----------------------velocidade do vento");
        for (VariacaoNumberModel vnm : velocidadeVentoService.getVelocidadesHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vnm.getMax());
        }

        System.out.println("\n\n-----------------------pressao atmosferica");
        for (VariacaoNumberModel vnm : pressaoAtmosfericaService.getPressoesHoraEspecificoDiasAtras(DIAS_)) {
            System.out.print(" " + vnm.getMax());
        }

    }

    @Test
    public void testeDia() {

        System.out.println("\n\n-----------------------sentido do vento");
        VariacaoStringModel vsm = sentidoVentoService.getVentosDiaEspecificoDiaAtras(DIAS_);
        System.out.print(" " + vsm.getModa());

        System.out.println("\n\n-----------------------chuva");
        VariacaoStringModel vcm = chuvaService.getChuvasDiaEspecificoDiaAtras(DIAS_);
        System.out.print(" " + vcm.getModa());

        System.out.println("\n\n-----------------------umidade");
        VariacaoNumberModel vum = umidadeService.getUmidadeEspecificoDiasAtras(DIAS_);
        System.out.print(" " + vum.getMax());

        System.out.println("\n\n-----------------------temperatura");
        VariacaoNumberModel vtm = temperaturaService.getTemperaturasEspecificoDiaAnterior(DIAS_);
        System.out.print(" " + vtm.getMax());

        System.out.println("\n\n-----------------------rotacoes");
        VariacaoNumberModel vrm = rotacoesService.getRotacaoEspecificoDiasAtras(DIAS_);
        System.out.print(" " + vrm.getMax());

        System.out.println("\n\n-----------------------velocidade do vento");
        VariacaoNumberModel vvm = velocidadeVentoService.getVelocidadesEspecificoDiaAtras(DIAS_);
        System.out.print(" " + vvm.getMax());

        System.out.println("\n\n-----------------------pressao atmosferica");
        VariacaoNumberModel vpm = pressaoAtmosfericaService.getPressoesEspecificoDiaAnterior(DIAS_);
        System.out.print(" " + vpm.getMax());

    }
}
