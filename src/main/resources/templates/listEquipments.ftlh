<#include "navbar.ftlh">
<#include "head.ftlh">
<#include "additionalDependencies.ftlh">
<!DOCTYPE html>
<html lang="es">
<@head/>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4"><@spring.message "equipmentsList"/></h1>
        </div>
        </body>
        <#if status == "inStock">
            <div><a href="/equipments/create" class="btn btn-success crear"><@spring.message "newEquipment"/></a></div>
        </#if>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th><@spring.message "equipmentId"/></th>
                    <th><@spring.message "name"/></th>
                    <#if status == "inStock">
                        <th><@spring.message "equipmentAmountInExistence"/></th>
                        <th><@spring.message "equipmentRentByDayCost"/></th>
                    <#elseif status == "rented">
                        <th><@spring.message "quantity"/></th>
                        <th><@spring.message "equipmentAmountOfDaysRented"/></th>
                        <th><@spring.message "client"/></th>
                    </#if>
                    <th class="text-center"><@spring.message "action"/></th>
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
                            <td class="text-center"><a class="btn btn-primary" href="/equipments/update/${equipment.id}"><@spring.message "update"/></a> |
                                <a class="btn btn-info" href="/equipments/view/${equipment.id}"><@spring.message "view"/></a> |
                                <a class="btn btn-danger" href="/equipments/delete/${equipment.id}"><@spring.message "delete"/></a> |
                                <a class="btn btn-success" href="/equipments/rent/${equipment.id}"><@spring.message "rent"/></a>
                            </td>
                        </tr>
                    </#foreach>
                </tbody>
                <#elseif status == "rented">
                    <#if receiptsList??>
                        <#list receiptsList as receipt>
                            <tr>
                                <td>${receipt.equipment.id}</td>
                                <td>${receipt.equipment.name}</td>
                                <td>${receipt.quantity}</td>
                                <td>${differenceInDays[receipt?index]}</td>
                                <td>${receipt.client.name}</td>
                                <td class="text-center"><a class="btn btn-success" href="/receipts/recover/${receipt.id}"><@spring.message "recover"/></a></td>
                            </tr>
                        </#list>
                    </#if>
                </#if>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>