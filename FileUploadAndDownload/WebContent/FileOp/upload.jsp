<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文件上传</title>

    <!-- Bootstrap -->
    <link href="../bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/UploadFileServlet" enctype="multipart/form-data" method="post">
    	<div class="form-group">
    		<label for="username" class="control-label col-md-2">上传用户：</label>
    		<div class="col-md-10">
    			<input class="form-control" id="username"  type="text" name="username" />
    		</div>
    	</div>
    	<div class="form-group">
    			<label for="file1" class="control-label col-md-2">上传文件1：</label>
    		<div class="col-md-10">
    			<input class="form-control" id="file1"  type="file" name="file1" />
    		</div>
    	</div>
    	<div class="form-group">
    			<label for="file2" class="control-label col-md-2">上传文件2：</label>
    		<div class="col-md-10">
    			<input class="form-control" id="file2"  type="file" name="file2" />
    		</div>
    	</div>
    	<div class="form-group">
    		<div class="col-md-10 col-md-offset-2">
    			<button type="submit" class="btn btn-default">上传文件</button>
    		</div>
    	</div>
    	
    	
    </form>
    
    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.1.0.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
  </body>
</html>