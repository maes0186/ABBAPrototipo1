/**
 * 
 * @param parent - Alert Container, if null alert must be manually appended
 * @param conf - {
 * 		type: [success, info, warning, danger],
 *		message: TEXTO PARA MOSTRAR,
 * 		[dismissable]: [true, false],
 * 		[autoOpen]:  [true, false],
 *		[id]: ID DEL DIV,
 *		
 * }
 * 
 */
function createAlertMessage(parent, conf){
	var alertDiv = $(	"<div "+(conf.id!=undefined?"id='"+conf.id+"'":"")+" class='alert "+(conf.dismissable?"alert-dismissable":"")+" alert-"+conf.type+"' style="+(!conf.autoOpen?"'display:none'":"")+">"+
			(conf.dismissable?"<button type='button' class='close' data-dismiss='alert'>×</button>":"")+conf.message+
						"</div>"	);
	if (parent != undefined && parent!=null){
		$(parent).append(alertDiv);
	}
	
	return alertDiv;
	
}