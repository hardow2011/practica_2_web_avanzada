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
            <h1 class="display-4"><@spring.message "receiptsList"/></h1>
        </div> 
        </body>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th><@spring.message "clientName"/></th>
                    <th><@spring.message "equipmentName"/></th>
                    <th><@spring.message "rentDate"/></th>
                    <th><@spring.message "promisedReturnDate"/></th>
                    <th><@spring.message "returnDate"/></th>
                    <th><@spring.message "totalCost"/></th>
                </tr>
            </thead>
            <tbody>
                <#foreach receipt in receiptsList>
                <tr>
                    <td>${receipt.client.name}</td>
                    <td>${receipt.equipment.name}</td>
                    <td>${receipt.rentDate}</td>
                    <td>${receipt.promisedReturnDate}</td>
                    <td>${receipt.returnedDate}</td>
                    <td>${receipt.totalCost}</td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>