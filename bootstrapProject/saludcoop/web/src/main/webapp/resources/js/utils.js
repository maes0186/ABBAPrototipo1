var functionLogger = {};
var readonlySelectValue= "";
function configABMComponents() {

//	//**************************Set up your functionLogger*****************//
//	
//
//	functionLogger.log = true;//Set this to false to disable logging 
//
//	/**
//	 * Gets a function that when called will log information about itself if logging is turned on.
//	 *
//	 * @param func The function to add logging to.
//	 * @param name The name of the function.
//	 *
//	 * @return A function that will perform logging and then call the function. 
//	 */
//	functionLogger.getLoggableFunction = function(func, name) {
//	    return function() {
//	        if (functionLogger.log) {
//	            var logText = name + '(';
//
//	            for (var i = 0; i < arguments.length; i++) {
//	                if (i > 0) {
//	                    logText += ', ';
//	                }
//	                logText += arguments[i];
//	            }
//	            logText += ');';
//
//	            console.log(logText);
//	        }
//
//	        func.apply(this, arguments);
//	    }
//	};
//
//	/**
//	 * After this is called, all direct children of the provided namespace object that are 
//	 * functions will log their name as well as the values of the parameters passed in.
//	 *
//	 * @param namespaceObject The object whose child functions you'd like to add logging to.
//	 */
//	functionLogger.addLoggingToNamespace = function(namespaceObject){
//	    for(var name in namespaceObject){
//	        var potentialFunction = namespaceObject[name];
//	        
//	        if(Object.prototype.toString.call(potentialFunction) === '[object Function]'){
//	            namespaceObject[name] = functionLogger.getLoggableFunction(potentialFunction, name);
//	        }
//	    }
//	};    

	
	
	$(".abmComponent.withSearch").each(
			function(i, e) {
				
//				functionLogger.addLoggingToNamespace(e);     
				
				if ($(this).hasClass("uniqueResult")) {
					e.addRow = fillSearchForm;
					e.onSuccess = function() {
						return true;
					};
				} else {
					e.addRow = addRow;
					e.onSuccess = function() {
						$(this).find(".searchForm .form-control").val('');
						return true;
					};
				}
				
				if ($(this).hasClass("pageable"))
					e.actualPage = 1;
				
				e.jObject = $(e).attr("javaObject");
				e.currentIndex = 0;
				e.dataObj = {};
				e.techFields = [];
				e.modalSearch = $("#ajax-modal").clone().attr("id",
						"search-" + e.jObject);
				$(e.modalSearch).find("h3")
						.html(getHeader(e));

				addSearchForm(e);

				e.disable = function (){ $(this).find(".form-control").attr("readonly",true); $(this).find("button").prop("disabled",true);};
				e.enable = function (){ $(this).find(".form-control").attr("readonly",false);$(this).find("button").prop("disabled",false);};
				
				if($(e).attr("dependentForm") != undefined){
					e.dependentForm = $("#"+$(e).attr("dependentForm"))[0];
					createAlertMessage($("#" + $(e).attr("id") + " .messages"), 
							{type:'info', 
							 message:'Complete primero el formulario ' + $("#"+$(e).attr("dependentForm")+ " strong").html(), 
							 autoShow:true, 
							 dismissable:true});
					e.disable();
				}
					
				e.onClean = function(){return true;};
				e.setOnClean = function(funct){e.onClean = funct;};
				e.clean = function() {
					e.enable();
					$(e).find(".form-control, .searchForm [type=hidden]").each(function(i,x){
						try{
							$(x).reset();
							if($(x).is("[reload='true']")){
								$(x).change();
							}
						}
						catch(ex){
							$(x).val("");
						}	
					});
					
					$(".abmComponent[dependentForm='" + $(e).attr("id") + "']").each(function(i,x){x.clean();x.disable();});
					
						
					e.onClean();
					return true;
				};

				e.setClean = function(funct) {
					e.onClean = funct;
				};


				e.onError = function() {
					return true;
				};

				e.setOnError = function(funct) {
					e.onError = funct;
				};

				e.setOnSuccess = function(funct) {
					e.onSuccess = funct;
				};

				e.callAddHandler = function() {
				};

				e.callRemoveHandler = function() {
				};
				e.checkBeforeAdd = verificarRepetidosPorId;

				e.checkBeforeRemove = function() {
					return true;
				};
				e.checkAfterAdd = function() {
					return true;
				};
				e.checkAfterRemove = function() {
					return true;
				};

				e.setAddHandler = function(funct) {
					e.callAddHandler = funct;
				};

				e.setRemoveHandler = function(funct) {
					e.callRemoveHandler = funct;
				};

				e.setCheckBeforeAdd = function(funct) {
					e.checkBeforeAdd = funct;
				};
				e.setCheckBeforeRemove = function(funct) {
					e.checkBeforeRemove = funct;
				};
				e.setCheckAfterAdd = function(funct) {
					e.checkAfterAdd = funct;
				};
				e.setCheckAfterRemove = function(funct) {
					e.checkAfterRemove = funct;
				};
				e.setValidateBeforeSearch = function(funct){
					e.validateBeforeSearch = funct;
				};
				e.validateBeforeSearch = validateBeforeSearch;
				
				$(e).find("th.data").each(function(i, thData) {
					e.dataObj[$(thData).attr("name")] = "";
				});
				$(e).setInit = function(funct) {
					e.init = funct;
				};

				$(e).find("th.editable").each(function(i, thData) {
					e.techFields[i] = {
						type : $(thData).attr("role"),
						name : $(thData).attr("name"),
						description : $(thData).html(),
						valueFrom : $(thData).attr("valueFrom"),
						readonly : $(thData).attr("readonly"),
                        digitsonly : $(thData).attr("digitsonly"),
						value: $(thData).attr("value")
						
					};
				});
				$(e).find(".combo-search").each(function(t, x) {
					x.reset = function() {
						$(this).val("");
						$(x).prop("disabled", false);
						$(x).prop("readonly", false);
					};
				});
				$(e).find(".input-search").each(function(t, x) {
					x.reset = function() {
						$(this).val("");
						$(x).prop("disabled", false);
						$(x).prop("readonly", false);
					};
				});
			});
	$(".abmComponent thead").hide();
	
	/*Permite configurar como readonly los select bloqueados, evitando que se cambie el valor y la necesidad de ponerlo como disabled*/
	$("select").bind("click",function(event){if ($(this).is("[readonly]")){readonlySelectValue=$(this).val();}});
	$("select").bind("change",function(event){if ($(this).is("[readonly]")){$(this).val(readonlySelectValue);}});
	
	
	 $("body").find(".control-label-sm").each(function() {
        $(this).css("padding-top", calcularPaddingTop($(this).height(), $(this).css("padding-top")));
    });
}

function enableDependants(form){
	$(".abmComponent[dependentForm='"+$(form).attr("id")+"']").each(function(i,e){e.enable();});
}
function getHeader(form){
	var header = $(form).find("blockquote>strong").html();
	if(header== undefined || header ==""){
		if($(form).is("[searchheader]")){
		header = $(form).attr("searchheader");
		}else{
			header = "Busqueda";
		}
	}
	return header;
}
function fillSearchForm(dataObj, additionalFields) {
	
	if (!this.checkBeforeAdd(dataObj, additionalFields, this)) {
		return false;
	}
	$(this).data("data", dataObj);
	var component = null;
	for ( var props in dataObj) {
		if ($(this).find('#' + props) != undefined) {
			component = $(this).find('#' + props);
			component.val(dataObj[props]);
			if($(component).attr('reload') == 'true' && $(component).prop('tagName').toUpperCase() == 'SELECT'){
				$(component).change();
			}
		}
	}
	$(this).find(".searchForm .form-control").attr("readonly",true);
	$(this).find("button[role='buscar']").attr('disabled', true);
	if(this.checkAfterAdd(dataObj, additionalFields, this)){
		this.callAddHandler(dataObj);
		
		enableDependants(this);
	};
	
	
}

function verificarRepetidosPorId(dataObj, additionalFields, context) {
	return $(context).find(
			".input-flat[id=id][value='" + dataObj.id + "']").size() == 0;
}

function addRow(dataObj, additionalFields) {
	
	if (!this.checkBeforeAdd(dataObj, additionalFields, this)) {
		return false;
	}
	var form = this;
	var trow = $("<tr class='row'></tr>");
	$(this).find("thead th.data").each(function(i,e){
		
	
		if (dataObj[$(e).attr("name")] != undefined) {
			var td = $("<td class='data' style='vertical-align: middle'></td>");
			td.append(createComponent({
				name : $(e).attr("name"),
				type : $(e).attr("role") ? $(e).attr("role") : 'input',
				value : dataObj[$(e).attr("name")]
			}, form).addClass("input-flat").attr("readonly",true));
			trow.append(td);
		}
	});

	for ( var index in additionalFields) {
		var td = $("<td class='data'></td>");
		td.append(createComponent(additionalFields[index], this));
		trow.append(td);

	}

	$(trow).data("data", dataObj);
	$(trow).data("index", this.currentIndex);
	this.callAddHandler(dataObj, trow);

	if (this.checkAfterAdd(dataObj, additionalFields, this)) {
		$(this).find("tbody").append(trow);
		$(this).find("thead").show();
		this.currentIndex++;
		enableDependants(this);
	} else {
		return false;
	}

}

function handlerFormLoad() {
	$(this).removeClass("btn-warning").addClass("btn-success");

}

function removeRow(button, context) {

	var row = $(button).parent().parent();
	if (!context.checkBeforeRemove(row, context)) {
		return false;
	}

	context.callRemoveHandler($(row).data("data"), context);

	if (context.checkAfterRemove(row, context)) {
		$(row).remove();

//		if (context.currentIndex <= 0) {
//			$(context).find("thead").hide();
//		}
		
		if (jQuery(context).find("tbody tr").length == 0) {
			$(context).find("thead").hide();
			if(this.dependentForm != undefined){
				this.dependentForm.disable();	
			}
		}
	} else {
		return false;
	}
}

function createComponent(field, context) {
	switch (field.type) {
	case 'input-search':
		return $("<input placeholder='" + field.description
				+ "' class='form-control input-sm input-search' id='"
				+ field.name + "' name='" + field.name + "'/>").keypress(captureEnter);
	case 'combo-search':
		var combo = $("#" + field.valueFrom).clone().addClass(
				"form-control input-sm combo-search").attr("id", field.name)
				.show();
		if(field.isReloaded){
			$(combo).attr('reload', true);
		}
		$(combo).attr('name', field.name);
		return combo;
    case 'checkbox-search':
      var check = $("<div onclick='toggleCheckbox(this)' name='"
              + field.name
              + "' class='btn btn-sm " + (field.checked == true ? "btn-success" : "btn-danger") + "  btn-link btn-toggle text-center'>"
              + "<input type='checkbox'" 
              + "  name='"+ field.name
              + "' style='display:none' class='form-control'/>"
              + "<i class='icon-unchecked'></i></div>"); 
        return check;        
	case 'input':
		var value = "";
		if (field.value != undefined) {
			value = field.value;
		}
		return $("<input placeholder='" + field.description
				+ "' class='form-control input-sm' id='" + field.name
				+ "' name='" + context.jObject + "[" + context.currentIndex
                + "]." + field.name + "' value='" + value + "' " + field.readonly + " />").keypress(captureEnter);
		if(field.digitsonly) {
		    input.keypress(allowDigitsOnly);
		}
	case 'input-number':
		var value = "";
		if (field.value != undefined) {
			value = field.value;
		}
		return $("<input placeholder='" + field.description
				+ "' class='form-control input-sm' id='" + field.name
				+ "' name='" + context.jObject + "[" + context.currentIndex
				+ "]." + field.name + "' value='" + value + "'/>").bind("keypress",captureEnter).bind("keypress",allowOnlyDigits);
	case 'buttonEliminar':
		var button = $("<button class='btn btn-danger btn-sm' type='button'></button>");
		$(button).append("<i class='icon-remove'></i>");
		$(button).bind("click", function() {
			removeRow(this, context);
		});
		return button;
	case 'buttonAddFormulario':
		var button = $("<button class='btn btn-sm' rel='tooltip' type='button'></button>");
		$(button).append("<i class='icon-file-text-alt'></i>");
		$(button).attr("title", field.description);
		$(button).tooltip({
			placement : 'left'
		});
		$(button).addClass("btn-warning");
		$(button).bind("click", field.onClick);
		return button;
	case 'inputFile':
		var button = $("<button class='btn btn-sm' rel='tooltip' type='button'></button>");
		$(button).append("<i class='icon-paperclip'></i>");
		var input = $("<input style='display:none' type='file' class='form-control input-sm' id='"
				+ field.name
				+ "' name='"
				+ context.jObject
				+ "["
				+ context.currentIndex + "]." + field.name + "'/>");
		$(button).bind("click", function() {
			$(input).click();
		});
		$(button).tooltip({
			placement : 'left'
		});
		$(button).addClass("btn-warning");
		$(input).change(function() {
			if ($(this).val() != "") {
				$(button).removeClass("btn-warning").addClass("btn-success");
			} else {
				$(button).removeClass("btn-success").addClass("btn-warning");
			}
		});
		var div = $("<div style='display:inline'></div>").append(input);
		$(div).append(button);
		return div;
	case 'radioButton':
		return $("<button type='button' onclick='toggleRadio(this)' "
				+ "class='btn btn-sm " + (field.checked == true ? "btn-success" : "btn-danger") + " btn-link btn-toggle text-center' "
				+ "data-group='" + context.jObject + "." + field.name + "'>"
				+ "<input type='radio' " + (field.checked == true ? "checked='checked'" : "") 
				+ "  name='" + context.jObject + "[" + context.currentIndex + "]." + field.name
				+ "' value='true' style='display:none'/>"
				+ "<i class='" + (field.checked == true ? "icon-check" : "icon-unchecked") + "'></i></button>");
    case 'checkbox':
//        return $("<input type='checkbox'" 
//                + "  name='" + context.jObject + "[" + context.currentIndex + "]." + field.name
//                + "' />");
        return $("<div onclick='toggleCheckbox(this)' "
                + "class='btn btn-sm " + (field.checked == true ? "btn-success" : "btn-danger") + " btn-link btn-toggle text-center'>"
                + "<input type='checkbox'" 
                + "  name='" + context.jObject + "[" + context.currentIndex + "]." + field.name
                + "' style='display:none' />"
                + "<i class='icon-unchecked'></i></div>");
        
	case 'combo':
		var combo = $("#" + field.valueFrom)
					.clone()
					.addClass("form-control input-sm")
					.attr("name", context.jObject + "[" + context.currentIndex + "]." + field.name)
					.show();
		$(combo).removeAttr("id");
		if (field.value) {
			$(combo).val(field.value);
		}
		return combo;
	case 'combo-sync':
		var combo = $("#" + field.valueFrom).clone().addClass(
				"form-control input-sm " + field.valueFrom).attr(
				"name",
				context.jObject + "[" + context.currentIndex + "]."
						+ field.name).show();
		if (field.value) {
			$(combo).val(field.value);
		}
		$(combo).removeAttr("id");
		return combo;
	case 'combo-row-data':
		var combo = $("<select></select>")
			.addClass("form-control input-sm")
			.attr("name", context.jObject + "[" + context.currentIndex + "]." + field.name)
			.show();
		return combo;
	case 'combo-dependent':
		var combo = $("#" + field.id)
			.clone()
			.addClass("form-control input-sm")
			.attr("name", context.jObject + "." + field.name)
			.show();
		$(combo).removeAttr("id");
		
		var comboSuperior = $("#"+field.dependentFrom);
		comboSuperior.bind("change", function(){
			$.get(utlAbsoluta+"/prestador/search/"+field.javaObject+"/"+comboSuperior.val(), 
					function(resultado){
						combo.remove("option");
						for (var x in resultado.content){
							combo.append("<option value='"+resultado.content[x].id+"'>"+resultado.content[x].descripcion+"</option>");
						}
					}
			);
		});
		
return combo;
	case 'modalCaller':
		var $button = $("<a data-toggle='modal' style='color:white' href='#"
				+ field.modalId
				+ "' title='"
				+ field.description
				+ "'><button onclick='setFormTargetContainer($(this));eval(\"fill"
				+ field.modalId
				+ "($(this))\")' class='btn btn-sm btn-warning' rel='tooltip' type='button' ><i class='icon-gear'></i></button></a>");
		var $div = $("<div style='display:inline'></div>");

		// Agrega los data usados en caso de que haya datos precargados
		if (field.objectData || field.serializedData) {
			$button.find("button").removeClass("btn-warning");
			$button.find("button").addClass("btn-success");				
			if (field.objectData) {
				$button.find("button").data(field.objectData);
			}
			if (field.serializedData) {
				$button.find("button").attr("data-form-prescr", field.serializedData);
			}
		}

		$div.append($button);
		return $div;
	case 'hidden':
		return $("<input type='hidden' id='"+ field.name +"' name='" + context.jObject + "["
				+ context.currentIndex + "]." + field.name + "' " + (field.value?"value='"+field.value+"' ":"") + " />");
	case 'hidden-search':
		var $input = $("<input type='hidden' name='" + field.name + "' id='"+ field.name +"'/>");
		if ($(context).is(".uniqueResult")) $input.attr("name",context.jObject+"."+$input.attr("name"));
		return $input;
	case 'searchForm':
		var $modal = $("#modal-template").clone().attr("id","prueba").attr("data-width","1000px");
		$fromForm = $("#"+field.valueFrom);
		if(($fromForm) == undefined){console.log("no se encuntra el form");}
		$modal.find("h3").html(field.description);
		$modal.find(".modal-body").append($fromForm.find(".searchForm > div:has(label)").clone());
		$modal.find(".modal-footer").append($fromForm.find("button").clone());
		return $modal;
		
	case 'date-range':
		var $dateRange = $("#date-range").clone().removeClass("template-hidden");
		$dateRange.addClass(field.class);
		$dateRange.find("[id]").each(function(i,e){$(e).attr("id",$(e).attr("id") + field.name);});
		
		if(field.nameLeft != undefined){
			$dateRange.find("#date-range-from-input"+field.name).attr("name", field.nameLeft);
				
		}else{
			field.nameLeft = $dateRange.find("#date-range-from-input"+field.name).attr("name");
		}
		
		if(field.valueLeft != undefined){
			$dateRange.find("#date-range-from-input"+field.name).val(field.valueLeft);
		}
		if(field.nameRight != undefined){
			$dateRange.find("#date-range-to-input"+field.name).attr("name", field.nameRight);	
		}else{
			field.nameRight = $dateRange.find("#date-range-to-input"+field.name).attr("name");
		}
		
		if(field.valueRight != undefined){
			$dateRange.find("#date-range-to-input"+field.name).val(field.valueRight);
		}
		
		var startDate ="" , endDate = "" ;
		$dateRange.find("#date-range-from"+field.name).datepicker()
	    .on('changeDate', function(ev){
	    	$(context).find(".messages").empty();
		        if (endDate != "" && endDate!=undefined && ev.date.valueOf() > endDate.valueOf()){
		        	appendErrorMessage($(context).find(".messages"),NOT_GREATER);
		            startDate = "";
		            $dateRange.find("#date-range-from-input"+field.name).val("");
		        } else {
		        	
		            startDate = new Date(ev.date);
		        }
		        $dateRange.find("#date-range-from" + field.name).datepicker('hide');
		    });
		$dateRange.find("#date-range-to"+field.name).datepicker()
		    .on('changeDate', function(ev){
		    	$(context).find(".messages").empty();
		        if (startDate != "" && startDate!=undefined && ev.date.valueOf() < startDate.valueOf()){
		        	appendErrorMessage($(context).find(".messages"),NOT_LESS);
		            endDate = "";
		            $dateRange.find("#date-range-to-input"+field.name).val("");
		        } else {
		        	
		            endDate = new Date(ev.date);
	        }
		        $dateRange.find("#date-range-to" + field.name).datepicker('hide');
		    });
		return $dateRange;

	case 'identificacion':
		var $identificacion = $("#identificacionComponent").clone().removeClass("template-hidden").removeAttr("id");
		$identificacion.find("label").each(function(i,e){
											if (i==0){
												$(e).attr("for",$(e).attr("for")+field.nameLeft);
											} else {
												$(e).attr("for",$(e).attr("for")+field.nameRight);
											}});
		$identificacion.find(".form-control").each(	function(i,e){
														if (i==0){
															$(e).attr("id",$(e).attr("id")+field.nameLeft);
															$(e).change(changeTipoIdentificacion);
														} else {
															$(e).attr("id",$(e).attr("id")+field.nameRight);
															$(e).bind("keypress",keypressNumeroIdentificacion);
															$(e).bind("keypress",captureEnter);
														}
														$(e).attr("name",$(e).attr("id"));
													});
		
		if(field.tipoDatos != undefined && field.tipoDatos == 'ips'){
		   $identificacion.find("option[es-ips=false]").remove();
		}
		
		if(field.tipoDatos != undefined && field.tipoDatos == 'afiliado'){
	       $identificacion.find("option[es-afiliado=false]").remove();
	    }
		
		if(field.tipoDatos != undefined && field.tipoDatos == 'profesional'){
	       $identificacion.find("option[es-profesional=false]").remove();
	    }
		
		return $identificacion;
		
	case 'fechaInicio':
		var $fechaInicio = $(
		
		'<div id="fechaInicioDiv" class="input-group date"   data-date-format="dd-mm-yyyy" data-date="">'+
		'<input type="text" name="" class="form-control input-sm cursor-default" id="" readonly/>'+
		'<span class="input-group-addon input-sm"><i class="icon-calendar"></i></span>'+
	'</div>'
		
		).addClass(field.class);
		$fechaInicio.find("input").attr("id", field.name).attr("name", context.jObject + "[" + context.currentIndex + "]." + field.name);
		
		$fechaInicio
	    .datepicker()
	    .on('changeDate', function(ev){
	        if ($(this).parents("tr").find("fechaFinDiv").data("date") != "" && 
	        		$(this).parents("tr").find("fechaFinDiv").data("date")!=undefined && 
	        		ev.date.valueOf() > $(this).parents("tr").find("fechaFinDiv").data("date").valueOf()){
	        	
	        	appendErrorMessage($(context).find(".messages"),NOT_GREATER);
	        	$(this).data("date","");
	        	$(this).find("input").val("");
	        } else {
	        	$(context).find(".messages").empty();
	            $(this).data("date",new Date(ev.date));
	        }
	        $fechaInicio.datepicker('hide');
	    });
		
		return $fechaInicio;
	case 'fechaFin':
		var $fechaFin = $(
				
				'<div id="fechaFinDiv" class="input-group date"   data-date-format="dd-mm-yyyy" data-date="">'+
				'<input type="text" name="" class="form-control input-sm cursor-default" id="" readonly/>'+
				'<span class="input-group-addon input-sm"><i class="icon-calendar"></i></span>'+
			'</div>'
				
				).addClass(field.class);
		
		$fechaFin.find("input").attr("id", field.name).attr("name", context.jObject + "[" + context.currentIndex + "]." + field.name);
		
		$fechaFin
	    .datepicker()
	    .on('changeDate', function(ev){
	        if ($(this).parents("tr").find("fechaInicioDiv").data("date") != "" && 
	        		$(this).parents("tr").find("fechaInicioDiv").data("date")!=undefined && 
	        		ev.date.valueOf() < $(this).parents("tr").find("fechaInicioDiv").data("date").valueOf()){
	        	
	        	appendErrorMessage($(context).find(".messages"),NOT_LESS);
	        	$(this).data("date","");
	            $(this).find("input").val("");
	        } else {
	        	$(context).find(".messages").empty();
	        	$(this).data("date",new Date(ev.date));
	        }
	        $fechaFin.datepicker('hide');
	    });
		
		return $fechaFin;
	}
}

function fillSearchResult(e, resultado) {
	
	var row;
	$(e.modalSearch).find(".modal-body table").remove();
	$(e.modalSearch).find(".modal-body span").remove();
	if (resultado.length >= 1) {
        var divTable = $("<div></div>");
		var table = $("<table class='table table-hover'><thead><tr id='headColumnName'></tr></thead><tbody></tbody><tfoot><tr id='footInputFinder'></tr></tfoot></table>");
        divTable.append(table);
        $(e).find("th.data").each(function(index, element){
        	if(!$(element).is("[role='hidden']")){
        		table.find("#footInputFinder").append(
        				"<th rowspan='1' colspan='1'>" +
        					"<input type='text' name='"+$(element).attr('name')+"' value='' class='search_init form-control input-sm' placeholder='Filtrar por "+$(element).text()+"' />"+
        				"</th>");
        	}else{
        		table.find("#footInputFinder").append(
        				"<th class='template-hidden' role='hidden' thclass='template-hidden' tdclass='template-hidden' >" +
        					"<input type='hidden' name='"+$(element).attr('name')+"'/>"+
        				"</th>");
        	}
        });
        
		table.find("#headColumnName").append($(e).find("th.data").clone());
		table.find(".data[thclass]").each(function(i, e) {
		    $(e).addClass($(e).attr("thclass"));
		});
		var arrayClassTD = [];
		table.find("th[tdclass]").each(function(index, element){
			arrayClassTD[index] = $(element).attr("tdclass");
		});
		var attrs = [];
        for (var prop in e.dataObj) {
            attrs.push({"mDataProp" : prop});
        }
		table.find("th").css("padding-left", "5px");
        $(e.modalSearch).attr("data-width", "75%");
        try {
            $.fn.dataTableExt.sErrMode = 'throw';
    	    table.prop("id", "tblSearchDiag");
    	    var diagTable = table.dataTable({
                "sPaginationType" : "full_numbers",
    	        "aaData": resultado,
    	        "bPaginate" : true,
                "bProcessing" : true,
                "bServerSide" : false,
                "bInfo" : false, // Informacion Pagina Y de X
                "bSort" : true, // Ordenar columnas
                "bLengthChange" : false, // Cantidad de Paginas
                "iDisplayLength" : 9, // # de registros por pagina
                "bFilter" : true, // Boton Search
                "bAutoWidth" : true, // Ancho Automatico de Columnas
    	        "aoColumns": attrs,
                "oLanguage" : {
                    "oPaginate" : {
                        "sFirst" : "««",
                        "sPrevious" : "«",
                        "sNext" : "»",
                        "sLast" : "»»"
                    }
                },
                "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                    row = $(nRow);
                    row.children().each(function(indice, elemento){
                    	$(elemento).addClass(arrayClassTD[indice]);
                    });
                    row.css("cursor", "pointer");
                    row.data("data", aData);
                    row.click(function() {
                        e.addRow(aData, e.techFields);
                        e.modalSearch.modal('hide');
                    });
                }
    	    });
    	    $(divTable).find("[id$=_filter]").hide();
    	    $(divTable).find(":input[name]").keyup(function() {
                diagTable.fnFilter(this.value, $(divTable).find(":input[name]").index(this));
            });
            table.removeClass("table-striped table-bordered");
        } catch(error) {
            log(error);
        }

		if (resultado.length == 1) {
            e.addRow(resultado[0], e.techFields);
            e.modalSearch.modal('hide');
			 
		} else {
            $(e.modalSearch).find(".modal-body").html("");
			$(e.modalSearch).find(".modal-body").append(divTable);
			e.modalSearch.modal();
		}

	} else {
		$(e.modalSearch).find(".modal-body").append(
				"<span>No se encontraron datos</span>");
		e.modalSearch.modal();
	}

    $("body").find(".control-label-sm").each(function() {
        $(this).css("padding-top", calcularPaddingTop($(this).height(), $(this).css("padding-top")));
    });
}

function addSearchForm(form) {
    var cambiaClass = false;
    var formData = [];
	if ($(form).find(".searchForm input").size() == 0) {
		formData = [ {
			name : 'codigo',
			row : 1,
			type : 'input-search',
			description : 'Código',
			class : "col-lg-2"
		}, {
			name : 'descripcion',
			row : 1,
			type : 'input-search',
			description : 'Descripción',
			class : "col-lg-2"
		}];
	} else {

		$(form).find(".searchForm input").each(
				function(i, e) {
					
					formData[i] = {};
					
					if ($(this).attr("role") != undefined ) {
    					formData[i].type = $(this).attr("role");
    				} else {
    					formData[i].type = "input-search";
    				}
    					
    				if( $(this).attr("type") != undefined &&  $(this).attr("type") == "checkbox-search"){
    				    formData[i].type = $(this).attr("type");
    				    cambiaClass = true;
    				}
    				
    				formData[i].description = $(this).attr("placeholder");
    				formData[i].value = $(this).val();
    				formData[i].id =  $(this).attr("id");
    				if($(this).attr("name")!=undefined){
    					formData[i].name =  $(this).attr("name");	
    				}else{
    					formData[i].name =  $(this).attr("id");
    				}
    				if($(this).attr("avoidDescriptionLabel")!=undefined){
    					formData[i].avoidDescriptionLabel =  true;	
    				}else{
    					formData[i].avoidDescriptionLabel = false;
    				}
    					
    				formData[i].isReloaded = $(this).attr("reload");	
    				formData[i].valueFrom = $(this).attr("valueFrom");
    				formData[i].valueLeft = $(this).attr("valueLeft");
    				formData[i].valueRight =  $(this).attr("valueRight");
    				formData[i].nameLeft =  $(this).attr("nameLeft");
    				formData[i].nameRight =  $(this).attr("nameRight");
    				formData[i].tipoDatos =  $(this).attr("tipoDatos");
    				formData[i].class = $(this).attr("class").replace(
    						'template-hidden', '');
    				if ($(this).attr("row") != undefined) {
    					formData[i].row = $(this).attr("row");
    				} else {
    					formData[i].row = 1;
    				}
                    formData[i].labelClass = $(this).attr("labelclass");
				});		
		$(form).find(".searchForm input").remove();

	}

	var formElement = $(form).find(".searchForm");
	formElement
			.prepend($("<div class='messages col-lg-12 col-sm-12 col-xs-12'></div>"));

	var divRow = null;
	var currentRow = 0;
	for ( var index in formData) {

		if (currentRow != formData[index].row) {
			currentRow = formData[index].row;
			if (divRow != null) {
				formElement.append(divRow);
			}
			divRow = $("<div class='row col-lg-12 form-group form-group-sm'></div>");

		}
		if (!formData[index].avoidDescriptionLabel){
			divRow
				.append($("<label class='text-right control-label control-label-sm " + (formData[index].labelClass != null ? formData[index].labelClass : "col-lg-2" ) + "' for='"
					+ formData[index].name
					+ "'>"
					+ formData[index].description + " </label>"));
		}

		divRow
			.append($("<div class='" + formData[index].class + "'></div>")
			.append(createComponent(formData[index], form)));
	}
	if (divRow.children().size() > 0) {
		formElement.append(divRow);
	}
	if ($(form).hasClass('uniqueResult')) {
		formElement.append('<div class=" col-lg-12 text-right"> '
						+ '<button type="button" role="buscar" class="btn btn-info btn-sm"> '
						+ ' Buscar <i class="icon-search"></i></button> '
						+ '<button type="button" role="limpiar" class="btn btn-warning btn-sm"> '
						+ ' Limpiar <i class="icon-eraser"></i></button> '
						+ '</div>');
	} else {
		if(divRow.children().size() != 4){
			formElement.append((cambiaClass?'<div class="col-lg-11 text-right" style="padding: 19px;">': ' <div class="col-lg-offset-9 col-lg-2"> ')
					+ '<button type="button" role="buscar" id="buscar" class="btn btn-info btn-sm"> '
					+ ' Buscar <i class="icon-search"></i> </button> '
					+ '</div> ');
		}else{
			if($(formElement).find("div.row").length == 1){
				$(formElement).find("div.row").append('<div class="col-lg-2"> '
						+ '<button type="button" role="buscar" id="buscar" class="btn btn-info btn-sm"> '
						+ ' Buscar <i class="icon-search"></i> </button> '
						+ '</div> ');
			}else{
				formElement.append('<div class="col-lg-11 text-right"> '
						+ '<button type="button" role="buscar" id="buscar" class="btn btn-info btn-sm"> '
						+ ' Buscar <i class="icon-search"></i> </button> '
						+ '</div> ');
			}			
		}
	}
	$(formElement).find("button[role='buscar']").bind(
			"click",
			function() {
				
				
				formElement.find(".messages").empty();
				 
				if (form.validateBeforeSearch()){
					
					var searchForm = {};
					
					formElement.find(".form-control").each(function(i,e){
					    if($(e).is(":checkbox")){
					        searchForm[e.name] = $(e).is(':checked');
					    }else{
					        searchForm[e.name] = $(e).val();
					    }
					});

					 
					$.ajax({
						url : urlAbsoluta+"/prestador/search/" + form.jObject,
						data : searchForm,
						dataType : "json",
						type : "POST",
						async : true,
						success : function(resultado) {
							if (resultado.generalErrors.length > 0) {
								createErrorMessages(formElement.find(".messages"),
										resultado.generalErrors);
								 
							} else {
								fillSearchResult(form, resultado.content);
								$(form).find(".form-control").each(function(i,e){
									searchForm[e.name] = $(e).val();
									});
								
						
							}

							form.onSuccess();
							
						},
						error : function(jqXHR, textStatus, errorThrown) {
							 
							form.onError();
							 
						},

						dataType : "json"
					});

					
				}else{
					return false;
				}
					
					
			
			});

	$(formElement).find("button[role='limpiar']").bind("click", function(event) {
		event.preventDefault();
		form.clean();
	});

}

function toggleRadio(radio) {

	var previousSelected = $(radio).parent().parent().parent().find(
			"button.btn-success[data-group='" + $(radio).attr("data-group")
					+ "']");
	$(previousSelected).removeClass("btn-success").addClass("btn-danger");
	$(previousSelected).find("i").removeClass("icon-check").addClass(
			"icon-unchecked");
	$(previousSelected).find("input[type=radio]").prop("checked", false);

	$(radio).find("input[type=radio]").prop("checked", true);
	$(radio).find("i").removeClass("icon-unchecked").addClass("icon-check");
	$(radio).removeClass("btn-danger").addClass("btn-success");
}

function toggleCheckbox(checkboxBtn) {

    var check = $(checkboxBtn).find("input[type=checkbox]");
    if($(check).is(":checked")) {
        $(check).prop("checked", false);
        $(checkboxBtn).find("i").removeClass("icon-check").addClass("icon-unchecked");
        $(checkboxBtn).removeClass("btn-success").addClass("btn-danger");
    } else {
        $(check).prop("checked", true);
        $(checkboxBtn).find("i").removeClass("icon-unchecked").addClass("icon-check");
        $(checkboxBtn).removeClass("btn-danger").addClass("btn-success");
    }
}

/**
 * Selecciona por defecto el valor indicado por parámetro al campo de tipo select indicado por el id
 * @param idSelect
 *          Id del componente al que se le quiere seleccionar el valor por defecto
 * @param value
 *          Valor que se requiere seleccionar por defecto
 */
function selectDefault(idSelect, value) {
    if(idSelect != null && value != null) {
        $("#" + idSelect +" option").each(function(){
            if(value == $(this).val()) {
                $(this).prop("selected", "selected");
                return;
            }
         });
    }
}

/**
 * Recibe elementos del tipo par name-value, teniendo el name la estructura namespace[index].elementName
 * Acumula en un mapa de forma map[namespace1][index]{elementName1:value1, elementName2:value2}
 * 
 * @param element Elemento a agregar
 * @param map Mapa al cual agregar el elemento
 */
function agregaAMapa(element, map) {
	var namespace = element.name.split("[")[0];
	var index = element.name.split("[")[1].split("]")[0];
	var elementName = element.name.split("].")[1];
	var object = {};
	object[elementName] = element.value;
	map[namespace] = map[namespace] || [];
	map[namespace][index] = $.extend(map[namespace][index], object);
}

/**
 * Dado el ID de un formulario, cambia el value de los input, textarea y select en "" y quita la marca de selección
 * en lo que esté seleccionado.
 * @param idForm ID del forumulario a limpiar. 
 */
function genericResetFields(idForm) {
	$("#" + idForm + " input:not([type=radio]), #" + idForm + " textarea, #" + idForm + " select").val("");
	$("#" + idForm + " :checked").attr("checked", false);
}

function resetValidateForm(idForm){
	$("#" + idForm).validate().resetForm();
	$("#" + idForm + ".error").removeClass("error");
}
/**
 * Genera un objeto similar al que devuelve $.serializeArray partiendo de un JSON plano. 
 * @param object Objeto a transformar
 * @param [scrapNull] Bandera opcional que indica si se descartan los valores nulos ('', null, undefined)
 * @returns {o} Objeto resultante.
 */
function obtieneObjetoNameValue(object, scrapNull) {
    var o = {};
    var i = 0;
    for (attribute in object) {
        if (!scrapNull || object[attribute]) {
            o[i++] = {"name" : attribute, "value" : object[attribute]};
        }
    }
    return o;
}

/**
 * Genera una lista de objetos nombre-valor partiendo de un JSON plano con 1 nivel de profundidad. 
 * @param object Objeto a transformar
 * @param [scrapNull] Bandera opcional que indica si se descartan los valores nulos ('', null, undefined)
 * @returns {list} Lista resultante.
 */
function obtieneListaNameValue(object, scrapNull) {
    var list = [];
    for (var attribute in object) {
        if (!scrapNull || object[attribute]) {
            
            if ($.isPlainObject(object[attribute])) {
                var subObject = object[attribute];
                for (var subAttr in subObject) {
                    list.push({"name" : attribute + "." + subAttr, "value" : subObject[subAttr]});
                    }
            } else if ($.isArray(object[attribute])) {
                var objectList = object[attribute];
                for (var element in objectList) {
                    var i = 0;
                    var subObject = objectList[element];
                    for (var subAttr in subObject) {
                        list.push({"name" : attribute + "[" + i + "]." + subAttr, "value" : subObject[subAttr]});
                    }
                    i++;
                }
                
            } else {
                list.push({"name" : attribute, "value" : object[attribute]});
            }
        }
    }
    return list;
}

/**
 * Captura las teclas presionadas en los campos de busqueda, si es Enter, 
 * genera el evento click en el boton de búsqueda
 */
function captureEnter(e) {
	if(e.which == 13) {
		e.preventDefault();
		$(this).parents(".searchForm").find("[role='buscar']").click();
	}
}
/**
 * Validador por defecto que chequea que se haya completado al menos un campo de los filtros
 */
function validateBeforeSearch(form){
	validateMe = form || this;
	$(validateMe).find(".messages").empty();
	var todosVacios = true;
	$.each($(validateMe).find(".searchForm .form-control"), function(i,e){
		if ($(e).val() != "" && $(e).prop("type") != "select-one"){
			todosVacios=false;
		}
	});
	if (todosVacios){
		appendErrorMessage($(validateMe).find(".messages"), "Debe diligenciar al menos un campo de texto para ejecutar la búsqueda.");
	}
	return !todosVacios;
}

function createPagination3p(renderTo, actualPage, totalPages){
	var ul = $('<ul class="pagination pagination-sm"></ul>');
	
	// Se agrega el elemento para llegar a la primera página
    var a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="console.log('+(1)+')"')+'>&laquo;&laquo;</a>');
    var li = $('<li class="'+(actualPage==1?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se agrega el elemento para ir a la página anterior
	a = $('<a style="'+(actualPage==1?'':'cursor:pointer')+'" '+(actualPage==1?'':'onclick="console.log('+(actualPage - 1)+')"')+'>&laquo;</a>');
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
			ul.append($('<li><a style="cursor:pointer" onclick="console.log('+(i)+')">' + i + '</a></li>'));
	}
	
	// Se agrega el elemento para ir a la página siguiente
    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="console.log('+(actualPage + 1)+')"')+'">&raquo;</a>');
    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se agrega el elemento para ir a la última página
    a = $('<a style="'+(actualPage==totalPages?'':'cursor:pointer')+'" '+(actualPage==totalPages?'':'onclick="console.log('+(totalPages)+')"')+'">&raquo;&raquo;</a>');
    li = $('<li class="'+(actualPage==totalPages?'disabled':'')+'"></li>');
    li.append(a);
    ul.append(li);
    
    // Se utiliza para centrar el paginador
	var div = $('<div align="center"></div>');
	div.append(ul);
	renderTo.append(div);
	$('#actualPage').val(actualPage);
}

function calcularPaddingTop(alto, paddingAct) {
    if(alto < 33) {
        return "5px";
    } else {
        return "0px";
    }
}
function allowOnlyDigits(evt) {
	var theEvent = evt || window.event;
  	var key = theEvent.keyCode || theEvent.which;
  	key = String.fromCharCode( key );
  	var regex = /[0-9]|\./;
  	if( !regex.test(key) ) {
    	theEvent.returnValue = false;
    	if(theEvent.preventDefault) theEvent.preventDefault();
  	}
}
function allowDigitsOnly(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]/;
    if( !regex.test(key) && evt.keyCode != 8 && evt.keyCode != 9 && evt.keyCode != 46
            && evt.keyCode != 37 && evt.keyCode != 39) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}

function setDigitsOnly() {
    $(".digits-only").each(function() {
        $(this).keypress(allowDigitsOnly);
    });
}

function reloadMunicipiosByDepartamento(departamentoSelect, municipioSelect) {
	selectDefault(municipioSelect.attr("id"), '');
	var stateChildCombo = $(municipioSelect).attr("disabled");
	$(municipioSelect).attr("disabled", true);
	var options = '<option value=\"\">Seleccione...</option>';
	if (departamentoSelect.val() != "") {
		$.ajax({
            url: urlAbsoluta + "/prestador/search/ciudadesByDept",
            async: false,
            type: "POST",
            data: {
            	departamentoId: departamentoSelect.val()
            },
            datatype: "json",
            success: function (data) {
                 for (i in data.content) {
                   options += '<option value="' + data.content[i].id + '">' + data.content[i].descripcion + '</option>';
                 }
                 municipioSelect.html(options);
            }
        });
	} else {
		municipioSelect.html(options);
	}
	$(municipioSelect).attr("disabled", (stateChildCombo  != undefined) ? true : false);
}
$(document).ready(function() {
    setDigitsOnly();
});