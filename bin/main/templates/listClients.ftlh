<#include "navbar.ftlh">
<#include "head.ftlh">
<#include "additionalDependencies.ftlh">
<!DOCTYPE html>
<html>
<@head/>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Lista de clientes</h1>
        </div> 
        </body>
        <div><a href="/clients/create" class="btn btn-success crear">Nuevo cliente</a></div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Cédula</th>
                    <th class="text-center">Acción</th>
                </tr>
            </thead>
            <tbody>
                <#foreach client in clientList>
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.identificationCard}</td>
                    <td class="text-center"><a class="btn btn-primary" href="/clients/update/${client.id}">Editar</a> |
                        <a class="btn btn-info" href="/clients/view/${client.id}">Visualizar</a> |
                        <a class="btn btn-danger" href="/clients/delete/${client.id}">Eliminar</a>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>