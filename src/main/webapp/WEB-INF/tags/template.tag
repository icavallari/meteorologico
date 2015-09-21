<%@tag description="Main template for pages" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="pt">
<head>
<meta charset="utf-8" />
<link rel="shortcut icon"
    href="<c:url value='/resources/icon/logo.png' />">
<title>Sistema Meteorológico IFSP - Campus Salto</title>
<link
    href="<c:url value="/resources/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>"
    rel="stylesheet">
<link
    href="<c:url value="/resources/font-awesome-4.3.0/css/font-awesome.min.css"/>"
    rel="stylesheet">
<link
    href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
    rel='stylesheet' type='text/css'>

<style type="text/css">
body {
    font-family: Merriweather, 'Helvetica Neue', Arial, sans-serif;
}

.navbar-default .navbar-nav>.open>a,.navbar-default .navbar-nav>.open>a:hover,.navbar-default .navbar-nav>.open>a:focus
    {
    background-color: #F8F8F8
}

.rodape {
    margin-top: 20px;
    background-color: #F8F8F8;
    border: solid 1px #e7e7e7;
}

.rodape .row {
    margin: 60px 0 20px 0;
}

.rodape i {
    font-size: 90px;
    color: #67B82D;
    text-shadow: 2px 2px 2px #ccc;
}

.tit {
    font-family: Merriweather, 'Helvetica Neue', Arial, sans-serif;
    font-size: 30px;
    text-align: center;
    color: #666;
}

.city {
    font-family: Merriweather, 'Helvetica Neue', Arial, sans-serif;
    font-size: 30px;
    color: #666;
}

.temper {
    font-family: Merriweather, 'Helvetica Neue', Arial, sans-serif;
    font-size: 40px;
    color: #666;
    text-indent: 60px;
    margin-top: -15px;
}

.subtit {
    font-family: Merriweather, 'Helvetica Neue', Arial, sans-serif;
    margin-bottom: 20px;
    font-size: 16px;
    line-height: 1.5;
    text-align: center;
}
</style>
</head>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-2.1.3.min.js"/> "></script>
<c:if test="${empty tipo}">
    <script type="text/javascript" src="<c:url value="/resources/highstock/highstock.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/highcharts/highcharts-more.js"/> "></script>
    <script type="text/javascript" src="<c:url value="/resources/highcharts/exporting.js"/> "></script>
    <script type="text/javascript" src="<c:url value="/resources/highcharts/modules/solid-gauge.js"/> "></script>
</c:if>
<body>

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand"><img
                    style="width: 169px; margin-top: -12px;"
                    src="<c:url value="/resources/icon/logoif.png"/> "></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="${empty tipo ? 'active' : '' }"><a href="/">Sistema
                            Meteorológico</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                        data-toggle="dropdown" role="button" aria-expanded="false">Clique
                            e veja mais <span class="caret"></span>
                    </a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="disabled"><a href="#">Intensidade de Chuva</a></li>
                            <li><a href="/pressao">Pressão Atmosférica</a></li>
                            <li><a href="/temperatura">Temperatura</a></li>
                            <li><a href="/umidade">Umidade Relativa do Ar</a></li>
                            <li class="divider"></li>
                            <li class="disabled"><a href="#">Sentido do Vento</a></li>
                            <li class="disabled"><a href="#">Rotações por Minuto</a></li>
                            <li class="disabled"><a href="#">Velocidade do Vento</a></li>
                            <!--                             <li class="divider"></li> -->
                            <!--                             <li><a href="#">Altitude</a></li> -->
                        </ul></li>
                    <li class="${not empty tipo ? 'active' : '' }"><a
                        href="/listagem/altitude">Listagem</a></li>
                </ul>

                <c:if test="${not empty tipo }">
                    <!--                     <ul class="nav navbar-nav navbar-right"> -->
                    <%--                         <li><a> ${tipo} </a></li> --%>
                    <!--                     </ul> -->
                </c:if>

                <c:if test="${not empty dados.dataCaptura }">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a>Captado às <fmt:formatDate pattern="HH:mm:ss"
                                    value="${dados.dataCaptura}" /> hrs
                        </a></li>
                    </ul>
                </c:if>
            </div>
        </div>
    </nav>

    <jsp:doBody />

    <div class="rodape">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="tit">Gráficos Interativos sobre dados
                        Climáticos </span>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 text-center">
                    <i class="fa fa-pie-chart"></i>
                </div>
                <div class="col-md-3 text-center">
                    <i class="fa fa-bar-chart"></i>
                </div>
                <div class="col-md-3 text-center">
                    <i class="fa fa-area-chart"></i>
                </div>
                <div class="col-md-3 text-center">
                    <i class="fa fa-line-chart"></i>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="subtit">Sistema Meteorológico IFSP - Campus
                        Salto - 2015</span>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript"
        src="<c:url value="/resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"/> "></script>

    <script type="text/javascript">
        window.onscroll = function(ev) {
            var top = window.pageYOffset || document.documentElement.scrollTop;
            if (top > 5) {
                $('nav').addClass("shadow-nav");
            } else {
                $('nav').removeClass("shadow-nav");
            }
        };
    </script>

</body>
</html>
