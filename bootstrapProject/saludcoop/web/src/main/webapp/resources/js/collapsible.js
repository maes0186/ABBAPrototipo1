$(function(){
	
	$(".collapsible-header").click(function(){
		$content = $(this).parent().find(".collapsible-content");
		if ($content.is(":visible")){
	        $content.slideToggle();
		} else{
	        $content.slideToggle();
		}
		$(this).toggleClass("collapsed");
	});
	
	
});