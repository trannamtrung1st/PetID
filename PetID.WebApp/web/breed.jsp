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

            .post-item {
                vertical-align: top;
                padding: 10px;
                display: inline-block;
                width: 30%;
            }

            .post-item img {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <script src="${pageContext.servletContext.contextPath}/js/main.js"></script>
        <script>
            getBreedDetail();
            let breedDetailXsl = getXMLDoc('${breedDetailXsl}');
            function getBreedDetail() {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.servletContext.contextPath}/api/breeds/${breedCode}");
                        xhr.send();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                var status = xhr.status;
                                if (status === 0 || (status >= 200 && status < 400)) {
                                    // The xhr has been completed successfully
                                    console.log(xhr.responseXML);
                                    processResult(xhr.responseXML);
                                } else {
                                    alert("Something's wrong");
                                }
                            }
                        };
                    }

                    function processResult(xml) {
                        if (document.implementation && document.implementation.createDocument)
                        {
                            let xsltProcessor = new XSLTProcessor();
                            xsltProcessor.importStylesheet(breedDetailXsl);
                            let resultDocument = xsltProcessor.transformToFragment(xml, document);
                            document.getElementsByTagName("body")[0].append(resultDocument);
                        }
                    }

        </script>
    </body>
</html>
