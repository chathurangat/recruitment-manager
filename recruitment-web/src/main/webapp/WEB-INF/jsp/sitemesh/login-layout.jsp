<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" />

    <title><decorator:title default="Customized Title Should Goes here"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="decorator" content="main"/>
    <decorator:head/>
</head>
<body>

<%--<jsp:include page="header.jsp"/>--%>


<div class="hero-unit">
    <jsp:include page="banner.jsp"/>
</div>


<div id="content">
    <decorator:body/>
</div>

<jsp:include page="footer.jsp"/>

<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/demo.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>

</body>
</html>