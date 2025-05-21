<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Elenco Alunni</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
          <script src="https://kit.fontawesome.com/c71cc343ef.js" crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/style.css">
</head>
<body class="container-fluid vh-100">

     <!-- Sidebar -->
     <div class="row ">
         <sidebar class="sidebar mySidebar">
            <a href="javascript:void(0)" class="closebtn invisible" onclick="closeNav()"><i class="fa-solid fa-xmark text-white fs-4"></i></a>
          <div class="main">
                       <button class="openbtn" onclick="openNav()"><i class="fa-solid fa-bars"></i></button>
          </div>
                <div id="filtriContainer" class="invisible px-2 my-5">
                <div class="d-flex flex-column">
                     <a class="btn btn-primary mb-3" href="<c:url value='/alunni/nuovo'/>">Nuovo Alunno</a>
                            <c:choose>
                                <c:when test="${isSearch}">
                                    <a class="btn btn-warning mb-3" href="<c:url value='/alunni/lista'/>">Torna a lista Alunni</a>
                                </c:when>
                                <c:otherwise>
                                   <a class="btn btn-warning mb-3" href="<c:url value='/'/>">Torna a Menu</a>
                                </c:otherwise>
                            </c:choose>
                </div>
                    <h3 class="text-white my-3">Filtri</h3>
                        <form class="w-100"  method="GET" action="/alunni/lista-alunni-per-nome-cognome" >
                             <label id="btnSearchNameSurname" class="form-label text-white">Cerca per nome</label>
                             <input name="keyword" value="${keyword}" id="inputSearchNameSurname"  type="text" class="form-control" placeholder="Inserisci nome o cognome" />
                        </form>
                        <form class="my-2 w-100"  method="GET" action="/alunni/lista-alunni-promossi" >
                            <c:choose>
                                <c:when test="${isPassed}">
                                    <a class="btn btn-secondary my-3" href="<c:url value='/alunni/lista'/>">Torna a lista alunni</a>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-secondary my-3">Trova alunni promossi</button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                </div>
         </sidebar>
         <div class="col flex-grow">
         <h1 class="my-4">Elenco Alunni</h1>


            <c:choose>
                <c:when test="${alunni.isEmpty()}">
                    <div class="row my-4 w-100">
                        <h3 class="my-4 d-flex justify-content-center align-items-center">
                           <em>Non ci sono alunni con questo nome o cognome<em>
                        </h3>
                    </div>
                </c:when>
            <c:otherwise>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Città di residenza</th><th>Voto</th><th>Iscritto al corso</th><th>Azioni</th>
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
                            <!-- Button trigger modal -->
                            <c:choose>
                            <c:when test="${empty a.corsi}">
                            <p class="m-0"><em>Nessun corso</em></p>
                            </c:when>
                            <c:otherwise>
                            <button class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#modal-${a.id}">
                              Lista corsi
                            </button>
                            </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-secondary" href="<c:url value='/alunni/${a.id}/edit'/>">Modifica</a>
                            <a class="btn btn-sm btn-danger"
                               href="<c:url value='/alunni/${a.id}/delete'/>"
                               onclick="return confirm('Sei sicuro?')">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
                <!-- Modal -->
                <c:forEach var="a" items="${alunni}">
                    <div class="modal fade" id="modal-${a.id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header bg-black text-white d-flex justify-content-between">
                            <h1 class="modal-title fs-5" id="modalLabel-${a.id}">Lista corsi di ${a.nome} ${a.cognome}</h1>
                            <i type="button" data-bs-dismiss="modal" class="fa-solid fa-xmark fs-4 text-white"></i>
                          </div>
                          <div class="modal-body ">

                            <c:forEach var="corso" items="${a.corsi}">
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

     </div>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"  crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/sidebar.js"></script>


</body>
</html>
