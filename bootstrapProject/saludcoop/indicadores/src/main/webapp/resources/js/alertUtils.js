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
	var alertDiv = $(	"<div "+(conf.id!=undefined?"id='"+conf.id+"'":"")+" class='alert "+(conf.dismissable?"alert-dismissable":"")+" alert-"+conf.type+"' style="+(!conf.autoShow?"'display:none'":"")+">"+
			(conf.dismissable?"<button type='button' class='close' data-dismiss='alert'>×</button>":"")+conf.message+
						"</div>"	);
	if (parent != undefined && parent!=null){
		$(parent).append(alertDiv);
	}
	
	return alertDiv;
	
}

/**
 * Construye el mensaje de error a partir de la lista <code>errors</code> indicada por parámetro
 * @param parent
 *          Componente padre en el que se mostrarán los errores generados
 * @param errors
 *          Lista de errores a mostrar
 */
function createErrorMessages(parent, errors) {
    var alertDiv = $("<div class='alert alert-dismissable alert-danger'>"+
            "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
    $.each(errors, function(i, value) {
        var span = "<div><i class='icon-warning-sign'></i> "+value+"<div>";
        alertDiv.append(span);
    });
    $(parent).append(alertDiv);
}

/**
 * Construye el mensaje a partir de la lista <code>msgs</code> indicada por parámetro, del tipo indicado
 * @param parent
 *          Componente padre en el que se mostrarán los mensajes generados
 * @param msgs
 *          Lista de mensajes a mostrar
 * @param type
 *          Tipo de mensaje a generar: info, warning, success
 *          
 */
function createMessages(parent, msgs, type, icon) {
    var alertDiv = $("<div class='alert alert-dismissable alert-"+type+"'>"+
            "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
    $.each(msgs, function(i, value) {
        var span = "<div><i class='"+icon+"'></i> "+value+"<div>";
        alertDiv.append(span);
    });
    $(parent).append(alertDiv);
}

function log(message){
	if (console)
		console.log(message);
}