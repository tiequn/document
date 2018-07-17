$(function(){
			
			$("#loginBtn").click(function(){
				$("#loginForm").submit();
			});
			
			$("#loginForm").validate({
				errorElement:'span',
				errorClass:'text-danger',
				rules:{
					adminName : {
						required : true
				    }
				}
				messages:{
					adminName : {
						required : "请输入用户名"
					}
				},
				//表单提交句柄（改为ajax提交替代表单自带提交）
				submitHandler:function(){
					$.ajax({
						url:"/login",
						type:"post",
						/* data:{
							"adminName":$("#adminName").val(),
							"password":$("#password").val()
						}, */
						data:$("#loginForm").serialize(),//表单数据的序列化封装 传递
						//在提交完成前的提交按钮样式操作
						beforeSend:function(){
							$("#loginBtn").attr("disabled","disabled").text("登录中...");
						},
						success:function(res){
							if(res.state == "success") {
								//当发起的请求是过滤的请求切不为空时，直接重定向
								if(callback){
								window.location.href=callback;
								}else{
									window.location.href="/filmList";
								}
								//当登录成功后，跳转
							} else {
							//提交失败的div内容展示 把隐藏属性变为非隐藏
								$("#error").text(res.message).show();
							}
						},
						error:function(){
							alert("系统繁忙");
						},
						//提交完成后的按钮样式展示
						complete:function(){
							$("#loginBtn").removeAttr("disabled").text("进入管理系统");
						}
					})
				}
			})
		});