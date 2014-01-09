/**
 * 
 * @param parent -
 *            Alert Container, if null alert must be manually appended
 * @param conf - {
 *            type: [success, info, warning, danger], message: TEXTO PARA
 *            MOSTRAR, [dismissable]: [true, false], [autoOpen]: [true, false],
 *            [id]: ID DEL DIV,
 *  }
 * 
 */
function createAlertMessage(parent, conf) {
    var alertDiv = $("<div " + (conf.id != undefined ? "id='" + conf.id + "'" : "") + " class='alert "
            + (conf.dismissable ? "alert-dismissable" : "") + " alert-" + conf.type + "' style=" + (!conf.autoShow ? "'display:none'" : "")
            + ">" + (conf.dismissable ? "<a type='button' class='close' data-dismiss='alert' style='text-decoration: none'>×</a>" : "") + conf.message
            + "</div>");
    if (parent != undefined && parent != null) {
        $(parent).append(alertDiv);
    }

    return alertDiv;

}

/**
 * Construye el mensaje de error a partir de la lista <code>errors</code>
 * indicada por parámetro
 * 
 * @param parent
 *            Componente padre en el que se mostrarán los errores generados
 * @param errors
 *            Lista de errores a mostrar
 */
function createErrorMessages(parent, errors) {
    var alertDiv = $("<div class='alert alert-dismissable alert-danger'>"
            + "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
    $.each(errors, function(i, value) {
        var span = "<div><i class='icon-warning-sign'></i> " + value + "<div>";
        alertDiv.append(span);
    });
    $(parent).append(alertDiv);
}

/**
 * Construye el mensaje a partir de la lista <code>msgs</code> indicada por
 * parámetro, del tipo indicado
 * 
 * @param parent
 *            Componente padre en el que se mostrarán los mensajes generados
 * @param msgs
 *            Lista de mensajes a mostrar
 * @param type
 *            Tipo de mensaje a generar: info, warning, success
 * 
 */
function createMessages(parent, msgs, type, icon) {
    if (msgs != null) {
        $(parent).empty();
        var alertDiv = $("<div class='alert alert-dismissable alert-" + type + "'>"
                + "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
        $.each(msgs, function(i, value) {
            var span = "<div><i class='" + icon + "'></i> " + value + "<div>";
            alertDiv.append(span);
        });
        $(parent).append(alertDiv);
    }
}

/**
 * Construye el mensaje a partir de la lista <code>msgs</code> indicada por
 * parámetro, del tipo indicado
 * 
 * @param parent
 *            Componente padre en el que se mostrarán los mensajes generados
 * @param msgs
 *            Lista de mensajes a mostrar
 * @param type
 *            Tipo de mensaje a generar: info, warning, success
 * 
 */
function createMessages(parent, msgs, type, icon, append) {
    if (msgs != null) {
        if(append != undefined && !append) {
            $(parent).empty();            
        }
        var alertDiv = $("<div class='alert alert-dismissable alert-" + type + "'>"
                + "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
        $.each(msgs, function(i, value) {
            var span = "<div><i class='" + icon + "'></i> " + value + "<div>";
            alertDiv.append(span);
        });
        $(parent).append(alertDiv);
    }
}

/**
 * Agrega un error a un contenedor de errores, si es el primero, formatea el
 * contenedor
 * 
 * @param parent
 *            El contenedor de los errores
 * @param msg
 *            El mensaje de error que se agregará
 */
function appendErrorMessage(parent, value) {
    if (parent.find(".alert").length == 0) {
        var alertDiv = $("<div class='alert alert-dismissable alert-danger'>"
                + "<button type='button' class='close' data-dismiss='alert'>×</button></div>");
        $(parent).append(alertDiv);
    }
    var span = "<div><i class='icon-warning-sign'></i> " + value + "<div>";
    parent.find(".alert").append(span);
}

/**
 * Agrega un error pegado al campo validado
 * 
 * @param parent
 *            El campo validado
 * @param msg
 *            El mensaje de error que se agregará
 */
function appendEmbebbedErrorMessage(field, value) {
    var label = '<label for="' + field.attr("id") + '" generated="true" class="error">' + value + '</label>';
    field.after(label);
}

/**
 * Crea una alerta que se muestra por cierto tiempo en pantalla
 * 
 * @param type
 * @param message
 * @param delay
 */
function newAlert(type, messages, delay, icon) {
    var div = $("<div class='alert alert-" + type + "' data-alert></div>");
    $.each(messages, function(i, value) {
        var span = "<div><i class='" + icon + "'></i> " + value + "<div>";
        div.append(span);
    });
    $("#alert-area").append(div);
    $("#alert-area").fadeIn("slow", function() {
    });
    $("#alert-area").delay(delay).fadeOut("slow", function() {
        $("#alert-area").empty();
    });
}

function scrollTop(timeout) {
    $('html, body').animate({
        scrollTop : 0
    }, timeout);
}
function scrollModalTop(modal, timeout) {
    modal.animate({
        scrollTop : (0)
    }, timeout);
}
function log(message) {
    if (console)
        console.log(message);
}