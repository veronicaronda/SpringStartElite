<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Docenti</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
                    <script src="https://kit.fontawesome.com/c71cc343ef.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body class="container-fluid vh-100">
<div class="row ">
         <sidebar class="sidebar mySidebar">
            <a href="javascript:void(0)" class="closebtn invisible" onclick="closeNav()"><i class="fa-solid fa-xmark text-white fs-4"></i></a>
          <div class="main">
                       <button class="openbtn" onclick="openNav()"><i class="fa-solid fa-bars"></i></button>
          </div>
                <div id="filtriContainer" class="invisible px-2 my-5">
                <div class="d-flex flex-column">
                     <a class="btn btn-primary mb-3" href="<c:url value='/docenti/nuovo'/>">Nuovo Docente</a>
                            <c:choose>
                                <c:when test="${isSearch}">
                                    <a class="btn btn-warning mb-3" href="<c:url value='/docenti/lista'/>">Torna a lista Docenti</a>
                                </c:when>
                                <c:otherwise>
                                   <a class="btn btn-warning mb-3" href="<c:url value='/'/>">Torna a Menu</a>
                                </c:otherwise>
                            </c:choose>
                </div>
                    <h3 class="text-white my-3">Filtri</h3>
                        <form:form cssClass="my-3 w-100"  method="GET" action="/docenti/list-docente-by-name" modelAttribute="docenteFiltro">
                            <label class="form-label text-white">Cerca per nome</label>
                            <form:input id="inputName" type="text" cssClass="form-control" path="nome" />
                        </form:form>
                       <form:form cssClass="my-3 w-100"  method="GET" action="/docenti/list-docente-by-surname" modelAttribute="docenteFiltro">
                            <label class="form-label text-white">Cerca per cognome</label>
                            <form:input id="inputSurname" type="text" cssClass="form-control" path="cognome" />
                       </form:form>
                </div>
         </sidebar>
         <div class="col flex-grow">
            <h1 class="my-4">Elenco Docenti</h1>
            <c:choose>
                <c:when test="${docenti.isEmpty()}"><p>Non ci sono docenti con questo nome o cognome</p></c:when>
                <c:otherwise>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Corsi assegnati</th><th>Azioni</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${docenti}">
                        <tr>
                            <td>${d.id}</td>
                            <td>${d.nome}</td>
                            <td>${d.cognome}</td>
                            <td>${d.dataDiNascita}</td>
                            <td>
                                                        <!-- Button trigger modal -->
                            <c:choose>
                                <c:when test="${empty d.corsi}">
                                  <p class="m-0"><em>Nessun corso</em></p>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#modal-${d.id}">
                                                          Lista corsi
                                    </button>
                                </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="btn btn-sm btn-secondary" href="<c:url value='/docenti/${d.id}/edit'/>">Modifica</a>
                                <a class="btn btn-sm btn-danger"
                                   href="<c:url value='/docenti/${d.id}/delete'/>"
                                   onclick="return confirm('Sei sicuro?')">Elimina</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <!-- Modal -->
                                    <c:forEach var="d" items="${docenti}">
                                        <div class="modal fade" id="modal-${d.id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                          <div class="modal-dialog">
                                            <div class="modal-content">
                                              <div class="modal-header bg-black text-white d-flex justify-content-between">
                                                <h1 class="modal-title fs-5" id="modalLabel-${d.id}">Lista corsi di ${d.nome} ${d.cognome}</h1>
                                                <i type="button" data-bs-dismiss="modal" class="fa-solid fa-xmark fs-4 text-white"></i>
                                              </div>
                                              <div class="modal-body ">

                                                <c:forEach var="corso" items="${d.corsi}">
                                                <div class="px-3 w-100 d-flex justify-content-between">
                                                    <p class="m-0">${corso.nome}</p>
                                                    <a onclick="return confirm('Sei sicuro?')" href="<c:url value='/alunni/${a.id}/delete/${corso.id}'/>"><button class="btn btn-sm btn-danger">Disiscrivi</button></a>
                                                </div>
                                                    <hr>
                                                </c:forEach>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                    </c:forEach>
                    </tbody>
                </table>
                </c:otherwise>
            </c:choose>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"  crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/sidebar.js"></script>

</body>
</html>
