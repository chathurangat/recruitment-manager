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



                <div class="control-group">
                    <label class="control-label" for="subject">Subject</label>
                    <div class="controls">
                        <input type="text"  id="subject" name="subject" size="100" >

                    </div>
                </div>
                </div>


                <div class="control-group">
                    <label class="control-label" for="body">Message Content</label>
                    <div class="controls">
                        <textarea  id="body" name="body" rows="15" cols="40" > </textarea>

                    </div>
                </div>

                </div>
                <div class="control-group">
                    <label class="control-label" for="receiver">Receiver</label>
                    <div class="controls">
                        <input type="checkbox"  id="receiver" name="receiver" >

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="date">Date</label>
                    <div class="controls">
                        <input type="checkbox"  id="date" name="date" >

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="time">Time</label>
                    <div class="controls">
                        <input type="checkbox"  id="time" name="time" >

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="venue">Venue</label>
                    <div class="controls">
                        <input type="checkbox"  id="venue" name="body" >

                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="map">Map</label>
                    <div class="controls">
                        <input type="checkbox"  id="map" name="map" >

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