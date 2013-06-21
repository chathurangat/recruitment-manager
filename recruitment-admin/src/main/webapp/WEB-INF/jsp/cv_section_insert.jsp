<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Employee Registration Form ! </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">



    <!-- styles sheets -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <div class="row">


        <div class="span8">

            <form class="form-horizontal" id="cvSectionInsertForm" action="insert/save" method="post">
                <fieldset>
                    <legend>Add New CV sections to the CV section register</legend>

                    <div class="control-group">

                        <div class="controls">
                            <input type="hidden" class="input-xlarge" id="id" name="id" >

                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="sectionName_EN">Section Name English</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="sectionName_EN" name="sectionName_EN" rel="popover" data-content="Enter Section Name English" data-original-title="sectionName_EN">

                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="sectionName_SI">Section Name Sinhala</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="sectionName_SI" name="sectionName_SI" rel="popover" data-content="Enter section Name Singhala" data-original-title="sectionName_SI">

                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="sectionName_TA">Section Name Tamil </label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="sectionName_TA" name="sectionName_TA" rel="popover" data-content="Enter section Name Tamil" data-original-title="sectionName_TA">

                        </div>
                        <br>
                        <label class="checkbox" for="checkbox">
                            <input type="checkbox" id="checkbox">
                            Active
                        </label>
                    </div>



                    <div class="control-group">
                        <label class="control-label" for="submit"></label>
                        <div class="controls">
                            <button type="submit" class="btn btn-success" rel="tooltip" title="first tooltip" id="submit" >Submit</button>

                        </div>

                    </div>



                </fieldset>
            </form>
        </div>

    </div>


    <hr>

    <footer>
        <div class="container">
            <p>&copy; hsenid mobile solutions</p>
        </div>
    </footer>

</div><!--/.fluid-container-->

<!-- javascript files
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="webapp/resources/js/jquery.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-transition.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-alert.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-scrollspy.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="webapp/resources/js/bootstrap-popover.js"></script>
<script type="text/javascript" src="webapp/resources/js/jquery.validate.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#cvSectionInsertForm input').hover(function()
        {
            $(this).popover('show')
        });
        $("#cvSectionInsertForm").validate({
            rules:{
                sectionName_EN:"required",
                sectionName_SI:"required",
                sectionName_TA:"required"

            },
            messages:{
                sectionName_EN:"Enter In English",
                sectionName_SI:"Enter In Sinhala",
                sectionName_TA:"Enter In Tamil"

            },
            errorClass: "help-inline",
            errorElement: "span",
            highlight:function(element, errorClass, validClass) {
                $(element).parents('.control-group').addClass('error');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).parents('.control-group').removeClass('error');
                $(element).parents('.control-group').addClass('success');
            }
        });
    });
</script>

</body>
</html>