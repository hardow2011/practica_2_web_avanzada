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
            <#if client.id != 0>
                <div class="form-group">
                    <label for="inputId">Id</label>
                    <input readonly type="number" class="form-control" id="inputId" name="id" value="${(client.id)!}" placeholder="Digite la matrícula">
                </div>

            </#if>
            <div class="form-group">
                <label for="inputName">Nombre</label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputName" name="name" value="${(client.name)!}" placeholder="Digite el nombre">
                <label for="inputIdentificationCard">Cédula</label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputIdentificationCard" name="identificationCard" value="${(client.identificationCard)!}" placeholder="Digite la cédula">
                <#if client.base64Image?has_content>
                    <label for="inputRawImage">Foto</label>
                    <div>
                        <img width=315px; src="${client.base64Image}" alt"No hay foto">
                    </div>
                </#if>
                <#if !view??>
                    <input type="file" class="form-control" id="inputRawImage" name="rawImage">
                </#if>
            </div>
            <#if view??>
                <a href="/clients/list" class="btn btn-secondary float-right">Listar clientes</a>
            <#else>
            <#if !view??>
                <button id="btnSubmitClient" type="submit" class="btn btn-primary float-right">Confirmar</button>
            <#else>
                <button id="btnSubmitClient" href="/clients/" class="btn btn-secondary float-right">Regresar a la lista</button>
            </#if>
            </#if>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>