<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        <c:choose>
            <c:when test="${empty rotacoes}">
                <div class="mensagem-dado-indisponivel">Desculpe, não houve variações neste intervalo. </div>
            </c:when>
            <c:otherwise>
                <div id="container" style="height: 400px;" class="chart"></div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<c:if test="${not empty rotacoes}">

<script>

    $(function() {

        var ranges = [
                    <c:forEach var="rot" items="${rotacoes}" varStatus="it">
                        ['${rot.dataString}', ${rot.min}, ${rot.max}]${!it.last ? ',' : '' }
                    </c:forEach>
                  ],
          averages = [
                    <c:forEach var="rot" items="${rotacoes}" varStatus="it">
                        ['${rot.dataString}', ${rot.med}, ]${!it.last ? ',' : '' }
                    </c:forEach>
                  ];

        $('#container').highcharts(
                {

                    title: {
                        text: 'Últimas Variações de Rotações'
                    },
                    subtitle: {
                        text: 'As últimas 30 horas captadas.'
                    },

                    xAxis: {
                        type: 'category',
                        tickInterval: 5,
                        tickWidth: 1
                    },

                    yAxis: {
                        title: {
                            text: ''
                        },
                        labels: {
                            format: '{value: .1f} * 1000 / Rpm'
                        },
                    },

                    tooltip: {
                        crosshairs: {
                            width: 1,
                            color: '#999'
                        },
                        valueDecimals: 1,
                        shared: true,
                        valueSuffix: ' r/min',
                        useHTML: true,
                        headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
                    },

                    legend: {
                    },

                    series: [{
                        name: 'Rotações por Minuto',
                        data: averages,
                        tooltip: {
                            format: '{value:.1f}'
                        },
                        zIndex: 1,
                        marker: {
                            fillColor: 'white',
                            lineWidth: 2,
                            lineColor: Highcharts.getOptions().colors[0]
                        }
                    }, {
                        name: 'Variação',
                        data: ranges,
                        type: 'arearange',
                        lineWidth: 0,
                        linkedTo: ':previous',
                        color: Highcharts.getOptions().colors[0],
                        fillOpacity: 0.3,
                        zIndex: 0
                    }]

                });
    });
</script>

</c:if>