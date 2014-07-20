// JavaScript Document
$(document).ready(function(){

	var hide_button = $('<span id="hide-button"><br />F<br />e<br />e<br />d<br />b<br />a<br />c<br />k</span>').appendTo("#customize")
	
	var initial_left = parseInt($('#customize').css('left'));

	$('#hide-button').live('click', function(){
		var container = $('#customize');
		var width = parseInt(container.outerWidth());
		if( !container.hasClass('hidden') ) {
			container.animate( {'left': -1*width+5});
			container.addClass('hidden');
		}else {
			container.removeClass('hidden');
			container.animate( {'left': initial_left });
		}
	});

	var container = $('#customize');
	var width = parseInt(container.outerWidth());
	if( !container.hasClass('hidden') ) {
			container.animate( {'left': -1*width+5});
			container.addClass('hidden');
	}
	
});

