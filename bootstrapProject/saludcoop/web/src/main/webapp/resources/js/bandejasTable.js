var renderTo;
var items = 0;
var autorizadas = 0;
var devueltas = 0;

function setContainer(container){
	renderTo = container;
}
function createHeader(table, dataSet, isSubTable){
    var rowHead = $('<thead></thead>');
    var row = $('<tr  class="active"></tr>');
    
    if(!isSubTable) {
        row.append('<td></td>');        
    }
                    
    for (var dataProp in dataSet[0]){
        // se verifica si no es una propiedad para generar una tabla anidada y la propiedad no es nula para generar el encabezado
    	if (dataProp != 'subitems' && dataSet[0][dataProp] != null){            		
    		var titulo =""; 
    		var partes = dataProp.replace(/([A-Z])/g, ",$1").split(",");
    		for (var i = 0; i < partes.length; i++){
    			parte = partes[i];
    			titulo += parte.substring(0,1).toUpperCase() + parte.substring(1) + " ";
    		}
    		titulo = titulo.substring(0, titulo.length-1);
    		row.append('<td>' + titulo + '</td>');        
    	}
    };
    row.append('<td></td>');
    rowHead.append(row);
    table.append(rowHead);
}
function createHeaderRedirec(table, dataSet, isSubTable){
    var rowHead = $('<thead></thead>');
    var row = $('<tr  class="active"></tr>');
    
    if(!isSubTable) {
        row.append('<td></td>');        
    }
                    
    for (var dataProp in dataSet[0]){
    	if("esNivelAutorizacionAuditor"!=dataProp){
        // se verifica si no es una propiedad para generar una tabla anidada y la propiedad no es nula para generar el encabezado
    	if (dataProp != 'subitems' && dataSet[0][dataProp] != null){            		
    		var titulo =""; 
    		var partes = dataProp.replace(/([A-Z])/g, ",$1").split(",");
    		for (var i = 0; i < partes.length; i++){
    			parte = partes[i];
    			titulo += parte.substring(0,1).toUpperCase() + parte.substring(1) + " ";
    		}
    		titulo = titulo.substring(0, titulo.length-1);
    		row.append('<td>' + titulo + '</td>');        
    	}
    	}
    };
    row.append('<td></td>');
    rowHead.append(row);
    table.append(rowHead);
}
function createHeaderSinID(table, dataSet, isSubTable){
    var rowHead = $('<thead></thead>');
    var row = $('<tr  class="active"></tr>');
    
    if(!isSubTable) {
        row.append('<td></td>');        
    }
                    
    for (var dataProp in dataSet[0]){
        // se verifica si no es una propiedad para generar una tabla anidada y la propiedad no es nula para generar el encabezado
    	if (dataProp != 'subitems' && dataSet[0][dataProp] != null){            		
    		var titulo =""; 
    		var partes = dataProp.replace(/([A-Z])/g, ",$1").split(",");
    		for (var i = 0; i < partes.length; i++){
    			parte = partes[i];
    			titulo += parte.substring(0,1).toUpperCase() + parte.substring(1) + " ";
    		}
    		titulo = titulo.substring(0, titulo.length-1);
    		if(titulo != 'Id'){
    			row.append('<td>' + titulo + '</td>');
    		}
    	}
    };
    row.append('<td></td>');
    rowHead.append(row);
    table.append(rowHead);
}
function createTableExpandibleAsync (dataSet, actualPage, totalPages, totalItems){
	var table  = $('<table id="solicitudesTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeader(table, $(dataSet), false);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
		if (node.numeroAutorizacion != null)
			numero = node.numeroAutorizacion;
		else
			numero = node.numeroSolicitud;
				
	    // Se reinicia la cuenta de las solicitudes por estado
	    pendientes = 0;
	    devueltas = 0;
	    autorizadas = 0;
	    var tdBadges = $('<td><span class="badge badge-info" title="Items solicitud" style="font-size: 14px;" id="spanCollapse_'+ numero +'">+</span></td>');
	    
	    var col = 1;
		count++;
			var row = $('<tr data-toggle="collapse" data-target="#itemsSolicitudNro_'+ numero +'"	class="accordion-toggle" onclick="cargarItemsSolicitud('+numero+');"></tr>');
		for (var dataProp in node){
		    if(node[dataProp] != null) {
		    	// Se verifica si se trata de la primera columna para añadir la columna de los badges
    			if(col == 1) {
    				col = 0;
    			    row.append(tdBadges);
    			}
    			row.append('<td>' + node[dataProp] + '</td>');
		    }
		}

        row.prepend(tdBadges);
        
		row.append('<td></td>');
		tbody.append(row);	
		var hiddenRow ='<tr><td colspan="'+Object.keys(node).length+'" class="hiddenRow"><div class="accordian-body collapse col-lg-12" id="itemsSolicitudNro_'+ numero +'"></div></td></tr>';
		tbody.append(hiddenRow);
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	renderTo.append('<span class="label-info-cantidad" > Se encontraron un total de <b>'+totalItems+'</b> items </span>');
	createPagination(actualPage, totalPages);

}
function createTableExpandibleAsyncRedirec (dataSet, actualPage, totalPages,iconHeader){
	var table  = $('<table id="solicitudesTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeaderRedirec(table, $(dataSet), false);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
		if (node.numeroAutorizacion != null)
			numero = node.numeroAutorizacion;
		else
			numero = node.numeroSolicitud;
				
	    // Se reinicia la cuenta de las solicitudes por estado
	    pendientes = 0;
	    devueltas = 0;
	    autorizadas = 0;
	    var tdBadges = $('<td><span class="badge badge-info" title="Items solicitud" style="font-size: 14px;" id="spanCollapse_'+ numero +'">+</span></td>');
	    
	    var col = 1;
		count++;
		var row = $('<tr data-toggle="collapse" data-target="#itemsSolicitudNro_'+ numero +'"	class="accordion-toggle" onclick="cargarItemsSolicitudRedirec(\''+numero+'\');"></tr>');
		for (var dataProp in node){
		    if(node[dataProp] != null) {
		    	// Se verifica si se trata de la primera columna para añadir la columna de los badges
    			if(col == 1) {
    				col = 0;
    			    row.append(tdBadges);
    			}
    			if("esNivelAutorizacionAuditor"!=dataProp){
    				row.append('<td>' + node[dataProp] + '</td>');
    			}else{
    				console.log(node);
    			}
		    }
		}

        row.prepend(tdBadges);
        
        var title = (iconHeader == 'icon-share-alt') ? 'Redireccionar todos': 'Anular todos';
        
        row.append('<td><a style="cursor:pointer" id="actionTable2" title="' + title + '" onclick="gestionarPadre(\''+numero+'\')"><i style="font-size: 16px;" class="'+iconHeader+'"></i></a></td>'); //New
        
		row.append('<td></td>');
		tbody.append(row);	
		var hiddenRow ='<tr><td colspan="'+Object.keys(node).length+'" class="hiddenRow"><div class="accordian-body collapse col-lg-12" id="itemsSolicitudNro_'+ numero +'"></div></td></tr>';
		tbody.append(hiddenRow);
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	createPagination(actualPage, totalPages);

}

function createTableExpandibleAsyncParcial (dataSet, actualPage, totalPages){
        var table  = $('<table id="solicitudesTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
        createHeaderSinID(table, $(dataSet), false);
        var tbody = $('<tbody></tbody>');
        var count = 0;
        $(dataSet).each(function(i, node){
                numero = node.id;
            // Se reinicia la cuenta de las solicitudes por estado
            pendientes = 0;
            devueltas = 0;
            autorizadas = 0;
            var tdBadges = $('<td></td>');
            
            var col = 1;
            count++;
            var row = $('<tr></tr>');
            for (var dataProp in node){
            	if(dataProp != 'id'){
	                if(node[dataProp] != null) {
	                    // Se verifica si se trata de la primera columna para añadir la columna de los badges
	                    if(col == 1) {
	                        col = 0;
	                        row.append(tdBadges);
	                    }
	                    row.append('<td>' + node[dataProp] + '</td>');
	                    
	                }
            	}
            }
            
            row.prepend(tdBadges);
            row.append('<td><a style="cursor:pointer" id="actionTable2" onclick="verSolicitud(\''+numero+'\')"><i style="font-size: 16px;" class="icon-edit"></i></a></td>'); //New
            row.append('<td></td>');
            tbody.append(row);  
            var hiddenRow ='<tr><td colspan="'+Object.keys(node).length+'" class="hiddenRow"><div class="accordian-body collapse col-lg-12" id="itemsSolicitudNro_'+ numero +'"></div></td></tr>';
            tbody.append(hiddenRow);
        });
        table.append(tbody);
        $(table);
        renderTo.html('');
        renderTo.append(table);
        createPagination(actualPage, totalPages);

    }
    
    

function createTableExpandible(dataSet, actualPage, totalPages){
	var table  = $('<table class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeader(table, $(dataSet), false);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
	    var tdBadges = $('<td><span class="badge badge-info" style="font-size: 14px;" id="spanCollapse_'+ count +'">+</span></td>');
	    var col = 1;
		var row = $('<tr data-toggle="collapse" data-target="#itemNro_'+ count +'" class="accordion-toggle" onclick="cargarSubItem('+count+', this);"></tr>');
		for (var dataProp in node){
		    if(node[dataProp] != null) {
    			if(col == 1) {
    				col = 0;
    			    row.append(tdBadges);
    			}
    			row.append('<td>' + node[dataProp] + '</td>');
		    }
		}
        row.prepend(tdBadges);
		row.append('<td></td>');
		tbody.append(row);	
		var hiddenRow ='<tr><td colspan="'+Object.keys(node).length+'" class="hiddenRow"><div class="accordian-body collapse col-lg-12" id="itemNro_'+ count +'"></div></td></tr>';
		tbody.append(hiddenRow);
		count++;
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	createPagination(actualPage, totalPages);
}
function createPagination(actualPage, totalPages){
	var ul = $('<ul class="pagination pagination-sm"></ul>');
	if(totalPages > 0){
	// Se agrega el elemento para llegar a la primera página
	    var a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="updateSolicitudes('+(1)+')"')+'>&laquo;&laquo;</a>');
	    var li = $('<li class="'+(actualPage==1?'disabled':'')+'"></li>');
	    li.append(a);
	    ul.append(li);
	    
	    // Se agrega el elemento para ir a la página anterior
		a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="updateSolicitudes('+(actualPage - 1)+')"')+'>&laquo;</a>');
		li = $('<li class="'+(actualPage==1?'disabled':'')+'"></li>');
		li.append(a);
		ul.append(li);
	}
	
	// Se realiza la verificación para determinar cuántas y cuáles páginas mostrar
	var resta = actualPage - 5;
	var suma = actualPage + 5;
	var inicio = (resta > 0) ? resta : 1; 
	var fin = (suma < totalPages) ? suma : totalPages;
	
	if(inicio == 1 && fin != totalPages) {
	    var comp = fin + (10 - (fin - inicio)); 
	    fin = comp > totalPages ? totalPages : comp;
	} else if(fin == totalPages && inicio != 1) {
	    var comp = inicio - (10 - (fin - inicio));
	    inicio = comp < 1 ? 1 : comp;
	}
	
	// Se agrega cada una de las páginas
	for (var i = inicio; i <= fin; i++) {
		if (i == actualPage)
			ul.append($('<li class="active"><a>' + i + '</a></li>'));
		
		else
			ul.append($('<li><a style="cursor:pointer" onclick="updateSolicitudes('+(i)+')">' + i + '</a></li>'));
	}
	
	if(totalPages > 0){
		// Se agrega el elemento para ir a la página siguiente
	    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="updateSolicitudes('+(actualPage + 1)+')"')+'">&raquo;</a>');
	    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
	    li.append(a);
	    ul.append(li);
	    
	    // Se agrega el elemento para ir a la última página
	    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="updateSolicitudes('+(totalPages)+')"')+'">&raquo;&raquo;</a>');
	    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
	    li.append(a);
	    ul.append(li);
	}
    // Se utiliza para centrar el paginador
	var div = $('<div align="center"></div>');
	div.append(ul);
	renderTo.append(div);
	$('#actualPage').val(actualPage);
}

function createTableHistorialAsync (dataSet, actualPage, totalPages){
	var table  = $('<table id="historialTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeader(table, $(dataSet), true);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
	    // Se reinicia la cuenta de las solicitudes por estado
	    pendientes = 0;
	    devueltas = 0;
	    autorizadas = 0;

	    var col = 1;
		count++;
		var row = $('<tr></tr>');
		for (var dataProp in node){
		    if(node[dataProp] != null) {
		    	// Se verifica si se trata de la primera columna para añadir la columna de los badges
    			row.append('<td>' + node[dataProp] + '</td>');
		    }
		}

		row.append('<td></td>');
		tbody.append(row);
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	createPagination(actualPage, totalPages);

}
function createTable(dataSet, actualPage, totalPages){
	var table  = $('<table class="bandejaTable table table-condensed"></table>');
	createHeader(table, $(dataSet), false);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
		var tdBadges = $('<td><span class="badge badge-info" style="font-size: 14px;" id="spanCollapse_'+ count +'">+</span></td>');
	    var col = 1;
		var row = $('<tr data-toggle="collapse" data-target="#itemNro_'+ count +'" class="accordion-toggle" onclick="cargarSubItem('+count+', this);"></tr>');
		for (var dataProp in node){
		    if(node[dataProp] != null) {
    			if(col == 1) {
    				col = 0;
    			    row.append(tdBadges);
    			}
    			row.append('<td>' + node[dataProp] + '</td>');
		    }
		}
		row.prepend(tdBadges);
		row.append('<td></td>');
		tbody.append(row);	
		count++;
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	createPagination(actualPage, totalPages);
}

function captureEnterBandejas(e){
	if(e.which == 13) {
		e.preventDefault();
		$("#filtrosContent #search").click()
	}

}