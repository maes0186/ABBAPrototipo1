package com.conexia.saludcoop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public final class Globals {

	private static Globals instance;
	
	private int hitsPerPage;

	private String dateFormatPattern;
	private String hourFormatPattern;
	private String hourFormatNoSecondsPattern;
	private String pathFolderRips;
	private String pathFolderHistoricos;
	private String pathFolderHistoricosZips;
	private DecimalFormat decimalFormatter;
	private String UIdateFormatPattern;
	private String decimalFormatPattern;
	private SimpleDateFormat dateFormatter;
	private Integer maxLoginAttempts;
	private Integer warningDaysForExpirationDate;
	private Integer maxDaysForExpirationTime;
	
	
	private Globals() {
		//set here all default values
		hitsPerPage = 20;
		
	}
	
	public static Globals getInstance() {
		if(instance == null) {
			instance = new Globals();
		}
		return instance;
	}

	public int getHitsPerPage() {
		return this.hitsPerPage;
	}
	
	public void setHitsPerPage(int hitsPerPage) {
		this.hitsPerPage = hitsPerPage;
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public SimpleDateFormat getDateFormatter() {
		if(dateFormatter == null) {
			dateFormatter = new SimpleDateFormat(dateFormatPattern);
		}
		return dateFormatter;
	}

	public void setDateFormatter(SimpleDateFormat dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	public String getUIdateFormatPattern() {
		return UIdateFormatPattern;
	}

	public void setUIdateFormatPattern(String uIdateFormatPattern) {
		UIdateFormatPattern = uIdateFormatPattern;
	}

	public DecimalFormat getDecimalFormatter() {
		return decimalFormatter;
	}

	public void setDecimalFormatter(DecimalFormat decimalFormatter) {
		this.decimalFormatter = decimalFormatter;
	}

	public String getDecimalFormatPattern() {
		return decimalFormatPattern;
	}

	public void setDecimalFormatPattern(String decimalFormatPattern) {
		this.decimalFormatPattern = decimalFormatPattern;
	}

	/**
	 * Devuelve el valor de hourFormatPattern.
	 *
	 * @return El valor de hourFormatPattern.
	 */
	public String getHourFormatPattern() {
		return hourFormatPattern;
	}

	/**
	 * Asigna un nuevo valor a hourFormatPattern.
	 *
	 * @param hourFormatPattern El valor a asignar a hourFormatPattern.
	 */
	public void setHourFormatPattern(String hourFormatPattern) {
		this.hourFormatPattern = hourFormatPattern;
	}

	/**
	 * Devuelve el valor de pathFolderRips.
	 *
	 * @return El valor de pathFolderRips.
	 */
	public String getPathFolderRips() {
		return pathFolderRips;
	}

	/**
	 * Asigna un nuevo valor a pathFolderRips.
	 *
	 * @param pathFolderRips El valor a asignar a pathFolderRips.
	 */
	public void setPathFolderRips(String pathFolderRips) {
		this.pathFolderRips = pathFolderRips;
	}

	/**
	 * @return the pathFolderHistoricos
	 */
	public String getPathFolderHistoricos() {
		return pathFolderHistoricos;
	}

	/**
	 * @param pathFolderHistoricos the pathFolderHistoricos to set
	 */
	public void setPathFolderHistoricos(String pathFolderHistoricos) {
		this.pathFolderHistoricos = pathFolderHistoricos;
	}

	/**
	 * @return the pathFolderHistoricosZips
	 */
	public String getPathFolderHistoricosZips() {
		return pathFolderHistoricosZips;
	}

	/**
	 * @param pathFolderHistoricosZips the pathFolderHistoricosZips to set
	 */
	public void setPathFolderHistoricosZips(String pathFolderHistoricosZips) {
		this.pathFolderHistoricosZips = pathFolderHistoricosZips;
	}
	
	public void setMaxLoginAttempts(String maxLoginAttempts){
		this.maxLoginAttempts = Integer.parseInt(maxLoginAttempts);
	}
	
	public Integer getMaxLoginAttempts(){
		return maxLoginAttempts;
	}

	/**
	 * Devuelve el valor de hourFormatNoSecondsPattern.
	 *
	 * @return El valor de hourFormatNoSecondsPattern.
	 */
	public String getHourFormatNoSecondsPattern() {
		return hourFormatNoSecondsPattern;
	}

	/**
	 * Asigna un nuevo valor a hourFormatNoSecondsPattern.
	 *
	 * @param hourFormatNoSecondsPattern El valor a asignar a hourFormatNoSecondsPattern.
	 */
	public void setHourFormatNoSecondsPattern(String hourFormatNoSecondsPattern) {
		this.hourFormatNoSecondsPattern = hourFormatNoSecondsPattern;
	}

	/**
	 * Devuelve el valor de warningDaysForExpirationDate.
	 *
	 * @return El valor de warningDaysForExpirationDate.
	 */
	public Integer getWarningDaysForExpirationDate() {
		return warningDaysForExpirationDate;
	}

	/**
	 * Asigna un nuevo valor a warningDaysForExpirationDate.
	 *
	 * @param warningDaysForExpirationDate El valor a asignar a warningDaysForExpirationDate.
	 */
	public void setWarningDaysForExpirationDate(String  warningDaysForExpirationDate) {
		this.warningDaysForExpirationDate = Integer.valueOf(warningDaysForExpirationDate);
	}

	/**
	 * Devuelve el valor de maxDaysForExpirationTime.
	 *
	 * @return El valor de maxDaysForExpirationTime.
	 */
	public Integer getMaxDaysForExpirationTime() {
		return maxDaysForExpirationTime;
	}

	/**
	 * Asigna un nuevo valor a maxDaysForExpirationTime.
	 *
	 * @param maxDaysForExpirationTime El valor a asignar a maxDaysForExpirationTime.
	 */
	public void setMaxDaysForExpirationTime(String maxDaysForExpirationTime) {
		this.maxDaysForExpirationTime = Integer.valueOf(maxDaysForExpirationTime);
	}
	
	
}
