//PRESETEOS Y FUNCIONES

/*El �ndice del array corresponde con el id del tipo de documento y de ah�, la longitud del n�mero*/
var maxLength = [ 16, 10, 6, 16, 11, 11, 12, 12 ];
var allowsText = [];

/**
 * 
 * Gestionar listado de documentacion requerida y vigencias.
 * 
 * @author rpadilla
 */
$(function() {

    var formulario = {

        initHistorial : function() {
            var asInitVals = new Array();
            $("form").submit(function(e) {
                e.preventDefault();
            });
            var oTable=$('#tablaHistorico').dataTable({
                "sPaginationType" : "full_numbers",
                "bDeferRender" : true, // Utilidad para optimar datasource al
                // usar Ajax
                "bInfo" : false, // Informacion Pagina Y de X
                "bSort" : true, // Ordenar columnas
                "bPaginate" : false, // Paginacion
                //"iDisplayLength" : 40, // # de registros por pagina
                "bLengthChange" : true, // Cantidad de Paginas
                "bFilter" : true, // Boton Search
                "bAutoWidth" : true, // Ancho Automatico de Columnas
                "oLanguage": {
                    "sSearch": "Buscar"
                },
                "bProcessing" : true, // Cuando hay muchos de datos para
                // cargar
                "bServerSide" : false,
                "sAjaxSource" : '/web/bandejas/listarHistorial',
                "aoColumns" : [ {
                    "mDataProp" : "idSolicitud"
                }, {
                    "mDataProp" : "regional"
                }, {
                    "mDataProp" : "fecha"
                }, {
                    "mDataProp" : "medicamento"
                }, {
                    "mDataProp" : "estado"
                }, {
                    "mDataProp" : "origen"
                }, {
                    "mDataProp" : "codigoProducto"
                }, {
                    "mDataProp" : "unidadesAprobadas"
                }, {
                    "mDataProp" : "periodoAprobado"
                }, {
                    "mDataProp" : "diasPeriodo"
                },
                ]
            });
            $('#tablaHistorico_filter').hide();
            $("#tablaHistorico input").keyup( function () {
                /* Filter on the column (the index) of this element */
                oTable.fnFilter( this.value, $("#tablaHistorico input").index(this) );
            } );
            /*
             * Support functions to provide a little bit of 'user friendlyness' to the textboxes in
             * the footer
             */
            $("#tablaHistorico input").each( function (i) {
                asInitVals[i] = this.value;
            } );
             
        }
    };
    
    formulario.initHistorial();
});
