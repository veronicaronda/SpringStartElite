<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Menu Scuola</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <div class="row justify-content-center mt-5">
    <div class="col-4 mt-5 d-flex flex-column">
        <h1 class="h1 mb-4 text-center">Menu</h1>

        <a class="btn btn-warning mb-3" href="<c:url value='/docenti/lista'/>">Lista Docenti</a>
        <a class="btn btn-warning mb-3" href="<c:url value='/alunni/lista'/>">Lista Alunni</a>
        <a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Lista Corsi</a>


     </div>
    </div>
</body>
</html>
