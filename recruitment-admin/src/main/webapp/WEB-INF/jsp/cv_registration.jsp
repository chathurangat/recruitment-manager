<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="" method="post">
    <table>
        <tr>
            <td rowspan="3"><label for="templateName">Cv Template Name</label></td>
            <td><label for="cvHeaderEn">Header Name in English</label><input id="cvHeaderEn" name="cvHeaderEn" type="text"/><br/>
                <label for="cvHeaderSi">Header Name in Sinhala</label><input id="cvHeaderSi" name="cvHeaderSi" type="text"/><br/>
                <label for="cvHeaderTa">Header Name in Tamil</label><input id="cvHeaderTa" name="cvHeaderTa" type="text"/>
            </td>
        </tr>
        <c:forEach items="${cvApplicationSection.sectionNameEn}" var="sectionNameEn"  >
            <tr>
                <td><input type="checkbox" for="status"> status </td>

                <td><label for="${cvApplicationSection.sectionNameEn}">${cvApplicationSection.sectionNameEn}</label></td>

                <td >
                    <select>
                        <c:forEach begin="1" end="${fn:length(cvApplicationSection.sectionNameEn)}" varStatus="loop">
                            <option value="${loop.index}">${loop.index}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </c:forEach>
        <tr><td><button type="submit" id="submit" >Register</button></td></tr>

    </table>
</form>

</body>
</html>