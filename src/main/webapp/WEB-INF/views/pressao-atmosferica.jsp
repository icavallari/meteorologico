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
                    <c:forEach var="pre" items="${pressoes}" varStatus="it">
                        [${pre.dataLong}, ${pre.media}, ]${!it.last ? ',' : '' }
                    </c:forEach>
                ],
                ranges = [
                   <c:forEach var="pre" items="${pressoes}" varStatus="it">
                           [${pre.dataLong}, ${pre.minima}, ${pre.maxima}]${!it.last ? ',' : '' }
                   </c:forEach>
               ];


    $('#container').highcharts('StockChart',{

        title: {
            text: 'Pressão Atmosférica'
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
                format: '{value: .0f}Pa'
            },
        },

        tooltip: {
            xDateFormat: '%e - %b - %Y',
            crosshairs: true,
            shared: true,
            valueSuffix: 'Pa',
            useHTML: true,
            headerFormat: '<b><u><span font-size:15px">{point.key}</span></u></b><br>'
        },

        legend: {
        },

        series: [{
            name: 'Pressão',
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