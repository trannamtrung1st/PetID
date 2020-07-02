<%-- 
    Document   : index
    Created on : Jun 28, 2020, 1:49:40 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
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

            .img-preview{
                padding: 10px;
                width: 25%;
            }

            .breed-item {
                vertical-align: top;
                padding: 10px;
                display: inline-block;
                width: 30%;
            }

            .breed-item img {
                width: 100%;
            }

            .breed-item h4 {
                color: red;
            }

            .d-none {
                display: none;
            }

            hr {
                margin: 50px 0;
            }
        </style>
    </head>
    <body>
        <h1 class="page-title">WELCOME TO PET-ID</h1>
        <h1>We have ${count} breeds of pet (cat & dog)</h1>
        <form id="upload-form" method="POST" enctype="multipart/form-data" >
            <input type="hidden" name="action" value="submit_image"/>
            <div>
                <h3>Upload an image and let we guest what it is: </h3>
                <input type="file" name="file" accept="image/*"/>
            </div>
            <div>
                <img class="d-none"/>
            </div>
            <div>
                <button type="button" onclick="guestPetByImage()">SUBMIT</button>
            </div>
        </form>
        <div id="top-outputs">
        </div>
        <hr/>
        <form id="search-form" method="GET" onsubmit="return validateSearchPostback();">
            <div>
                <h3>Or search breed's name: </h3>
                <input type="text" name="q" value="${param.q}"/>
            </div>
            <div>
                <input type="submit" value="SUBMIT" />
            </div>
        </form>
        <hr/>
        <x:transform doc="${listXml}" xslt="${xsl}">
            <x:param name = "query" value="${param.q}"/>
        </x:transform>
        <c:if test="${not empty param.q}"> <!--temp-->
        </c:if>
        <script src="${pageContext.servletContext.contextPath}/js/main.js"></script>
        <script>
            let guestEnabled = true;
            document.querySelector("#upload-form input[name=file]").onchange = function (e) {
                document.querySelector("#upload-form button").removeAttribute("disabled");
                guestEnabled = true;
                var reader = new FileReader();
                reader.onload = function (e) {
                    // get loaded data and render thumbnail.
                    let src = e.target.result;
                    let ele = document.querySelector("#upload-form img");
                    ele.src = src;
                    ele.setAttribute("class", "img-preview");
                };
                // read the image file as a data URL.
                if (this.files[0])
                    reader.readAsDataURL(this.files[0]);
                else {
                    document.querySelector("#upload-form img").setAttribute("class", "d-none");
                }
            };

            function validateSearchPostback() {
                let value = document.querySelector("#search-form input[name=q]").value;
                if (value === '${param.q}') {
                    alert("The search value has not been changed");
                    return false;
                }
                return true;
            }

            let breedsXML = '${listXml}';
            let xmlDoc = getXMLDoc(breedsXML);
            let topXsl = getXMLDoc('${topXsl}');

            function guestPetByImage() {
                if (!guestEnabled)
                    return;
                document.querySelector("#upload-form button").setAttribute("disabled", "true");
                guestEnabled = false;
                let topOutputs = document.getElementById("top-outputs");
                topOutputs.innerHTML = "";
                var value = document.querySelector("#upload-form input[name=file]").value;
                if (!value)
                    return alert("Choose an image");
                var form = document.getElementById("upload-form");
                var formData = new FormData(form);
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "${applicationScope.baseApiUrl}/api/pet-id");
                xhr.setRequestHeader("Accept", "application/xml");
                xhr.send(formData);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        var status = xhr.status;
                        if (status === 0 || (status >= 200 && status < 400)) {
                            // The xhr has been completed successfully
                            console.log(xhr.responseXML);
                            processGuestResult(xhr.responseXML);
                        } else {
                            alert("Something's wrong");
                        }
                    }
                };
            }

            function processGuestResult(xml) {
                let xRes = xpathEval("//topOutput", xml);
                let count = 1;
                let currentOutput;
                while ((currentOutput = xRes.iterateNext())) {
                    currentOutput = getXMLDoc(currentOutput.outerHTML);
                    console.log(currentOutput);
                    let code = null;
                    code = xpathEval("//label", currentOutput, XPathResult.STRING_TYPE).stringValue;
                    let score = xpathEval("//score", currentOutput, XPathResult.NUMBER_TYPE).numberValue;
                    score = parseInt((score * 100).toString());
                    let topXml = xpathEval("//PetBreed[code='" + code + "']", xmlDoc, XPathResult.FIRST_ORDERED_NODE_TYPE).singleNodeValue;
                    displayTopResult(topXml, count++, score);
                }
            }

            function displayTopResult(xml, top, rate)
            {
                console.log(xml);
                let topOutputs = document.getElementById("top-outputs");
                if (document.implementation && document.implementation.createDocument)
                {
                    let xsltProcessor = new XSLTProcessor();
                    xsltProcessor.setParameter(null, "top", top.toString());
                    xsltProcessor.setParameter(null, "rate", rate.toString());
                    xsltProcessor.importStylesheet(topXsl);
                    let resultDocument = xsltProcessor.transformToFragment(xml, document);
                    topOutputs.append(resultDocument);
                }
            }

        </script>
    </body>
</html>
