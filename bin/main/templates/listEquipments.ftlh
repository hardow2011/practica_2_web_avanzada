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
            <h1 class="display-4">Lista de equipos</h1>
        </div> 
        </body>
        <div><a href="/equipments/create" class="btn btn-success crear">Nuevo equipo</a></div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Cantidad en existencia</th>
                    <th>Cantidad por renta diaria</th>
                    <th class="text-center">Acción</th>
                </tr>
            </thead>
            <tbody>
                <#foreach equipment in equipmentsList>
                <tr>
                    <td>${equipment.id}</td>
                    <td>${equipment.name}</td>
                    <td>${equipment.amountInExistence}</td>
                    <td>${equipment.rentByDayCost}</td>
                    <td class="text-center"><a class="btn btn-primary" href="/equipments/update/${equipment.id}">Editar</a> |
                        <a class="btn btn-info" href="/equipments/view/${equipment.id}">Visualizar</a> |
                        <a class="btn btn-danger" href="/equipments/delete/${equipment.id}">Eliminar</a> |
                        <a class="btn btn-success" href="/equipments/rent/${equipment.id}">Alquilar</a>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>