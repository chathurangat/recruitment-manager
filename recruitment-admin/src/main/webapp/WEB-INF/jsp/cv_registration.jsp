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
 <c:forEach items="${cvApplicationSection.sectionName }" var="sectionName"  >
 <tr>
     <td rowspan="3" ><input type="checkbox" for="status"> status </td>

     <td>
     <!-- label for section name in English -->
    <label for="${sectionName.sectionNameEN}">
           Section Name in English
    </label>
    <input id="${sectionName.sectionNameEN}" name="${sectionName.sectionNameEN}" type="text" value="${sectionName.sectionNameEN}"/>
    <br/>

     <!-- label for section name in Sinhala  -->
     <label for="${sectionName.sectionNameSI}">
         Section Name in Sinhala
     </label>
     <input id="${sectionName.sectionNameSI}" name="${sectionName.sectionNameSI}" type="text" value="${sectionName.sectionNameSI}"/>
     <br/>

     <!-- label for section name in Tamil  -->
     <label for="${sectionName.sectionNameTA}">
         Section Name in Tamil
     </label>
     <input id="${sectionName.sectionNameTA}" name="${sectionName.sectionNameTA}" type="text" value="${sectionName.sectionNameTA}"/>
     <br/>
     </td>

     <td rowspan="3">
     <select>
         <c:forEach begin="0" end="${fn:length(cvApplicationSection.sectionName)}" varStatus="loop">
             <option value="${loop.index}">${loop.index}</option>
         </c:forEach>
     </select>
     </td>
 </tr>
</c:forEach>
<tr><td colspan="3"><button type="submit" id="submit" >Register</button></td></tr>

</table>
</form>

</body>
</html>