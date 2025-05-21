<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Corso</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
    <h1>Nuovo Corso</h1>

    <a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Torna a lista corsi</a>
    <div class="row mt-4 justify-content-center">
        <div class="col-6">
         <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/corsi/add" modelAttribute="corso" >
                <form:hidden path="id" />
                <div class="mb-3">
                  <label class="form-label">Nome</label>
                  <form:input type="text" cssClass="form-control" path="nome" />
                </div>
                 <div class="mb-3">
                    <label class="form-label">Ore</label>
                    <form:input type="number" cssClass="form-control" path="ore" />
                 </div>
                  <div class="mb-3">
                      <label class="form-label">Anno accademico</label>
                      <form:input type="number" cssClass="form-control" path="annoAccademico" />
                  </div>
                  <div class="mb-3">
                     <label class="form-label">Docente</label>
                     <select class="form-select" name="docenteId">
                         <c:forEach var="d" items="${docenti}">
                            <option value="${d.id}" ${d.id == corso.docente.id ? 'selected' : ''}>
                                ${d.nome} ${d.cognome}
                            </option>
                         </c:forEach>
                     </select>
                  </div>
                  <div class="mb-3">
                     <label class="form-label">Assegna uno o più alunni:</label>
                     <form:select multiple="true" cssClass="form-control" path="alunniIds">
                        <form:options  items="${alunni}" itemValue="id" itemLabel="nome" />
                     </form:select>
                   </div>
                  <div class="mb-3 d-flex justify-content-end">
                      <button type="submit" class="btn btn-success mt-4">
                         <a>Salva Corso</a>
                      </button>
                  </div>
            </form:form>
        </div>
    </div>

</body>
</html>
