<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Alunno</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1>Nuovo Alunno</h1>

    <a class="btn btn-warning mb-3" href="<c:url value='/alunni/lista'/>">Torna a Lista Alunni</a>
    <div class="row mt-4 mb-5 justify-content-center">
        <div class="col-6">
         <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/alunni/add" modelAttribute="alunno" >
                <form:hidden path="id" />
                <div class="mb-3">
                  <label class="form-label">Nome</label>
                  <form:input type="text" cssClass="form-control" path="nome" />
                </div>
                 <div class="mb-3">
                    <label class="form-label">Cognome</label>
                    <form:input type="text" cssClass="form-control" path="cognome" />
                 </div>
                  <div class="mb-3">
                      <label  class="form-label">Data di nascita</label>
                      <form:input type="date" cssClass="form-control" path="dataDiNascita" />
                  </div>
                  <div class="mb-3">
                      <label class="form-label">Città di residenza</label>
                      <form:input type="text" cssClass="form-control" path="cittaDiResidenza" />
                  </div>
                  <div class="mb-3">
                      <label class="form-label">Voto</label>
                      <form:input type="number" step=".1" cssClass="form-control" path="voto" />
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Assegna uno o più corsi:</label>
                    <form:select multiple="true" cssClass="form-control" path="corsiIds">
                          <form:options  items="${corsi}" itemValue="id" itemLabel="nome" />

                    </form:select>
                  </div>
                  <div class="mb-3 d-flex justify-content-end">
                  <button type="submit" class="btn btn-success mt-4">
                     <a>Salva Alunno</a>
                   </button>
                  </div>
            </form:form>
        </div>
    </div>

</body>
</html>
