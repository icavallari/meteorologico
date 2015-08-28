<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<link href="<c:url value="/resources/css/template.css"/>"
    rel="stylesheet">

<t:template>
    <div class="content container">
        <div id="container" style="width: 100%; height: 400px; margin: 0 auto"></div>
    </div>

    <script type="text/javascript">

    window.onscroll = function(ev) {
        var top = window.pageYOffset || document.documentElement.scrollTop;
        if (top > 5) {
            $('nav').addClass("shadow-nav");
        } else {
            $('nav').removeClass("shadow-nav");
        }
    };

$(function () {

    var averages = [
                    <c:forEach var="umi" items="${umidades}" varStatus="it">
                        [${umi.dataLong}, ${umi.media}, ]${!it.last ? ',' : '' }
                    </c:forEach>
                ],
                ranges = [
                   <c:forEach var="umi" items="${umidades}" varStatus="it">
                   [${umi.dataLong}, ${umi.minima}, ${umi.maxima}]${!it.last ? ',' : '' }
                   </c:forEach>
               ];


    $('#container').highcharts('StockChart',{

        title: {
            text: 'Umidade Relativa do Ar'
        },

        rangeSelector: {
            selected: 0
        },

        xAxis: {
            type: 'datetime'
        },

        yAxis: {
            title: {
                text: null
            },
            labels: {
                format: '{value}%'
            },
        },

        tooltip: {
            xDateFormat: '%e - %b - %Y',
            crosshairs: true,
            shared: true,
            valueSuffix: '%',
            useHTML: true,
            headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
        },

        legend: {
        },

        series: [{
            name: 'Umidades',
            data: averages,
            zIndex: 1,
            marker: {
                fillColor: 'white',
                lineWidth: 2,
                lineColor: Highcharts.getOptions().colors[0]
            }
        }, {
            name: 'Range',
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
</t:template>