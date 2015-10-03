<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        <c:choose>
            <c:when test="${empty velocidades}">
                <div class="mensagem-dado-indisponivel">Desculpe, não houve variações neste intervalo. </div>
            </c:when>
            <c:otherwise>
                <div id="container" style="height: 400px;" class="chart"></div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<c:if test="${not empty velocidades}">

<script>
$(function () {
    $('#container').highcharts({
        chart: { type: 'spline' }, title: { text: 'Últimas Variações de Velocidade do Vento' },
        subtitle: { text: 'As últimas 30 horas captadas.' },
        xAxis: {
            categories: [
                <c:forEach var="v" items="${velocidades}" varStatus="it">
                    '${v.dataString}'${!it.last ? ',' : '' }
                </c:forEach>
                ],
            tickInterval: 5,
            tickWidth: 1
        },
        yAxis: {
            title: {
                text: 'Velocidade do Vento (Km/h)'
            },
            min: 0,
            minorGridLineWidth: 0,
            gridLineWidth: 0,
            alternateGridColor: null,
            plotBands: [{
                from: 0,
                to: 11,
                color: 'rgba(68, 170, 213, 0.1)',
                label: { text: 'Brisa Leve', style: { color: '#000' } }
            }, {
                from: 12,
                to: 19,
                color: 'rgba(0, 0, 0, 0)',
                label: { text: 'Brisa Fraca', style: { color: '#000' } }
            }, {
                from: 20,
                to: 28,
                color: 'rgba(68, 170, 213, 0.1)',
                label: { text: 'Brisa Moderada', style: { color: '#000' } }
            }, {
                from: 29,
                to: 38,
                color: 'rgba(0, 0, 0, 0)',
                label: { text: 'Brisa Forte', style: { color: '#000' } }
            }, {
                from: 39,
                to: 49,
                color: 'rgba(68, 170, 213, 0.1)',
                label: { text: 'Vento Fresco', style: { color: '#000' } }
            }, {
                from: 50,
                to: 61,
                color: 'rgba(0, 0, 0, 0)',
                label: { text: 'Vento Forte', style: { color: '#000' } }
            }, {
                from: 62,
                to: 74,
                color: 'rgba(68, 170, 213, 0.1)',
                label: { text: 'Ventania', style: { color: '#000' } }
            }, {
                from: 75,
                to: 88,
                color: 'rgba(0, 0, 0, 0)',
                label: { text: 'Ventania Forte', style: { color: '#000' } }
            }, {
                from: 89,
                to: 102,
                color: 'rgba(68, 170, 213, 0.1)',
                label: { text: 'Tempestade', style: { color: '#000' } }
            }, {
                from: 103,
                to: 117,
                color: 'rgba(0, 0, 0, 0)',
                label: { text: 'Tempestade Violenta', style: { color: '#000' } }
            }]
        },
        tooltip: {
            crosshairs: {
                width: 1,
                color: '#999'
            },
            valueDecimals: 1,
            shared: true,
            valueSuffix: ' Km/h',
            useHTML: true,
            headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
        },
        plotOptions: { spline: { marker: { radius: 4, lineColor: '#666666', lineWidth: 0.2 } } },
        series: [{
            name: 'Velocidade do Vento',
            data: [

                    <c:forEach var="v" items="${velocidades}" varStatus="it">
                        ${v.med}${!it.last ? ',' : '' }
                    </c:forEach>

                  ]
        }]
    });
});
</script>

</c:if>