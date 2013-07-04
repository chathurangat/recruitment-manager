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


            <sf:form class ="form-horizontal" method="POST" action=""  modelAttribute="emailTemplate">
            <legend>Generate your email </legend>

            <div class="control-group">
                <label class="control-label" for="toAddress">To :</label>
                <div class="controls">
                    <input type="text"  id="toAddress" name="toAddress" size="100">

                </div>
            </div>
                <div class="control-group">
                    <label class="control-label" for="from">From :</label>
                    <div class="controls">
                        <input type="text"  id="from" name="from" size="100">

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="ccAddress">Cc :</label>
                    <div class="controls">
                        <input type="text"  id="ccAddress" name="ccAddress" size="100">

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="bccAddress">Bcc :</label>
                    <div class="controls">
                        <input type="text"  id="bccAddress" name="bccAddress" size="100">

                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="replyTo">Reply To:</label>
                    <div class="controls">
                        <input type="text"  id="replyTo" name="replyTo" size="100">

                    </div>
                </div>


                <legend></legend>



                <c:forEach var="emailTemlate" items="${emailTemplateList}">

                        <p>
                            <label class="control-label" for="hi"> Hi..</label>
                            <input type="text"  id="hi" name="replyTo" size="100">
                        </p>
                        <p>
                                ${emailTemlate.subject}

                        </p>
                        <p>
                                ${emailTemlate.body}
                        </p>
                        <p>
                                ${emailTemlate.date}
                    <div class="controls">
                        <input type="date"  id="date" name="date" size="100">
                    </div>

                    <br>
                               ${emailTemlate.time}
                    <div class="controls">
                        <input type="time"  id="time" name="time" size="100">
                    </div>

                       <br>
                                ${emailTemlate.venue}
                    <div class="controls">
                        <input type="text"  id="venue" name="venue" size="100">
                    </div>

                        </p>
                    </c:forEach>

                </sf:form>


                <div class="control-group">
                    <label class="control-label" for="submit"></label>
                    <div class="controls">
                        <button type="submit" name="submit" class="btn btn-success" rel="tooltip" title="first tooltip" id="submit" >Send Email</button>
                    </div>
                </div>

</body>
</html>