<%-- 
    Document   : error
    Created on : Jun 28, 2020, 2:18:55 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something's wrong</title>
    </head>
    <body>
        <h1>Something's wrong, contact administrator for more information</h1>
        <script>
            console.log("ex: ${requestScope.exception.message}");
            console.log("code: ${requestScope.status_code}");
            console.log("uri: ${requestScope.uri}");
        </script>
    </body>
</html>
