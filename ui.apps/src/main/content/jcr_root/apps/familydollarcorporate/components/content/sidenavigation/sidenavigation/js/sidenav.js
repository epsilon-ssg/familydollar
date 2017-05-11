 $(document).ready(function() {

        $('.accordion .toggle').on('click', function(e) {
            e.preventDefault();
          
            var $this = $(this);
            
            if ($this.next().hasClass('show')) {
                $this.next().removeClass('show');
                $this.next().slideUp(350);
                 $this.removeClass('open')
            } else {
                $this.addClass('open');
                $this.parent().parent().find('li .inner').removeClass('show');
                $this.parent().parent().find('li .inner').slideUp(350);
                $this.next().toggleClass('show');
                $this.next().slideToggle(350);
            }
        });

    });