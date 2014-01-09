//PRESETEOS Y FUNCIONES

/*El �ndice del array corresponde con el id del tipo de documento y de ah�, la longitud del n�mero*/
var maxLength = [16, 10, 6, 16, 11, 11, 12, 12];
var allowsText = [];

/**
 * 
 * Gestionar listado de documentacion requerida y vigencias.
 * 
 * @author rpadilla
 */
$(function() {

    var formulario = {
        init: function() {
            $(".bs-sidenav li:nth-child(2)").addClass("active");

            $("form").submit(function(e) {
                e.preventDefault();
            });
            $('#tablaSolicitudes').dataTable({
                "sPaginationType": "full_numbers",
                "bDeferRender": true, // Utilidad para optimar datasource al
                // usar Ajax
                "bInfo": false, // Informacion Pagina Y de X
                "bSort": true, // Ordenar columnas
                "bPaginate": true, // Paginacion
                 "iDisplayLength": 40, // # de registros por pagina
                "bLengthChange": true, // Cantidad de Paginas
                "bFilter": false, // Boton Search
                "bAutoWidth": true, // Ancho Automatico de Columnas
                "bProcessing": true, // Cuando hay muchos de datos para
                // cargar
                "bServerSide": true,
                "sAjaxSource": '/web/auditor/listarSolicitudesCTC',
                "oLanguage": {
                    "oPaginate": {
                        "sFirst": "Inicio",
                        "sPrevious": "Anterior",
                        "sNext": "Siguiente",
                        "sLast": "Ultimo"
                    }
                },
                "fnServerParams": function ( aoData ) {
                    aoData.push( { "name": "more_data", "value": $('#numeroSolicitudSearch').val() } );
                },
                "aoColumns": [{
                        "mDataProp": "codigo"
                    }, {
                        "mDataProp": "descripcion"
                    }, {
                    	"mDataProp": "id"
                    }]
            });
            $('#search').click(function() {
//                var post = jQuery.post("/web/auditor/listarSolicitudesCTC2",
//                        $('#formFiltroPrincipal').serialize()).done(function() {
//                    window.alert('funciona2');
//                }).fail(function() {
//                    window.alert('no funciona');
//                });
//                post.done(function(data) {
//                    $('#numeroSolicitudSearch').val(data.afiliado);
//                });
                $('#tablaSolicitudes').dataTable().fnDraw();
            });
        },
        setMaxLength: function() {

        }
    };
    formulario.init();
});
