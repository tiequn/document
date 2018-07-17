带有文件的表单的提交和文件的上传保存
 1.表单提交必须是post 
 2.表单增加属性 enctype="multipart/form-data"
 3.普通数据的input 默认name属性desc
 4.文件的input name属性必须是 file
 5.servlet接收端不收web端的影响
 
 
 <form action="/upload3" method="post" enctype="multipart/form-data">
<input type="text" name="desc" /><br /> 
<input type="file" name="file" />
<button>开始上传</button>
</form>

<img src="/download?file=c5724829-8d04-4c72-9c94-da64460cb5f2.jpg" height="200" alt="">
<a href="/download?file=c5724829-8d04-4c72-9c94-da64460cb5f2.jpg&name=you.jpg">下载照片</a>
<a href="/download?file=我的照片.jpg">下载我的照片</a>
<a href="/download?file=my.pdf">下载PDF文件</a>
<a href="/download?file=commons-collections4-4.1-bin.zip">下载zip文件</a>



使用webuploader提交表单和文件数据
1.导入 /static/bootstrap/css/bootstrap.css 
2.导入/static/plugins/uploader/webuploader.css
3.导入/static/dist/js/jquery3.js
4.导入/static/plugins/uploader/webuploader.js
<div id="picker">文件上传</div> // 设置文件上传按钮


   $(function(){
             //富文本框插入图片
         var editor = new Simditor({
			textarea : $('#editor'),
			//添加插入图片弹框
			upload:{
				url:"/img/uploader",//指定图片上传的服务
				fileKey:"file"
			}
		});
    	 //初始化webuploader 
        var uploader= WebUploader.create({
    	swf: '/static/plugins/uploader/Uploader.swf',//指定使用flash上传的插件
    	server:"upload3",//文件上传的服务路径
    	pick:'#picker',//上传按钮 弹出选择文件窗口
    	auto:'true',//是否自动上传
    	fileNumLimit:"1",//只允许上传一个图片每次
    	//只允许选择图片文件。
    	 accept:{
    		title:'Images',
    		extensions:'gif,jpg,png,jpeg',
    		mimeTypes:'image/*'//mime类型
    	} 
    }); 
    	// 当有文件被添加进队列的时候（可选 在页面上可能是隐藏的div）
		uploader.on('fileQueued', function(file) {
			$("#fileList").append(
    			'<div id="' + file.id + '" class="item">'
    					+ '<h4 class="info">' + file.name + '</h4>'
    					+ '</div>');
		});
    	 //文件的上传进度
    	 uploader.on("uploadProgress",function(file,percentage){
    		 $("#span").text(parseInt(percentage*100)+"%");
    	 });
    	 //文件上传成功（同会接到后端成功的响应数据）
    	 uploader.on("uploadSuccess",function(file,resp){
		//传过来文件下载请求 添加到指定位置图片src中 实现图片的预览
		//file_path和success 是富文本框接受ajax响应的格式，会自动把图片file_path 添加到src 在插入部位显示
		//servlet发送数据格式以富文本框为 标准
    	  $("#img").attr("src", resp.file_path);
    	    
    	//把生成的唯一的图片名添加到 隐藏input中 和表单数据一同提交
    		$("#imgName").attr("value",resp.fileName);
        	 })，
    	 //文件上传错误
    	 uploader.on("uploadError",function(file){
    		 $("#"+file.id).find("span").text("上传失败");
    	 });
    	 //点击按钮时，开始上传
    	 $("#btn").click(function(){
    		 uploader.upload();
    	 });
     });