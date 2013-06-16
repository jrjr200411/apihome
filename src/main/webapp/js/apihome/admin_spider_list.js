$(document).ready(function() {
	$('.settingOpt').click(function(e){
		var id = this.id;
		$.Dialog({
			'title' : '开启爬虫任务',
			'content' : '<div class="span5" style="margin-left:10px;">'+
							'<h3>填写Page参数</h3>'+
							'<div class="row">'+
								'<div class="span2">'+
									'<div class="input-control as-block text">'+
										'<input name="pageFrom" id="pageFrom" type="number" placeholder="起始位置"/>'+
									'</div>'+
								'</div>'+ 
								'<div class="span2">'+
									'<div class="input-control as-block text">'+
										'<input name="pageTo" id="pageTo" type="number" placeholder="结束位置"/>'+
									'</div>'+  
								'</div>'+ 
							'</div>'+
						'</div>',
			'buttons' : {
				'Ok' : {
					'action': function() {
						var pageFrom = $("#pageFrom").val();
						var pageTo = $("#pageTo").val();
						if(pageFrom < 0 || pageTo < 0 || (pageFrom > pageTo))
						{
							alert("参数非法");
							return false;
						}
						
						var data = {};
						data.ruleId = id;
						data.pageFrom = pageFrom;
						data.pageTo = pageTo;
						$.ajax({
							type: "get",
							async: true,
							url: "/spiderUed/spiderTask",
							data: data,
							dataType:'json', 
							success: function(data){
								
							}
						});
					}
				},
				'Cancel' : {
					'action': function() {
						
					}
				}
			}
		});
	}); 
});