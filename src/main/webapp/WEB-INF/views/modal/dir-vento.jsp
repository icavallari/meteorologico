<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.owl-theme .owl-controls {
    margin-top: 30px;
    text-align: center;
}
.owl-item{
    margin-bottom: 15px;
}
table.table td{
    font-size: 12px;
    padding-right: 0px;
}
.sigla{
    text-align: right;
    padding-right: 0px !important;
}
.cabecalho{
    font-family: "Lucida Grande", "Lucida Sans Unicode", Arial, Helvetica, sans-serif;
    transform-origin: 0px 0px 0px;
    padding-bottom: 10px;
    margin-top: -10px;
    text-align: center;
    color: #333333;
    fill: #333333;
    white-space: nowrap;
    text-anchor: middle;
}
.legenda-rose{
    text-align: center;
}
dl{
    margin-left: 30px;
}
dt {
    width: 10px;
    height: 10px;
    display: inline-block;
    overflow: hidden;
}
.gold {
    background-color: #F2D732;
    color: #F2D732;
}
.gray {
    background-color: #A0A0A0;
    color: #A0A0A0;
}
.bronze {
    background-color: #E9B667;
    color: #E9B667;
}
dd {
    display: inline-block;
    width: 6em;
    text-align: initial;
}
.highcharts-title{
    font-size: 18px;
}
.highcharts-subtitle{
    font-size: 12px;
}
.box{
    border: 1px solid transparent;
    background-color: #F8F8F8;
    border-color: #e7e7e7;
    border-radius: 0;
    box-shadow: 0 8px 17px -2px rgba(0, 0, 0, 0.2);
}
</style>

<div class="row" style="margin: 25px 10px;">
    <div class="col-md-12">

        <c:choose>
            <c:when test="${empty ventos}">
                <div class="mensagem-dado-indisponivel">Ainda não temos
                    informações disponíveis para este gráfico.</div>
            </c:when>
            <c:otherwise>

                <div class="cabecalho">
                    <p class="highcharts-title">
                        Últimas Variações no Sentido de Vento
                    </p>
                    <p class="highcharts-subtitle">As últimas 30 horas captadas.</p>
                </div>

                <div class="multiple-items" style="padding-bottom: 20px">
                    <c:forEach var="vento" items="${ventos}" varStatus="it">
                         <div id="container-rosemodal-${it.index}" class="box chart"
                             style="min-width: 250px; max-width: 250px; height: 250px; margin: 0 35px 0 12px;">
                         </div>
                    </c:forEach>
                </div>

                <div class="legenda-rose">
                    <dl>
                        <dt class="gold"></dt>
                        <dd>1° Moda</dd>
                        <dt class="gray"></dt>
                        <dd>2° Moda</dd>
                        <dt class="bronze"></dt>
                        <dd>3° Moda</dd>
                    </dl>
                    <dl>
                        <dd style="width: 300px; margin-top: -8px;"><small>Os três dados que mais aparecem no intervalo.</small></dd>
                    </dl>
                </div>

                <table class="table">
                <tbody>
                    <tr>
                        <td class="sigla">N </td><td>- Norte</td>
                        <td class="sigla">NNE </td><td>- Norte-Nordeste</td>
                        <td class="sigla">NE </td><td>- Nordeste</td>
                        <td class="sigla">ENE </td><td>- Este-Nordeste</td>
                    </tr>
                    <tr>
                        <td class="sigla">L </td><td>- Leste</td>
                        <td class="sigla">ESE </td><td>- Este-Sudoeste</td>
                        <td class="sigla">SE </td><td>- Sudeste</td>
                        <td class="sigla">SSE </td><td>- Sul-Sudeste</td>
                    </tr>
                    <tr>
                        <td class="sigla">S </td><td>- Sul</td>
                        <td class="sigla">SSO </td><td>- Sul-Sudoeste</td>
                        <td class="sigla">SO </td><td>- Sudoeste</td>
                        <td class="sigla">OSO </td><td>- Oeste-Sudoeste</td>
                    </tr>
                    <tr>
                        <td class="sigla">O </td><td>- Oeste</td>
                        <td class="sigla">ONO </td><td>- Oeste Noroeste</td>
                        <td class="sigla">NO </td><td>- Noroeste</td>
                        <td class="sigla">NNO </td><td>- Norte-Noroeste</td>
                    </tr>
                </tbody>
            </table>

            </c:otherwise>
        </c:choose>

    </div>
</div>

<c:if test="${not empty ventos}">

    <script type="text/javascript">
        $(function() {
            <c:forEach var="vento" items="${ventos}" varStatus="it2">
                $('#container-rosemodal-${it2.index}').highcharts({
                    chart: { type : 'column', backgroundColor : '#F8F8F8', polar: true },
                    pane : { size : '80%', background : {backgroundColor : 'transparent' }},
                    title : { text : 'Dia ${vento.dataString}', },
                    credits : { enabled : false },
                    exporting : { enabled : false },
                    data : { startRow : 1, endRow : 10, endColumn : 7, },
                    yAxis: { min: 0, endOnTick: false, showLastLabel: false, labels: { enabled :false } },
                    xAxis: { tickmarkPlacement : 'on',
                    categories: [${direcoes}], labels: { formatter: function () { return this.value + ''; } } }, legend : { enabled : false },
                    tooltip : { formatter : function() { return false; } },
                    plotOptions : { series : { stacking : 'normal', shadow : false, groupPadding : 0, pointPlacement : 'on' } },
                    series: [

                    <c:if test="${vento.direcaoVentoModa ne 'null'}">
                        { color: '#F2D732', data: [${vento.direcaoVentoModa}] }
                    </c:if>
                    <c:if test="${vento.direcaoVentoMediana ne 'null'}">
                        ,{ color: '#A0A0A0', data: [${vento.direcaoVentoMediana}] }
                    </c:if>
                    <c:if test="${vento.direcaoVentoMedia ne 'null'}">
                        ,{ color: '#E9B667', data: [${vento.direcaoVentoMedia}] }
                    </c:if>

                    ]
                });
            </c:forEach>

            var owl = $(".multiple-items");
            owl.owlCarousel({
                dots : true,
                items : 3,
                itemsDesktop : [ 1000, 5 ],
                itemsDesktopSmall : [ 900, 3 ],
                itemsTablet : [ 600, 2 ],
                itemsMobile : false,
                autoplayHoverPause: true
            });
            owl.trigger('owl.play', 5000);

        });
    </script>
</c:if>