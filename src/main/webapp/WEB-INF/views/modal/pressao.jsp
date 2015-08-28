<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        <c:choose>
            <c:when test="${empty pressoes}">
                <div class="mensagem-dado-indisponivel">Ainda não temos informações disponíveis para este gráfico. </div>
            </c:when>
            <c:otherwise>
                <div id="container" style="height: 400px;" class="chart"></div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<c:if test="${not empty pressoes}">

<script>

    $(function() {

        var ranges = [
                    <c:forEach var="pre" items="${pressoes}" varStatus="it">
                        ['${pre.dataString}', ${pre.min}, ${pre.max}]${!it.last ? ',' : '' }
                    </c:forEach>
                  ],
          averages = [
                    <c:forEach var="pre" items="${pressoes}" varStatus="it">
                        ['${pre.dataString}', ${pre.med}, ]${!it.last ? ',' : '' }
                    </c:forEach>
                  ];

        $('#container').highcharts(
                {

                    title: {
                        text: 'Últimas Variações de Pressão Atmosférica'
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
                            format: '{value: .0f}Pa'
                        },
                    },

                    tooltip: {
                        crosshairs: {
                            width: 1,
                            color: '#999'
                        },
                        shared: true,
                        valueSuffix: 'Pa',
                        useHTML: true,
                        headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
                    },

                    legend: {
                    },

                    series: [{
                        name: 'Pressão Atmosférica',
                        data: averages,
                        tooltip: {
                            format: '{value:.0f}'
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