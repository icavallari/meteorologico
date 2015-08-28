<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<link href="<c:url value="/resources/css/template.css"/>"
    rel="stylesheet">
<t:template>

    <div class="content container">
        <div class="row">
            <div class="col-md-3 for-btn">
                <div class="text-center legenda">Temperatura</div>
                <div class="col-md-12 alert alert-if">
<!--                     <span class="btn-clicavel" data-toggle="modal" -->
<!--                         onclick="openModal('/modal-temperatura')" -->
<!--                         data-target="#chartModal"><i class="fa fa-hand-o-right"></i> -->
<!--                         visualize-me</span> -->
                    <!-- termometro -->
                    <div class="termometro">
                        <span class="glass"> <span class="total" style="bottom: 0%">${dados.temperatura}
                                ºC</span> <span class="amount"></span>
                        </span>
                        <div class="bulb">
                            <span class="red-circle"></span> <span class="filler"> <span></span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- altitude -->
            <div class="col-md-3 col-sm-6">
                <div class="text-center legenda">Altitude</div>
                <div class="col-md-12 alert alert-if">
                    <table style="margin-top: 15px">
                        <td>
                            <div
                                style="width: 27px; height: 2px; background-color: #888; margin-left: 85px;">
                                <span class="val" style="top: 20px; left: 58px;">1000</span>
                            </div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 27px; height: 2px; background-color: #888; margin-left: 85px; margin-top: 9.5px">
                                <span class="val" style="top: 56px; left: 65px;">700</span>
                            </div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 27px; height: 2px; background-color: #888; margin-left: 85px; margin-top: 9.5px">
                                <span class="val" style="top: 90px; left: 65px;">400</span>
                            </div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 27px; height: 2px; background-color: #888; margin-left: 85px; margin-top: 9.5px">
                                <span class="val" style="top: 125px; left: 67px;">100</span>
                            </div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 15px; height: 2px; background-color: #888; margin-left: 97px; margin-top: 9.5px"></div>
                            <div
                                style="width: 100px; height: 2px; background-color: #888; margin-left: 85px; margin-top: 11.5px"></div>
                        </td>
                        <td valign="top"><div class="arrow">
                                <i class="glyphicon glyphicon-arrow-left"></i> ${dados.altitude }m
                            </div></td>
                    </table>
                    <div class="line"></div>
                </div>
            </div>

            <div class="col-md-3 for-btn">
                <div class="text-center legenda">Umidade Relativa do Ar</div>
<!--                    <span class="btn-clicavel" data-toggle="modal" -->
<!--                           onclick="openModal('/modal-umidade')" -->
<!--                        style="z-index: 99; margin-left: 17px; margin-top: 37px" -->
<!--                        data-target="#chartModal"><i class="fa fa-hand-o-right"></i> -->
<!--                        visualize-me</span> -->
                <div class="col-md-12 alert alert-if">
                    <div id="container-umidade" style="width: 100%; height: 280px"></div>
                    <div id="addText"></div>
                </div>
            </div>
            <div class="col-md-3 for-btn">
                <div class="text-center legenda">Direção do Vento</div>
                <!--                 <span class="btn-clicavel" data-toggle="modal" -->
                <!--                     style="z-index: 99; margin-left: 17px; margin-top: 35px" -->
                <!--                     data-target="#myModal"><i class="fa fa-hand-o-right"></i> -->
                <!--                     visualize-me</span> -->
                <div class="col-md-12 alert alert-if">
                    <div id="container-rose"
                        style="width: 100%; height: 100%; margin-top: 5px"></div>
                    <div style="display: none">
                        <table id="freq" border="0" cellspacing="0" cellpadding="0">
                            <tr nowrap bgcolor="#CCCCFF">

                            </tr>
                            <tr nowrap bgcolor="#CCCCFF">
                                <th class="freq">Direction</th>
                            </tr>
                            <tr nowrap>
                                <td class="dir">N</td>
                                <c:if test="${dados.sentidoVento eq 'NORTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">NNE</td>
                                <c:if test="${dados.sentidoVento eq 'NOR-NORDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">NE</td>
                                <c:if test="${dados.sentidoVento eq 'NORDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">ENE</td>
                                <c:if test="${dados.sentidoVento eq 'ES-NORDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">E</td>
                                <c:if test="${dados.sentidoVento eq 'LESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">ESE</td>
                                <c:if test="${dados.sentidoVento eq 'ES-SUDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">SE</td>
                                <c:if test="${dados.sentidoVento eq 'SUDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">SSE</td>
                                <c:if test="${dados.sentidoVento eq 'SU-SUDESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">S</td>
                                <c:if test="${dados.sentidoVento eq 'SUL' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">SSO</td>
                                <c:if test="${dados.sentidoVento eq 'SU-SUDOESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">SO</td>
                                <c:if test="${dados.sentidoVento eq 'SUDOESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">OSO</td>
                                <c:if test="${dados.sentidoVento eq 'OES-SUDOESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">O</td>
                                <c:if test="${dados.sentidoVento eq 'OESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">ONO</td>
                                <c:if test="${dados.sentidoVento eq 'OES-NOROESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="dir">NO</td>
                                <c:if test="${dados.sentidoVento eq 'NOROESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap bgcolor="#DDDDDD">
                                <td class="dir">NNO</td>
                                <c:if test="${dados.sentidoVento eq 'NOR-NOROESTE' }">
                                    <td class="data">17</td>
                                </c:if>

                            </tr>
                            <tr nowrap>
                                <td class="totals">Total</td>
                                <td class="totals">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- fim row -->
        <div class="row">
            <div class="col-md-3 col-sm-3 for-btn">
                <!--                 <span class="btn-clicavel" data-toggle="modal" -->
                <!--                     style="z-index: 99; margin-left: 17px; top: 1px" -->
                <!--                     data-target="#myModal"><i class="fa fa-hand-o-right"></i> -->
                <!--                     visualize-me</span> -->

                <div class="col-md-12 alert alert-if">
                    <div id="container-speed" style="width: 100%; height: 100%"></div>
                </div>
                <div class="text-center">Velocidade do Vento</div>
            </div>
            <div class="col-md-3 for-btn">
                <!--                 <span class="btn-clicavel" data-toggle="modal" -->
                <!--                     style="z-index: 99; margin-left: 17px; top: 1px" -->
                <!--                     data-target="#myModal"><i class="fa fa-hand-o-right"></i> -->
                <!--                     visualize-me</span> -->

                <div class="col-md-12 alert alert-if">
                    <div id="container-rpm" style="width: 100%; height: 100%"></div>
                </div>
                <div class="text-center ">Rotações por Minuto</div>

            </div>
            <div class="col-md-3 for-btn">
<!--                 <span class="btn-clicavel" data-toggle="modal" onclick="openModal('/modal-pressao')" -->
<!--                     style="z-index: 99; margin-left: 17px; top: 1px" -->
<!--                     data-target="#chartModal"><i class="fa fa-hand-o-right"></i> -->
<!--                     visualize-me</span> -->
                <div class="col-md-12 alert alert-if">
                    <div id="container-uv" style="width: 115%; padding-right: 12px"></div>
                </div>
                <div class="text-center ">Pressão Atmosférica</div>
            </div>
            <div class="col-md-3 for-btn">
<!--                 <span class="btn-clicavel" data-toggle="modal" onclick="openModal('/modal-chuva')" -->
<!--                     style="z-index: 99; margin-left: 17px; top: 1px" -->
<!--                     data-target="#chartModal"><i class="fa fa-hand-o-right"></i> -->
<!--                     visualize-me</span> -->
                <div class="col-md-12 alert alert-if">
                    <img id="chuva"
                        src="<c:url value="/resources/icon/${dados.chuva}.png"/> "
                        class="img-responsive" style="margin-top: -35px; opacity: 0.0" />
                    <p class="text-center valorchuva" style="margin-top: -20px">
                        <c:out value="${fn:replace(dados.chuva, '-', ' ')}" />
                    </p>
                </div>
                <div class="text-center ">Chuva</div>

            </div>
        </div>
        <!-- fim row -->
    </div>

    <!-- inclui os scripts highcharts dos elementos da home -->
    <%@include file="scripts-highchats-home.jsp"%>

    <!-- modal de visualizações rapidas -->
    <div id="chartModal" class="modal fade">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="padding-bottom: 25px;">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body"></div>
            </div>

        </div>

    </div>

    <script type="text/javascript">

        setTimeout(animacoes, 200);

        function animacoes() {
            $(".arrow").animate({
                top : '74px'
            }, 1000);
            $(".total").animate({
                bottom : '${dados.temperatura}%'
            }, 1000);
            $(".amount").animate({
                height : '${dados.temperatura}%'
            }, 1000);
            $("#chuva").fadeTo("slow", 1);

            $('circle').remove();
        }

        function openModal(url) {
            $.get(url, function(data, status) {
                $('#chartModal .modal-body').html(data);
            });
        }

        $('#chartModal').on('shown.bs.modal', function() {
            $('#chartModal .chart').highcharts().reflow();
        });

    </script>

</t:template>