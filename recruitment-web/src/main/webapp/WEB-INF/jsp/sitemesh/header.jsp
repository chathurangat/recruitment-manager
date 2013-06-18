<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header class="page-header">

    <div id="header">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#"><spring:message code = "header.application.name"/>  </a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li><a href="?language=si"><spring:message code = "localization.language.sinhala"/></a></li>
                            <li><a href="?language=en"><spring:message code = "localization.language.english"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--<div id="header">--%>
    <%--<div class="navbar navbar-inverse navbar-fixed-top">--%>
    <%--<div class="navbar-inner">--%>
    <%--<div class="container-fluid">--%>
    <%--<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">--%>
    <%--<span class="icon-bar"></span>--%>
    <%--<span class="icon-bar"></span>--%>
    <%--<span class="icon-bar"></span>--%>
    <%--</button>--%>
    <%--<a class="brand" href="#">Project name</a>--%>
    <%--<div class="nav-collapse collapse">--%>
    <%--<p class="navbar-text pull-right">--%>
    <%--Logged in as <a href="#" class="navbar-link">Username</a>--%>
    <%--</p>--%>
    <%--<ul class="nav">--%>
    <%--<li class="active"><a href="#">Home</a></li>--%>
    <%--<li><a href="#about">About</a></li>--%>
    <%--<li><a href="#contact">Contact</a></li>--%>
    <%--</ul>--%>
    <%--</div><!--/.nav-collapse -->--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

</header>