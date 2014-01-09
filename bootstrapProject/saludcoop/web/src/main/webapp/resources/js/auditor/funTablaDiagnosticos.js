//PRESETEOS Y FUNCIONES

$(function() {
    $('#tablaDiagnosticos').prop("initialized", false);
    var tablaDiagnosticos = {

        initDiagnosticos : function() {
            var asInitVals = new Array();
            $("form").submit(function(e) {
                e.preventDefault();
            });
            var diagTable = $('#tablaDiagnosticos').dataTable({
                "sPaginationType" : "full_numbers",
                "bDeferRender" : true, // Utilidad para optimar datasource al
                // usar Ajax
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
                "bProcessing" : true, // Cuando hay muchos de datos para
                // cargar
                "bServerSide" : false,
                "sAjaxSource" : '/web/bandejas/listarDiagnosticos',
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
                "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                    $(nRow).css("cursor", "pointer");
                    $(nRow).click(function() {
                        $("#idDiagnostico").val(aData.id);
                        $("#codigoDiagnostico").val(aData.codigo);
                        $("#descripcionDiagnostico").val(aData.descripcion);
                        $('#diagnosticosModal').modal('hide');
                    });
                }
            });
            $("#tablaDiagnosticos_filter").hide();
            $("#tablaDiagnosticos input").keyup(function() {
                diagTable.fnFilter(this.value, $("#tablaDiagnosticos input").index(this));
            });
            $("#tablaDiagnosticos input").each(function(i) {
                asInitVals[i] = this.value;
            });

        }
    };

    $("#btnLoadDiagnosticos").click(function() {
        if(!$('#tablaDiagnosticos').prop("initialized")) {
            tablaDiagnosticos.initDiagnosticos();
            $('#tablaDiagnosticos').prop("initialized", true);
            $('#tablaDiagnosticos').removeClass("table-striped table-bordered");
        }
    });
});
