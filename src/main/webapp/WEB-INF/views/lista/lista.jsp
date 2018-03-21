<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lista de paises</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <table>
            <thead>
                <tr>
                    <td>Código alfa 2</td>
                    <td>Código alfa 3</td>
                    <td>Nombre</td>
                    <td>Capital</td>
                    <td>Bandera</td>
                </tr>
            </thead>
            <c:forEach var="country" items="${lista}">
                <tr>
                    <td>${country.alpha2Code}</td>
                    <td>${country.alpha3Code}</td>
                    <td>${country.name}</td>
                    <td>${country.capital}</td>
                    <td><img src="https://github.com/adamoliver/Country-Flags-ISO-3/blob/master/gif/${fn:toLowerCase(country.alpha3Code)}.gif?raw=true"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>