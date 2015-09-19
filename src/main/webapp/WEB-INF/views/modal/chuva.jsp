<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        <c:choose>
            <c:when test="${empty chuvas}">
                <div class="mensagem-dado-indisponivel">Ainda não temos
                    informações disponíveis para este gráfico.</div>
            </c:when>
            <c:otherwise>
                <div id="container" style="height: 400px;" class="chart"></div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

    <script>
        $(function() {
            $('#container').highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: 'Últimas Variações de Chuva & Temperatura Média'
                },
                subtitle: {
                    text: 'As últimas 30 horas captadas.'
                },
                xAxis: {
                    categories: [
                        <c:forEach var="ch" items="${chuvas}" varStatus="it">
                            '${ch.dataString}'${!it.last ? ',' : '' }
                        </c:forEach>
                        ],
                    tickInterval: 5,
                    tickWidth: 1
                },
                yAxis: {
                    title: {
                        text: 'Temperatura'
                    },
                    labels: {
                        format: '{value: .0f}°C'
                    }
                },
                tooltip: {
                    crosshairs: {
                        width: 1,
                        color: '#999'
                    },
                    shared: true,
                    valueSuffix: '°C',
                    useHTML: true,
                    headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
                },
                plotOptions: {
                    spline: {
                        marker: {
                            radius: 4,
                            lineColor: '#666666',
                            lineWidth: 1
                        }
                    }
                },
                series: [{
                    name: 'Temperatura Média',
                    marker: {
                        symbol: 'square'
                    },
                    data: [

                       <c:forEach var="ch" items="${chuvas}" varStatus="it">
                           <c:set var="img" value="icon-${ch.chuva}.png" />

                          {  y : ${ch.temperatura},
                                 marker: {
                                        symbol: 'url(<c:url value="/resources/icon/${img}" />)'
                                  }
                          } ${!it.last ? ',' : '' }
                        </c:forEach>
                    ]

                }]
            });
        });
    </script>