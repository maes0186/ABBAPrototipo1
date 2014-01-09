package com.conexia.saludcoop.spring.editor;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.conexia.saludcoop.Globals;

public class GlobalBindingInitializer implements WebBindingInitializer {

    public void initBinder(WebDataBinder binder, WebRequest request) {
    	SimpleDateFormat sdf = Globals.getInstance().getDateFormatter();
        binder.registerCustomEditor(java.util.Date.class, new SaludCoopCustomDateEditor(sdf, false));
    }

}
