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
                            <%--<li class="active"><a href="#">Home</a></li>--%>
                            <%--<li><a href="#">Get started</a></li>--%>
                            <%--<li><a href="#">Errors &amp; Validations</a></li>--%>
                            <%--<li><a href="#">Forms</a></li>--%>
                            <li><a href="?language=si"><spring:message code = "localization.language.sinhala"/></a></li>
                            <li><a href="?language=en"><spring:message code = "localization.language.english"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>