$(function(){
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
		
		$(".dropdown").hover(function(){
				$(this).addClass("open");
			},function(){
				$(this).removeClass("open");
		});
		var getWidth = $("body").width();
		
			$('#sidebar-wrapper .sidebar-nav .dropdown a').on('click',function(){
				if(getWidth < 980){
					$(this).find('span').toggleClass('glyphicon-triangle-bottom');
					$(this).parent().toggleClass('open');
					$(this).parent().find(".drop-down-menus").toggle('fast');
				}else{
					//$("#wrapper").removeClass("toggled");
				}
			});
		
		resizeMenuBar();
	});
	$(window).resize(function(){
		resizeMenuBar();
		
	});
	function resizeMenuBar(){
		var getWidth = $(window).width();
		if(getWidth > 980){
			$(".drop-down-menus").addClass("dropdown-menu");
		}else{
			try{
				$(".drop-down-menus").removeClass("dropdown-menu");
				$("#wrapper").removeClass("toggled");
			}catch(e){
				//
			}
		}
	}