<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Cv Template Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">



    <!-- styles sheets -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">

</head>
<body>
<div class="container">

    <div class="row">


        <div class="span8">

            <form class="form-horizontal" id="cvTemplateInsertForm" action="register" method="post">
                <fieldset>
                    <legend>Create New Cv Template</legend>


               <!--     <div class="control-group">
                        <div class="controls">
                            <input type="hidden" class="input-xlarge" id="id" name="id" >
                        </div>
                    </div>     -->

                    <div class="control-group">
                        <label class="control-label" for="cvHeaderEn">Cv Template Name in English</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="cvHeaderEn" name="cvHeaderEn" rel="popover" data-content="Enter Template Name in English" data-original-title="cvHeaderEn">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="cvHeaderSi">Cv Template Name in Sinhala</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="cvHeaderSi" name="cvHeaderSi" rel="popover" data-content="Enter Template Name in Sinhala" data-original-title="cvHeaderSi">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="cvHeaderTa">Cv Template Name in English</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="cvHeaderTa" name="cvHeaderTa" rel="popover" data-content="Enter Template Name in Tamil" data-original-title="cvHeaderTa">
                        </div>
                    </div>

        <c:forEach items="${cvApplicationSection}" var="sectionName"  >
          <fieldset name="${sectionName.sectionNameEn}">
                        <legend>
                                ${sectionName.sectionNameEn}

                        </legend>
                 <div class="control-group">
                     <label class="checkbox inline">
                         <input type="checkbox" for="status" id="status"> status </td>
                      </label>

                <label for="${sectionName.sectionNameEn}">${sectionName.sectionNameEn}</label>

                     <!--the following hidden textboxes are used to insert cv section names in three languages to cv template collections-->
                     <input type="hidden"  id="sectionNameEn" name="sectionNameEn" value="cvApplicationSection.sectionNameEn">
                     <input type="hidden"  id="sectionNameSi" name="sectionNameSi" value="cvApplicationSection.sectionNameSi">
                     <input type="hidden"  id="sectionNameTa" name="sectionNameTa" value="cvApplicationSection.sectionNameTa">

                <td >
                    <select id="priority">
                        <c:forEach begin="1" end="${fn:length(cvApplicationSection)}" varStatus="loop">
                            <option value="${loop.index}">${loop.index}</option>
                        </c:forEach>
                    </select>
                </div>
             </fieldset>
          </c:forEach>


                    <div class="control-group">
                        <label class="control-label" for="submit"></label>
                        <div class="controls">
                            <button type="submit" class="btn btn-success" rel="tooltip" title="first tooltip" id="submit" >Submit</button>
                        </div>
                    </div>



                </fieldset>
            </form>
        </div>  <!--/span8-->
      </div>  <!--/row-->
</div><!--/container-->

</body>
</html>