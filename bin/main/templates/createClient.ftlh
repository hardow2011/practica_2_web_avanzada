<#include "navbar.ftlh">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${action} cliente</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
    <@navbar/>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4 text-center">${action} cliente</h1>
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
                <input <#if visualizar??>readonly</#if> type="text" class="form-control" id="inputName" name="name" value="${(client.name)!}" placeholder="Digite el nombre">
                <label for="inputIdentificationCard">Cédula</label>
                <input <#if visualizar??>readonly</#if> type="text" class="form-control" id="inputIdentificationCard" name="identificationCard" value="${(client.identificationCard)!}" placeholder="Digite la cédula">
                <label for="inputPicture">Foto</label>
                <input <#if visualizar??>readonly</#if> type="file" class="form-control" id="inputPicture" name="base64Image" value="${(client.base64Image)!}">
            </div>
            <#if visualizar??>
                <a href="/clients/list" class="btn btn-secondary float-right">Listar clientes</a>
            <#else>
                <button id="btnSubmitClient" type="submit" class="btn btn-primary float-right">Confirmar</button>
            </#if>
        </form>
    </div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>