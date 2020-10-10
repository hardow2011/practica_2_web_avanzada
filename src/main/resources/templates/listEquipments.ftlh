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
        <#if status == "inStock">
            <div><a href="/equipments/create" class="btn btn-success crear">Nuevo equipo</a></div>
        </#if>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>id del equipo</th>
                    <th>Nombre</th>
                    <#if status == "inStock">
                        <th>Cantidad en existencia</th>
                        <th>Cantidad por renta diaria</th>
                    <#elseif status == "rented">
                        <th>Días alquilados</th>
                        <th>Cliente</th>
                    <#if>
                    <th class="text-center">Acción</th>
                </tr>
            </thead>
            <tbody>
                <#if status == "inStock">
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
                <#elseif status == "rented">
                    <#foreach receipt in receiptsList>
                        <tr>
                            <td>${receipt.equipment.id}</td>
                            <td>${receipt.equipment.name}</td>
                            <td>${receipt.equipment.id}</td>
                        </tr>
                    </#foreach>
                <#if>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>