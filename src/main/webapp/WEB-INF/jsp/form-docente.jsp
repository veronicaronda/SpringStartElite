<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Docente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1>Nuovo Docente</h1>

    <a class="btn btn-warning mb-3" href="<c:url value='/docenti/lista'/>">Torna a Lista Docenti</a>
    <div class="row mt-4 justify-content-center">
        <div class="col-6">
         <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/docenti/add" modelAttribute="docente" >
                <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Nome</label>
                  <form:input type="text" cssClass="form-control" path="nome" />
                </div>
                 <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">Cognome</label>
                    <form:input type="text" cssClass="form-control" path="cognome" />
                 </div>
                  <div class="mb-3">
                      <label for="exampleFormControlInput1" class="form-label">Data di nascita</label>
                      <form:input type="date" cssClass="form-control" path="dataDiNascita" />
                  </div>
                  <div class="mb-3 d-flex justify-content-end">
                  <button type="submit" class="btn btn-success mt-4">
                     <a>Salva Docente</a>
                   </button>
                  </div>
            </form:form>
        </div>
    </div>

</body>
</html>
