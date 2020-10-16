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
            <h1 class="display-4 text-center">${action}</h1>
        </div>
        <form enctype="multipart/form-data" action=${(postAddress)!} method="post">
            <#--  El objeto new Estudiante() enviado del servidor tiene id = 0...
            entonces si el id es diferente de 0, es que el onjeto se está...
            editando, no creando, por lo tanto, se visualizará el id.  -->
            <#if equipment.id != 0>
                <div class="form-group">
                    <label for="inputId"><@spring.message "id"/></label>
                    <input readonly type="number" class="form-control" id="inputId" name="id" value="${(equipment.id)!}">
                </div>

            </#if>
            <div class="form-group">
                <label for="inputName"><@spring.message "name"/></label>
                <input <#if view??>readonly</#if> type="text" class="form-control" id="inputName" name="name" value="${(equipment.name)!}" required>
                <label for="inputAmountInExistence"><@spring.message "equipmentAmountInExistence"/></label>
                <input <#if view??>readonly</#if> type="number" min="1" class="form-control" id="inputAmountInExistence" name="amountInExistence" value="${(equipment.amountInExistence)!}" required>
                <label for="inputRentByDayCost"><@spring.message "equipmentRentByDayCost"/></label>
                <input <#if view??>readonly</#if> type="number" min="1" class="form-control" id="inputRentByDayCost" name="rentByDayCost" value="${(equipment.rentByDayCost)!}" required>
                <label for="inputRawImage"><@spring.message "photo"/></label>
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
                            <label for="inputQuantityToBeRented"><@spring.message "equipmentAmountToBeRented"/></label>
                            <input type="number" min="1" max="${(equipment.amountInExistence)!}" class="form-control" id="inputQuantityToBeRented" value="1" name="quantity" required>
                        </div>
                        <div class="col-sm">
                            <label for="inputClientId"><@spring.message "clientId"/></label>
                            <input  type="number" min="1" class="form-control" id="inputClientId" value="1" name="clientId" required>
                        </div>
                        <div class="col-sm">
                            <label for="inputPromisedReturnDate"><@spring.message "returnDate"/></label>
                            <input type="date" class="form-control" id="inputPromisedReturnDate" name="promisedReturnDate" required>
                        </div>
                    </div>
                </#if>
            </div>
            <#if !view?? || rent??>
                <button id="btnSubmitEquipment" type="submit" class="btn btn-primary float-right"><@spring.message "confirm"/></button>
            <#else>
                <a href="/equipments/" class="btn btn-secondary float-right"><@spring.message "goBackToList"/></a>
            </#if>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>