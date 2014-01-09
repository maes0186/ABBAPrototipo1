//PRESETEOS Y FUNCIONES

/*El �ndice del array corresponde con el id del tipo de documento y de ah�, la longitud del n�mero*/
var maxLength = [ 16, 10, 6, 16, 11, 11, 12, 12 ];
var allowsText = [];
var tablaIps ;
$(function() {
    tablaIps = {
        initIps : function() {
        	
            var asInitVals = new Array();
            $("form").submit(function(e) {
                e.preventDefault();
            });
            var ipsTable = $('#tablaEspecialidades').dataTable({
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
                "sAjaxSource" : "/web/profesionalCrud/listarEspecialidadPorMedico",
                "oLanguage" : {
                    "oPaginate" : {
                        "sFirst" : "««",
                        "sPrevious" : "«",
                        "sNext" : "»",
                        "sLast" : "»»"
                    }
                },
                "aoColumns" : [ {
                    "mDataProp" : "codigo"
                }, {
                    "mDataProp" : "descripcion"
                } ],
                "fnServerParams" : function(aoData) {
                    var itemID = $('#itemId').serializeArray();
                    aoData.push.apply(aoData, itemID);
                },
                "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                    $(nRow).css("cursor", "pointer");
                    $(nRow).click(function() {
                        $("#idSedeIps").val(aData.id).change();
                    });
                }
            });
            $("#tablaIps_filter").hide();
            $("#tablaIps input").keyup(function() {
                /* Filter on the column (the index) of this element */
                log(this.value);
                ipsTable.fnFilter(this.value, $("#tablaIps input").index(this));
            });
            /*
             * Support functions to provide a little bit of 'user friendlyness'
             * to the textboxes in the footer
             */
            $("#tablaIps input").each(function(i) {
                asInitVals[i] = this.value;
            });

        }
    };
    if(ejecutarBusqueda){
//    	tablaIps.initIps();
    };
});
