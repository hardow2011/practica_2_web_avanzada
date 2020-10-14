<#include "head.ftlh">
<#include "additionalDependencies.ftlh">
<!DOCTYPE html>
<html>
<@head/>
<body>

    <br>
    <div class="container">
        <div class="jumbotron">
            <h1 class="display-4">Login</h1>
            <br>

        <form enctype="application/x-www-form-urlencoded" method="post" action=${postAddress} >
            <div class="form-group">
                <label for="nombreForm">Nombre de usuario</label>
                <input type="text"  name="nombre" class="form-control" id="nombreForm" required>
            </div>
            <div class="form-group">
                <label for="passwordInput">Contraseña</label>
                <input type="password" id="passwordInput" class="form-control" name="password" required/><br/>
            </div>
            <#--  <div>
                <input type="checkbox" id="recuerdame" name="recuerdame">
                <label for="recuerdame">Recuérdame</label><br>
            </div>  -->
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </form>

        </div>
    </div>
<@additionalDependencies/>
</body>
</html>
