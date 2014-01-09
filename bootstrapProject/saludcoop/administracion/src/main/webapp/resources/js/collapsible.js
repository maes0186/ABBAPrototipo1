$(function(){
	
	$(".collapsible-header").click(function(){
		$content = $(this).parent().find(".collapsible-content");
		if ($content.is(":visible")){
			$content.hide();
		}
		else{
			$content.show();
		}
		$(this).toggleClass("collapsed");
	});
	
	
});