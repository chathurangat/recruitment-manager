<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>
<head>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet"  type="text/css" />
    <link href="../resources/css/bootstrap-responsive.min.css" rel="stylesheet"  type="text/css" />

    <%--<title><decorator:title default="Customized Title Should Goes here"/></title>--%>
    <%--<meta http-equiv="content-type" content="text/html; charset=utf-8"/>--%>
    <%--<meta name="decorator" content="main"/>--%>
    <decorator:head/>
</head>
<body>

<jsp:include page="main-header.jspx"/>


<div class="hero-unit">
    <jsp:include page="banner.jspx"/>
</div>


<div id="content">
    <%--<decorator:body/>--%>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">

                <div class="well sidebar-nav">
                    side bar content from main template
                    <ul class="nav nav-list">
                        <li class="nav-header">Sidebar</li>
                        <li class="active"><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="nav-header">Sidebar</li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="nav-header">Sidebar</li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                    </ul>
                </div><!--/.well -->


            </div>
            <div class="span10">
            <decorator:body/>
            </div>
        </div>
    </div>

    <%--<div class="span10">--%>
        <%--<decorator:body/>--%>
    <%--</div>--%>
</div>

<jsp:include page="footer.jspx"/>

<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="../resources/js/jquery-1.8.0-min.js"><jsp:text /></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/demo.js"></script>
<script type="text/javascript" src="../resources/js/json2.js"></script>
<script type="text/javascript" src="../resources/js/date.format.js"></script>


</body>
</html>