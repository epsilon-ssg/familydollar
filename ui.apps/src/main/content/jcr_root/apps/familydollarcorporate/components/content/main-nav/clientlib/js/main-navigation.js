// JavaScript Document

$(function(){
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper,.header").toggleClass("toggled");
		});
		
		$(".dropdown").hover(function(){
				$(this).addClass("open");
			},function(){
				$(this).removeClass("open");
		});				
		$('#sidebar-wrapper .sidebar-nav .dropdown a').on('click',function(){
			var getWidth = $(window).width();															
				if(getWidth < 980){ 
					var parent = $(this).parent();
					if($(this).parent().find(".drop-down-menus").is(":hidden")){
						$(this).find('span').addClass('glyphicon-triangle-bottom');
						parent.addClass('open');
						parent.find(".drop-down-menus").show();
					}else{
						$(this).find('span').removeClass('glyphicon-triangle-bottom');
						parent.removeClass('open');
						parent.find(".drop-down-menus").hide();
					}
				}else{
				}
		});
		
		resizeMenuBar();
	});
	$(window).resize(function(){
		resizeMenuBar();
		
	});
	function resizeMenuBar(){
		var getWidth = $("body").width();
		$("ul.drop-down-menus").removeAttr('style');
		$("li.dropdown").removeClass("open");
		if(getWidth < 980){
			$(".drop-down-menus").removeClass("dropdown-menu");
		}else{
			try{
				$(".drop-down-menus").addClass("dropdown-menu");
				$("#wrapper,.header").removeClass("toggled");
				$('#sidebar-wrapper .sidebar-nav .dropdown a').find(".glyphicon-triangle-bottom").removeClass("glyphicon-triangle-bottom");
			}catch(e){
				//
			}
		}
	}