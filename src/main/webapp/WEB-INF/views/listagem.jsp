<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<link href="<c:url value="/resources/css/template.css"/>"
    rel="stylesheet">

<style type="text/css">
td {
    text-align: center;
}

div.sub-menu {
    font-size: 16px;
    margin-bottom: 20px;
    overflow: auto;
}

.sub-menu a {
    color: #333;
    cursor: pointer;
    -webkit-transition: all 0.5s ease; /* Safari and Chrome */
    -moz-transition: all 0.5s ease; /* Firefox */
    -o-transition: all 0.5s ease; /* IE 9 */
    -ms-transition: all 0.5s ease; /* Opera */
    transition: all 0.5s ease;
    max-width: 100%;
}

.sub-menu a:HOVER {
    color: #67B82D;
    cursor: pointer;
    text-decoration: none;
    -webkit-transform: scale(1.15); /* Safari and Chrome */
    -moz-transform: scale(1.15); /* Firefox */
    -ms-transform: scale(1.15); /* IE 9 */
    -o-transform: scale(1.15); /* Opera */
    transform: scale(1.15);
}

.sub-menu {
    border-bottom: solid 1px #67B82D;
}

.botao-flutuante {
    position: fixed;
    bottom: 20px;
    right: 20px;
}

.btn.btn-success {
      border-radius: 27px !important;
      padding: 14px;
    font-size: 25px;
    font-weight: bold;
    border-radius: 0;
    box-shadow: 0px 0px 8px 1px #ccc;
    background-color: #68B82D;
}

a.active-m {
    color: #67B82D;
    text-decoration: none;
}
</style>

<t:template>
    <div class="content container">
        <div class="row">
            <div class="col-md-12" style="color: #000">

                <div class="text-center sub-menu">
                    <a href="/listagem/altitude"
                        class="${tipo eq 'Altitude' ? 'active-m' : ''}">Altitude</a> - <a
                        href="/listagem/chuva"
                        class="${tipo eq 'Chuva' ? 'active-m' : ''}">Chuva</a> - <a
                        href="/listagem/direcao-do-vento"
                        class="${tipo eq 'Vento' ? 'active-m' : ''}">Direção
                        do Vento</a> - <a href="/listagem/pressao-atmosferica"
                        class="${tipo eq 'Pressao' ? 'active-m' : ''}">Pressão
                        Atmosférica</a> - <a href="/listagem/rotacao"
                        class="${tipo eq 'Rotacao' ? 'active-m' : ''}">Rotação</a> - <a
                        href="/listagem/temperatura"
                        class="${tipo eq 'Temperatura' ? 'active-m' : ''}">Temperatura</a> - <a
                        href="/listagem/umidade"
                        class="${tipo eq 'Umidade' ? 'active-m' : ''}">Umidade</a> - <a
                        href="/listagem/velocidade"
                        class="${tipo eq 'Velocidade' ? 'active-m' : ''}">Velocidade</a>
                </div>
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <td><b>Valor Capturado</b></td>
                            <td><b>Data de Captura</b></td>
                        </tr>
                    </thead>
                    <tbody>
                        <%@include file="listagem-content.jsp"%>
                        <tr id="mais-content"></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="botao-flutuante" id="bt-up">
        <a href="" class="btn btn-success" title="Voltar ao topo"><span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span> </a>
    </div>
</t:template>
<script>
    $('#bt-up').hide();
    var pagina = 1;
    var deveBuscar = true;

    window.onscroll = function(ev) {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight
                && deveBuscar) {
            buscarMais();
        }

        var top = window.pageYOffset || document.documentElement.scrollTop;
        if (top > 1000) {
            $('#bt-up').fadeIn(600);
        } else {
            $('#bt-up').fadeOut(600);
        }

        if (top > 5) {
            $('nav').addClass("shadow-nav");
        } else {
            $('nav').removeClass("shadow-nav");
        }

    };

    function buscarMais() {
        pagina++;
        var url = $(location).attr('href') + "?pagina=" + pagina;

        $.post(url, function(data) {
            $(data).insertBefore("#mais-content");
            if (data.trim().length == 0) {
                deveBuscar = false;
            }
        });
    }

    $('.btn.btn-success').click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 1000, 'linear');
        return false;
    });
</script>