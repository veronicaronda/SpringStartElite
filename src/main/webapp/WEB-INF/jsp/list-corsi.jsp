<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
                    <script src="https://kit.fontawesome.com/c71cc343ef.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"
</head>
<body class="container-fluid vh-100">
 <!-- Sidebar -->
     <div class="row position-relative">
         <sidebar class="sidebar mySidebar">
            <a href="javascript:void(0)" class="closebtn invisible" onclick="closeNav()"><i class="fa-solid fa-xmark text-white fs-4"></i></a>
          <div class="main">
                       <button class="openbtn" onclick="openNav()"><i class="fa-solid fa-bars"></i></button>
          </div>
                <div id="filtriContainer" class="invisible px-2 my-5">
                <div class="d-flex flex-column">
                    <a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo Corso</a>
                        <c:choose>
                            <c:when test="${isSearch}">
                                <a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Torna a lista corsi</a>
                            </c:when>
                        <c:otherwise>
                            <a class="btn btn-warning mb-3" href="<c:url value='/'/>">Torna a Menu</a>
                        </c:otherwise>
                        </c:choose>
                </div>
                    <h3 class="text-white my-3">Filtri</h3>
                        <form class="w-100"  method="GET" action="/alunni/lista-corsi-per-nome" >
                             <label id="btnSearchNameSurname" class="form-label text-white">Cerca per nome del corso</label>
                             <input name="keyword" value="${keyword}" id="inputSearchNameSurname"  type="text" class="form-control" placeholder="Inserisci nome o cognome" />
                        </form>
                         <form:form cssClass="my-3 w-100"  method="GET" action="/corsi/lista-corsi-per-anno-accademico" modelAttribute="corso">
                            <label id="btnSearchNameSurname" class="form-label text-white">Cerca per anno</label>
                            <form:input id="inputSurname" type="number" cssClass="form-control" placeholder="Inserisci anno accademico" path="annoAccademico" />
                         </form:form>
                         <form class="my-3 w-100" method="GET" action="${pageContext.request.contextPath}/corsi/lista-corsi-per-docente">
                             <label class="form-label text-white">Cerca per nome docente</label>
                             <input name="keyword" type="text" class="form-control" placeholder="Inserisci nome docente" value="${keyword}" />
                         </form>
                </div>
         </sidebar>
         <div class="col flex-grow">

        <h1 class="my-4">Elenco Corsi</h1>

<c:choose>
    <c:when test="${corsi.isEmpty()}">
        <div class="row my-5">
            <h3 class="my-5 d-flex justify-content-center align-items-center">
                <em>La ricerca non ha restituito nessun corso</em>
            </h3>
        </div>
    </c:when>
    <c:otherwise>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th><th>Nome</th><th>Ore</th><th>Anno accademico</th><th>Docente assegnato</th><th>Alunni iscritti</th><th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${corsi}">
            <tr>
                <td>${c.id}</td>
                <td>${c.nome}</td>
                <td>${c.ore}</td>
                <td>${c.annoAccademico}</td>
                <c:choose>
                    <c:when test="${c.docente.id != null}">
                        <td>${c.docente.nome} ${c.docente.cognome}</td>
                    </c:when>
                    <c:otherwise>
                        <td><p><em>Nessun docente</em></p></td>
                    </c:otherwise>
                </c:choose>
                <td>
                 <!-- Button trigger modal -->
                 <c:choose>
                 <c:when test="${empty c.alunni}">
                    <p class="m-0"><em>Nessun alunno</em></p>
                 </c:when>
                 <c:otherwise>
                 <button class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#modal-${c.id}">Lista alunni</button>
                 </c:otherwise>
                 </c:choose>
                 </td>
                <td>
                    <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${c.id}/modifica'/>">Modifica</a>
                    <a class="btn btn-sm btn-danger"
                       href="<c:url value='/corsi/${c.id}/delete'/>"
                       onclick="return confirm('Sei sicuro?')">Elimina</a>
                </td>
            </tr>
        </c:forEach>
         <!-- Modal -->
                        <c:forEach var="c" items="${corsi}">
                            <div class="modal fade" id="modal-${c.id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                              <div class="modal-dialog">
                                <div class="modal-content">
                                  <div class="modal-header bg-black text-white d-flex justify-content-between">
                                    <h1 class="modal-title fs-5" id="modalLabel-${a.id}">Lista alunni iscritti al corso ${c.nome}</h1>
                                    <i type="button" data-bs-dismiss="modal" class="fa-solid fa-xmark fs-4 text-white"></i>
                                  </div>
                                  <div class="modal-body">
                                    <c:forEach var="alunno" items="${c.alunni}">
                                    <div class="px-3 w-100 d-flex justify-content-between">
                                        <p class="m-0">${alunno.nome}</p>
                                        <a onclick="return confirm('Sei sicuro?')" href="<c:url value='/corsi/${c.id}/delete/${alunno.id}'/>"><button class="btn btn-sm btn-danger">Disiscrivi</button></a>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/sidebar.js"></script>
</body>
</html>
