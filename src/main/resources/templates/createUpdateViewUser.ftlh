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
            <#if user.id != 0>
                <div class="form-group">
                    <label for="inputId"><@spring.message "id"/></label>
                    <input readonly type="number" class="form-control" id="inputId" name="id" value="${(user.id)!}">
                </div>

            </#if>
            <div class="form-group">
                <label for="inputUsername"><@spring.message "name"/></label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputUsername" name="username" value="${(user.username)!}" required>
                <label for="password"><@spring.message "password"/></label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputIdentificationCard" name="password" value="${(user.password)!}" required>
                <input type="checkbox" id="isAdmin" name="isAdmin" value="false">
                <label for="isAdmin"><@spring.message "isAdmin"/></label><br>
            </div>
            <#if !view??>
                <button id="btnSubmitUser" type="submit" class="btn btn-primary float-right"><@spring.message "confirm"/></button>
            <#else>
                <button id="btnSubmitUser" href="/users/" class="btn btn-secondary float-right"><@spring.message "goBackToList"/></button>
            </#if>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>