<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Alunni</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Elenco Alunni</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/alunni/nuovo'/>">Nuovo Alunno</a>
<a class="btn btn-warning mb-3" href="<c:url value='/'/>">Torna a Menu</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Città di residenza</th><th>Voto</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="a" items="${alunni}">
        <tr>
            <td>${a.id}</td>
            <td>${a.nome}</td>
            <td>${a.cognome}</td>
            <td>${a.dataDiNascita}</td>
            <td>${a.cittaDiResidenza}</td>
            <td>${a.voto}</td>

            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/alunni/${a.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/alunni/${a.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
