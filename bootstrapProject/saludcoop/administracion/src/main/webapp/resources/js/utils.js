function configABMComponents() {
console.log("hola");
	$(".abmComponent.withSearch").each(
			function(i, e) {
				e.addRow = addRow;
				e.jObject = $(e).attr("javaObject");
				e.currentIndex = 0;
				e.dataObj = {};
				e.techFields = [];
				e.modalSearch = $("#ajax-modal").clone().attr("id",
						"search-" + e.jObject);
				$(e.modalSearch).find("h3").html($(e).find("blockquote").html());
				addSearchForm(e);
				e.callAddHandler = function() {
				};
				e.callRemoveHandler = function() {
				};
				e.checkBeforeAdd = function() {
					return true;
				};
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
				$(e).find("th.data").each(function(i, thData) {
					e.dataObj[$(thData).attr("name")] = "";
				});

				$(e).find("th.editable").each(function(i, thData) {
					e.techFields[i] = {
						type : $(thData).attr("role"),
						name : $(thData).attr("name"),
						description : $(thData).html(),
						valueFrom : $(thData).attr("valueFrom")
					};
				});

			});
}

function addRow(dataObj, additionalFields) {
	if (!this.checkBeforeAdd(dataObj, additionalFields, this)) {
		return false;
	}
	var trow = $("<tr class='row'></tr>");
	for ( var props in dataObj) {
		if (this.dataObj[props] != undefined) {
			var td = $("<td class='data' style='vertical-align: middle'></td>");
			td.append(createComponent({
				name : props,
				type : 'input',
				value : dataObj[props]
			}, this).addClass("input-flat"));
			trow.append(td);
		}
	}

	for ( var index in additionalFields) {
		var td = $("<td class='data'></td>");
		td.append(createComponent(additionalFields[index], this));
		trow.append(td);

	}

	$(trow).data("data", dataObj);
	this.callAddHandler(dataObj, trow);

	if (this.checkAfterAdd(dataObj, additionalFields, this)) {
		$(this).find("tbody").append(trow);
		$(this).find("thead").show();
		this.currentIndex++;
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
		context.currentIndex--;
		if (context.currentIndex <= 0) {
			$(context).find("thead").hide();
		}
	} else {
		return false;
	}

}

function createComponent(field, context) {
	switch (field.type) {
	case 'input-search':
		return $("<input placeholder='" + field.description
				+ "' class='form-control input-sm' id='" + field.name + "'/>");
	case 'input':
		var value = "";
		if (field.value != undefined) {
			value = field.value;
		}
		return $("<input placeholder='" + field.description
				+ "' class='form-control input-sm' id='" + field.name
				+ "' name='" + context.jObject + "[" + context.currentIndex
				+ "]." + field.name + "' value='" + value + "'/>");
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
		return $("<button type='button' onclick='toggleRadio(this)' class='btn btn-sm btn-danger btn-toggle text-center' data-group='"
				+ context.jObject
				+ "."
				+ field.name
				+ "'><input type='radio' name='"
				+ context.jObject
				+ "["
				+ context.currentIndex
				+ "]."
				+ field.name
				+ "' value='true' style='display:none'/><i class='icon-unchecked'></i></button>");
	case 'combo':
		var combo = $("#" + field.valueFrom).clone().addClass(
				"form-control input-sm").attr(
				"name",
				context.jObject + "[" + context.currentIndex + "]."
						+ field.name).show();
		$(combo).removeAttr("id");
		return combo;
	case 'combo-sync':
		var combo = $("#" + field.valueFrom).clone().addClass(
				"form-control input-sm " + field.valueFrom).attr(
				"name",
				context.jObject + "[" + context.currentIndex + "]."
						+ field.name).show();
		$(combo).removeAttr("id");
		return combo;
	case 'modalCaller':
		var $button = $("<a data-toggle='modal' style='color:white' href='#"+field.modalId+"' title='"+field.description+"'><button onclick='setFormulaMedicamentoTargetRow($(this).parent().parent().parent().parent())' class='btn btn-sm btn-warning' rel='tooltip' type='button' ><i class='icon-edit'></i></button></a>");
		var $div = $("<div style='display:inline'></div>");
		$div.append($button);
		return $div;
	case 'hidden':
		return $("<input type='hidden' name='" + context.jObject + "[" + context.currentIndex + "]." + field.name+ "'/>");
	}
}

function fillSearchResult(e, resultado) {

	var table = $("<table class='table table-hover'><thead></thead><tbody></tbody></table>");
	var row;

	for ( var index in resultado) {
		row = $("<tr style='cursor:pointer'></tr>");
		for ( var prop in resultado[index]) {
			if (e.dataObj[prop] != undefined) {
				row.append($("<td>" + resultado[index][prop] + "</td>"));
			}
		}
		$(row).data("data", resultado[index]);
		$(row).bind("click", function() {
			e.addRow($(this).data("data"), e.techFields);
			e.modalSearch.modal('hide');

		});
		$(table).find("tbody").append(row);
	}

	$(e.modalSearch).find(".modal-body table").remove();
	$(e.modalSearch).find(".modal-body").append(table);
	e.modalSearch.modal();
}

function addSearchForm(form) {
	var formData;
	if ($(form).data("searchForm") == undefined) {
		formData = [ {
			name : 'codigo' + form.jObject,
			type : 'input-search',
			description : 'Código'
		}, {
			name : 'descripcion' + form.jObject,
			type : 'input-search',
			description : 'Descripción'
		} ];
	} else {
		formData = $(form).data("searchForm");
	}

	var formElement = $("<div class='row form-group searchForm  collapsible-content'></div>");

	for ( var index in formData) {
		formElement
				.append($("<label class='text-right control-label col-lg-2' for='"
						+ formData[index].name
						+ "'>"
						+ formData[index].description + " </label>"));

		formElement.append($("<div class='col-lg-2'></div>").append(
				createComponent(formData[index], form)));
	}

	formElement
			.append(' <div class="col-lg-2"> '
					+ '<button type="button" role="buscar" id="buscar" class="btn btn-info btn-sm btn-block"> '
					+ '<i class="icon-search"></i> Buscar</button> ' + '</div> ');

	$(formElement).find("button").bind(
			"click",
			function() {
				$('body').modalmanager('loading');
				var searchForm = {};
				for ( var index in formData) {
					searchForm[formData[index].name] = $(formElement).find(
							"#" + formData[index].name).val();
				}

				$.ajax({
					url : "search/" + form.jObject,
					json : searchForm,
					type : "POST",
					async : false,
					success : function(resultado) {
						fillSearchResult(form, resultado);
					},
					dataType : "json"
				});

			});
	$(form).find("blockquote").after(formElement);
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