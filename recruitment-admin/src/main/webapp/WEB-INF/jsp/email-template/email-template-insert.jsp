<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">

</head>
<body>



<div class="container">

    <div class="row">


        <div class="span8">


            <sf:form class ="form-horizontal" method="POST" action="email_insert"  modelAttribute="emailTemplate">
            <legend>Add New Email Templates</legend>

                <div class="control-group">
                    <label class="control-label" for="templateType">Template Type</label>
                    <div class="controls">
                        <input type="text"  id="templateType" name="templateType" size="100">

                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="subject">Subject</label>
                    <div class="controls">
                        <input type="text"  id="subject" name="subject" size="100" >

                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="receiver">Receiver</label>
                    <div class="controls">
                        <input type="text"  id="receiver" name="receiver" size="100">

                    </div>
                </div>


                <div class="control-group">
                    <label class="control-label" for="body">Message Content</label>
                    <div class="controls">
                        <input type="text"  id="body" name="body" size="500">

                    </div>
                </div>



                <div class="control-group">
                <label class="control-label" for="submit"></label>
                <div class="controls">
                    <button type="submit" name="submit" class="btn btn-success" rel="tooltip" title="first tooltip" id="submit" >Submit</button>

                </div>
                </sf:form>


</body>
</html>