var tablaIps ;
$(function() {
    tablaIps = {
        initIps : function() {
        	
            var asInitVals = new Array();
            $("form").submit(function(e) {
                e.preventDefault();
            });
            var ipsTable = $('#tablaIps').dataTable({
                "sPaginationType" : "full_numbers",
                "bDeferRender" : true, // Utilidad para optimar datasource al usar Ajax
                "bInfo" : false, // Informacion Pagina Y de X
                "bSort" : true, // Ordenar columnas
                "bPaginate" : true, // Paginacion
                // "iDisplayLength" : 40, // # de registros por pagina
                "bLengthChange" : false, // Cantidad de Paginas
                "bFilter" : true, // Boton Search
                "bAutoWidth" : true, // Ancho Automatico de Columnas
                "oLanguage" : {
                    "sSearch" : "Buscar"
                },
                "bProcessing" : true, // Cuando hay muchos de datos para cargar
                "bServerSide" : false,
                "sAjaxSource" : "/web/bandejas/listarSedeIps",
                "oLanguage" : {
                    "oPaginate" : {
                        "sFirst" : "««",
                        "sPrevious" : "«",
                        "sNext" : "»",
                        "sLast" : "»»"
                    },
                    "sEmptyTable": "No hay datos para mostrar"
                },
                "aoColumns" : [ {
                    "mDataProp" : "id"
                }, {
                    "mDataProp" : "nombre"
                }, {
                    "mDataProp" : "direccion"
                }, {
                    "mDataProp" : "telefono1"
                }, {
                    "mDataProp" : "municipio.descripcion"
                } ],
                "fnServerParams" : function(aoData) {
                    var itemID = $('#itemId').serializeArray();
                    aoData.push.apply(aoData, itemID);
                },
                "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                    $(nRow).css("cursor", "pointer");
                    $(nRow).click(function() {
                        $("#idSedeIps").val(aData.id).change();
                        $("#nombreIps").val(aData.nombre);
                        $("#direccionIps").val(aData.direccion);
                        $("#telefonoIps").val(aData.telefono1);
                        $('#dirIps').modal('hide');
                    });
                }
            });
            
            $("#tablaIps_filter").hide();
            $("#tablaIps input").keyup(function() {
                ipsTable.fnFilter(this.value, $("#tablaIps input").index(this));
            });
            
            $("#tablaIps input").each(function(i) {
                asInitVals[i] = this.value;
            });

        }
    };

    $("#btnLoadIps").click(function() {
        if(!$('#tablaIps').prop("initialized")) {
            tablaIps.initIps();
            $('#tablaIps').prop("initialized", true);
            $('#tablaIps').removeClass("table-striped table-bordered");
        }
    });
});
