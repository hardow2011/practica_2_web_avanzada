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
                    <th><@spring.message "id"/></th>
                    <th><@spring.message "name"/></th>
                    <th class="text-center"><@spring.message "action"/></th>
                </tr>
            </thead>
            <tbody>
                <#foreach user in usersList>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td class="text-center">
                    <#--  <a class="btn btn-primary" href="/users/update/${user.id}"><@spring.message "update"/></a> |  -->
                        <#if user.username != "admin">
                            <#--  <a class="btn btn-info" href="/users/view/${user.id}"><@spring.message "view"/></a> |  -->
                            <a class="btn btn-danger" href="/users/delete/${user.id}"><@spring.message "delete"/></a>
                        </#if>
                    </td>
                </tr>
                </#foreach>
            </tbody>
        </table>
    </div>
    <@additionalDependencies/>
</body>
</html>