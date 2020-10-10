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
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputName" name="name" value="${(equipment.name)!}" placeholder="Digite el nombre" required>
                <label for="inputAmountInExistence">Cantidad en existencia</label>
                <input <#if view??>readonly</#if> type="number" min="1" class="form-control" id="inputAmountInExistence" name="amountInExistence" value="${(equipment.amountInExistence)!}" placeholder="Digite la cantidad en existencia" required>
                <label for="inputRentByDayCost">Cantidad para la renta diaria</label>
                <input <#if view??>readonly</#if> type="number" min="1" class="form-control" id="inputRentByDayCost" name="rentByDayCost" value="${(equipment.rentByDayCost)!}" placeholder="Digite la cantidad por la renta diaria" required>
                <label for="inputRawImage">Foto</label>
                <#if equipment.base64Image?has_content>
                    <div>
                        <img width=315px; src="${equipment.base64Image}" alt"No hay foto">
                    </div>
                </#if>
                <#if !view?? && !rent??>
                    <input type="file" class="form-control" id="inputRawImage" name="rawImage">
                </#if>
                <#if rent??>
                    <div class="row">
                        <div class="col-sm">
                            <label for="inputQuantityToBeRented">Cantidad a ser alquilada</label>
                            <input type="number" min="1" max="${(equipment.amountInExistence)!}" class="form-control" id="inputQuantityToBeRented" value="1" name="quantity" placeholder="Digite la cantidad a ser alquilada" required>
                        </div>
                        <div class="col-sm">
                            <label for="inputClientId">Id el cliente</label>
                            <input  type="number" min="1" class="form-control" id="inputClientId" value="1" name="clientId" placeholder="Digite el id del cliente" required>
                        </div>
                        <div class="col-sm">
                            <label for="inputPromisedReturnDate">Fecha de retorno</label>
                            <input type="date" class="form-control" id="inputPromisedReturnDate" name="promisedReturnDate" required>
                        </div>
                    </div>
                </#if>
            </div>
            <#if !view?? || rent??>
                <button id="btnSubmitEquipment" type="submit" class="btn btn-primary float-right">Confirmar</button>
            <#else>
                <a href="/equipments/" class="btn btn-secondary float-right">Listar equipos</a>
            </#if>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>