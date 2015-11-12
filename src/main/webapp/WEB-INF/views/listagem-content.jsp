<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${objects.size() > 0}">
<c:forEach var="obj" items="${objects}" varStatus="it">
    <tr>
        <td>${obj.valor}${tipoDado}</td>
        <td><fmt:formatDate type="both" pattern="dd/MM/yyyy - HH:mm:ss"
                value="${obj.data}" /></td>
    </tr>
</c:forEach>
</c:if>