<%-- 
    Document   : breed
    Created on : Jul 2, 2020, 3:57:14 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Breed detail</title>
        <style>
            .page-title {
                text-align: center;
                color: green;
            }

            .breed-img {
                width: 50%;
            }

            .d-none {
                display: none;
            }

            hr {
                margin: 50px 0;
            }

            .type-name {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1 class="page-title">Detail information of <b><span class="breed-name">breed</span></b></h1>
        <div class="breed-info">
            <h3>Information</h3>
            <div>
                <img class="breed-img"/>
            </div>
            <p>
                <b>Type: </b>
                <span class="type-name">Cat</span>
            </p>
            <p>
                <b>Code: </b>
                ${breedCode}
            </p>
            <p>
                <b>Name: </b>
                Breed name
            </p>
            <p>
                <b>Description: </b>
                Breed name
            </p>
            <hr/>
            <h3>Traits</h3>
            <p>
                <b>Trait A:</b> 3 out of 5<br/>
                <b>Trait B:</b> 3 out of 5<br/>
                <b>Trait C:</b> 3 out of 5<br/>
                <b>Trait D:</b> 3 out of 5<br/>
                <b>Trait E:</b> 3 out of 5<br/>
            </p>
            <hr/>
            <h3>Attributes</h3>
            <p>
                <b>Attr A:</b> 3 out of 5<br/>
                <b>Attr B:</b> 3 out of 5<br/>
                <b>Attr C:</b> 3 out of 5<br/>
                <b>Attr D:</b> 3 out of 5<br/>
                <b>Attr E:</b> 3 out of 5<br/>
            </p>
            <hr/>
            <h3>Section 1</h3>
            <p>
                This is the section 1 content
            </p>
        </div>
    </body>
</html>
