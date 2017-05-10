(function(){
	// component specific js

  $(".multimedia-slider").slick({
	  autoplay: true,
	  autoplaySpeed: 3000,
	  dots: false,
	  infinite: false,
	  speed: 3000,
	  fade: true,
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  arrows: false,
	  centerPadding: '40px',
	  responsive: [
	    {
	      breakpoint: 1024,
	      settings: {
	        slidesToShow: 1,
	        slidesToScroll: 1,
	        infinite: true,
	        dots: false
	      }
	    },
	    {
	      breakpoint: 600,
	      settings: {
	        slidesToShow: 1,
	        dots: false,
	        slidesToScroll: 1
	      }
	    },
	    {
	      breakpoint: 480,
	      settings: {
	        slidesToShow: 1,
	        dots: false,
	        slidesToScroll: 1
	      }
	    }
	  ]
	});

})();