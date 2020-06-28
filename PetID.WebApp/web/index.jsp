<%-- 
    Document   : index
    Created on : Jun 28, 2020, 1:49:40 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to PetID</title>
        <style>
            .page-title {
                text-align: center;
                color: green;
            }
        </style>
    </head>
    <body>
        <h1 class="page-title">WELCOME TO PETID</h1>
        <h1>We have 269 breeds of pet (cat & dog)</h1>
        <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}">
            <input type="hidden" name="action" value="submit_image"/>
            <div>
                Upload an image and let we guest what it is: 
                <input type="file" name="file" accept="image/*"/>
            </div>
            <div>
                <input type="submit" value="SUBMIT"/>
            </div>
        </form>
    </body>
</html>
