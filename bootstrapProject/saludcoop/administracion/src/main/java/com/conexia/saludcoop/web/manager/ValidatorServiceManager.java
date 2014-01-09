package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;

@Service
@Transactional
public class ValidatorServiceManager {

	private static final String COMPROBAR_DERECHOS = "comprobacionDerechos";

	
	public RespuestaDto comprobarDerechos(AfiliadoDto a){
		try {
			
		
		RespuestaDto rta =  this.getRestClient().postForObject(getUrl(ValidatorServiceManager.COMPROBAR_DERECHOS), a, RespuestaDto.class);
		
		return rta;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}



	private String getUrl(String transaction) {

		return "http://localhost:8080/validador/" + transaction;
	}

	private RestTemplate getRestClient() {

		RestTemplate restTemplate = new RestTemplate();

		setTimeOut(restTemplate);

		HttpMessageConverter<?> conv = new MappingJacksonHttpMessageConverter();

		HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
		List<HttpMessageConverter<?>> convs = new ArrayList<HttpMessageConverter<?>>();
		convs.add(conv);
		convs.add(formHttpMessageConverter);
		restTemplate.setMessageConverters(convs);

		return restTemplate;

	}

	public static void setTimeOut(RestTemplate restTemplate) {
		if (restTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory())
					.setConnectTimeout(10 * 1000);
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory())
					.setReadTimeout(10 * 10000);
		} else if (restTemplate.getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) restTemplate
					.getRequestFactory()).setReadTimeout(10 * 1000);
			((HttpComponentsClientHttpRequestFactory) restTemplate
					.getRequestFactory()).setConnectTimeout(10 * 10000);
		}
	}

}
