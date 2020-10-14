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
            <h1 class="display-4"><@spring.message "clientsList"/></h1>
        </div> 
        </body>
        <div><a href="/clients/create" class="btn btn-success crear"><@spring.message "newClient"/></a></div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th><@spring.message "id"/></th>
                    <th><@spring.message "name"/></th>
                    <th><@spring.message "clientIdentificationCard"/></th>
                    <th class="text-center"><@spring.message "action"/></th>
                </tr>
            </thead>
            <tbody>
                <#foreach client in clientList>
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.identificationCard}</td>
                    <td class="text-center"><a class="btn btn-primary" href="/clients/update/${client.id}"><@spring.message "update"/></a> |
                        <a class="btn btn-info" href="/clients/view/${client.id}"><@spring.message "view"/></a> |
                        <a class="btn btn-danger" href="/clients/delete/${client.id}"><@spring.message "delete"/></a>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>