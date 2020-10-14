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

            <div class="form-group">
                <label for="inputId"><@spring.message "id"/></label>
                <input readonly type="number" class="form-control" id="inputId" name="id" value="${(receipt.id)!}">
                <label for="inputName"><@spring.message "clientName"/></label>
                <input readonly readonly type="text" class="form-control" id="inputName" name="clientName" value="${(receipt.client.name)!}">
                <label for="inputQuantity"><@spring.message "equipentRentedQuantity"/></label>
                <input readonly type="number" min="1" max="${(receipt.quantity)!}" value="${(receipt.quantity)!}" class="form-control" id="inputQuantity" name="inputAmountInExistence">
                <label for="inputRentByDayCost"><@spring.message "equipmentRentByDayCost"/></label>
                <input readonly type="number" min="1" class="form-control" id="inputRentByDayCost" name="rentByDayCost" value="${(receipt.equipment.rentByDayCost)!}">
                <#if receipt.equipment.base64Image?has_content>
                    <label for="inputRawImage"><@spring.message "photo"/></label>
                    <div>
                        <img width=315px; src="${receipt.equipment.base64Image}" alt"No hay foto">
                    </div>
                </#if>
                    <div class="row">
                        <div class="col-sm">
                            <label for="inputQuantityToBeRecovered"><@spring.message "equipmentQuantityToBeRecovered"/></label>
                            <input type="number" min="1" value="${(receipt.quantity)!}" max="${(receipt.quantity)!}" class="form-control" id="inputQuantityToBeRecovered" name="quantityToBeRecovered">
                        </div>
                        <div class="col-sm">
                        </div>
                        <div class="col-sm">
                        </div>
                    </div>
            </div>
            <button id="btnSubmitEquipment" type="submit" class="btn btn-success float-right"><@spring.message "recover"/></button>
        </form>
    </div>
    <@additionalDependencies/>
</body>
</html>