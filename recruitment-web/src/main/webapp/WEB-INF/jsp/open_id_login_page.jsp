<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default" />
</head>
<body>

<div id="login-error">${error}</div>

<div class="txt-login"><jsp:text>Sign In</jsp:text></div>


<a href="${facebookLoginUrl}" class="btn-facebook"><img src="../../resources/img/facebook.png"/>  Facebook Account</a>

</body>
</html>