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
    var cont=0;  
    for (var dataProp in dataSet[0]){
        // se verifica si no es una propiedad para generar una tabla anidada y la propiedad no es nula para generar el encabezado
    	if (dataProp != 'subitems' && dataSet[0][dataProp] != null){    
    		if(cont>0){
    		var titulo =""; 
    		var partes = dataProp.replace(/([A-Z])/g, ",$1").split(",");
    		for (var i = 0; i < partes.length; i++){
    			parte = partes[i];
    			titulo += parte.substring(0,1).toUpperCase() + parte.substring(1) + " ";
    		}
    		titulo = titulo.substring(0, titulo.length-1);
    		row.append('<td>' + titulo + '</td>');
    	   }
    		cont=cont+1;
    	}
    };
    row.append('<td></td>');
    rowHead.append(row);
    table.append(rowHead);
}
function createTableExpandibleAsync (dataSet, actualPage, totalPages){
	var table  = $('<table id="solicitudesTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeader(table, $(dataSet), false);
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
		    if(node[dataProp] != null) {
		    	// Se verifica si se trata de la primera columna para añadir la columna de los badges
    			if(col == 1) {
    				col = 0;
    			    row.append(tdBadges);
    			}
    			else{	
    				row.append('<td>' + node[dataProp] + '</td>');
    				}
    			
		    }
		}

        row.prepend(tdBadges);
        row.append('<td><a style="cursor:pointer" id="actionTable2" title="Editar profesional" onclick="editarElemento(\''+numero+'\')"><i style="font-size: 16px;" class="icon-edit"></i></a></td>'); //New
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
	var table  = $('<table id="solicitudesTable" class="bandejaTable table table-condensed" style="border-collapse:collapse;"></table>');
	createHeader(table, $(dataSet), false);
	var tbody = $('<tbody></tbody>');
	var count = 0;
	$(dataSet).each(function(i, node){
	    // Se reinicia la cuenta de las solicitudes por estado
	    pendientes = 0;
	    devueltas = 0;
	    autorizadas = 0;
	    var tdBadges = $('<td></td>');
	    
	    var col = 1;
		count++;
		var row = $('<tr data-toggle="collapse" data-target="#test'+ count +'"	class="accordion-toggle"></tr>');
		var hiddenRow='';
		var cols = 0;
        for (var dataProp in node){
            cols = cols + 1;
        }
		for (var dataProp in node){
		    if(node[dataProp] != null) {
    			if (node[dataProp] instanceof Array){
    				var subTable = createSubTableExpandible(node[dataProp]);
    				hiddenRow ='<tr><td colspan="'+cols+'" class="hiddenRow"><div class="accordian-body collapse col-lg-12" id="test'+ count +'">' + subTable + '</div></td></tr>';
    			}
    			else{
    			    // Se verifica si se trata de la primera columna para añadir la columna de los badges
    			    if(col == 1) {
    			        col = 0;
    			        row.append(tdBadges);
    			    }
                    row.append('<td>' + node[dataProp] + '</td>');
    			}
		    }
		}

		// Se muestra un badge con la cantidad de items por fila en caso que tenga subtitems
		if (typeof node.subitems != 'undefined')
			tdBadges.append('<span class="badge badge-info" title="Items solicitud">'+node.subitems.length+'</span> ');
        row.prepend(tdBadges);
        
		row.append('<td></td>');
		tbody.append(row);	
		tbody.append(hiddenRow);
	});
	table.append(tbody);
	$(table);
	renderTo.html('');
	renderTo.append(table);
	createPagination(actualPage, totalPages);
}

function createPagination(actualPage, totalPages){
	var ul = $('<ul class="pagination pagination-sm"></ul>');
	
	// Se agrega el elemento para llegar a la primera página
    var a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="actualizarElementos('+(1)+')"')+'>&laquo;&laquo;</a>');
    var li = $('<li class="'+(actualPage==1?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se agrega el elemento para ir a la página anterior
	a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="actualizarElementos('+(actualPage - 1)+')"')+'>&laquo;</a>');
	li = $('<li class="'+(actualPage==1?'disabled':'')+'"></li>');
	li.append(a);
	ul.append(li);
	
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
			ul.append($('<li><a style="cursor:pointer" onclick="actualizarElementos('+(i)+')">' + i + '</a></li>'));
	}
	
	// Se agrega el elemento para ir a la página siguiente
    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="actualizarElementos('+(actualPage + 1)+')"')+'">&raquo;</a>');
    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se agrega el elemento para ir a la última página
    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="actualizarElementos('+(totalPages)+')"')+'">&raquo;&raquo;</a>');
    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se utiliza para centrar el paginador
	var div = $('<div align="center"></div>');
	div.append(ul);
	renderTo.append(div);
	$('#actualPage').val(actualPage);
}

