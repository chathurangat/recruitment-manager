<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header class="page-header">

    <div id="header">
    <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
    <div class="container-fluid">
    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    </button>
    <a class="brand" href="#"><spring:message code = "header.application.name"/> </a>
    <div class="nav-collapse collapse">
    <ul class="nav">

    <li><a href="?language=en"><spring:message code = "localization.language.english"/></a></li>
        <li><a href="?language=si"><spring:message code = "localization.language.sinhala"/></a></li>
        <li><a href="?language=ta"><spring:message code = "localization.language.tamil"/></a></li>
    </ul>
    </div><!--/.nav-collapse -->
    </div>
    </div>
    </div>
    </div>

</header>