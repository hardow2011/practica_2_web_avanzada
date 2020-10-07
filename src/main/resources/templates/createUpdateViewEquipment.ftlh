<#include "navbar.ftlh">
<#include "head.ftlh">
<#include "additionalDependencies.ftlh">
<!DOCTYPE html>
<html lang="en">
<@head/>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4 text-center">${action}</h1>
        </div>
        <form enctype="multipart/form-data" action=${(postAddress)!} method="post">
            <#--  El objeto new Estudiante() enviado del servidor tiene id = 0...
            entonces si el id es diferente de 0, es que el onjeto se está...
            editando, no creando, por lo tanto, se visualizará el id.  -->
            <#if equipment.id != 0>
                <div class="form-group">
                    <label for="inputId">Id</label>
                    <input readonly type="number" class="form-control" id="inputId" name="id" value="${(equipment.id)!}">
                </div>

            </#if>
            <div class="form-group">
                <label for="inputName">Nombre</label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputName" name="name" value="${(equipment.name)!}" placeholder="Digite el nombre">
                <label for="inputAmountInExistence">Cantidad en existencia</label>
                <input <#if view??>readonly</#if> type="number" class="form-control" id="inputAmountInExistence" name="amountInExistence" value="${(equipment.amountInExistence)!}" placeholder="Digite la cantidad en existencia">
                <label for="inputRentByDayCost">Cantidad por renta diaria</label>
                <input <#if view??>readonly</#if> type="number" min="0" class="form-control" id="inputRentByDayCost" name="rentByDayCost" value="${(equipment.rentByDayCost)!}" placeholder="Digite la cantidad por la renta diaria">
            </div>
            <#if view??>
                <a href="/equipments/list" class="btn btn-secondary float-right">Listar equipos</a>
            <#else>
            <#if !view??>
                <button id="btnSubmitEquipment" type="submit" class="btn btn-primary float-right">Confirmar</button>
            <#else>
                <button id="btnSubmitEquipment" href="/equipments/" class="btn btn-secondary float-right">Regresar a la lista</button>
            </#if>
            </#if>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>