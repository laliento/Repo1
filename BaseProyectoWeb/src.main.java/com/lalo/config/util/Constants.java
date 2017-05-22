package com.lalo.config.util;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public enum Constants {
	NAME_SYSTEM("SoftMvi"),
	SESSION_USER_kEY("SessionUser"),
	CONSTANTE_ESTATUS_ACTIVO(Integer.valueOf(1)),
	CONSTANTE_MAIL_ADMINISTRADOR("mailAdministrador"),
	MSG_ERR_REPIT_USER("msg_err_repitUser"),
	MSG_ERR_USER_NUMEMPLEADO_VACIO("msg_err_user_numEmpleado_empty"),
	MSG_ERR_USER_USERNAME_VACIO("msg_err_user_userName_empty"),
	MSG_ERR_USER_NOMBRE_VACIO("msg_err_user_nombre_empty"),
	MSG_ERR_USER_PERFIL_VACIO("msg_err_user_perfil_empty"),
	MSG_ERR_USER_IDIOMA_VACIO("msg_err_user_idioma_empty"),
	MSG_ERR_USER_ESTATUS_VACIO("msg_err_user_estatus_empty"),
	MSG_ERR_USER_CORREO_VACIO("msg_err_user_correo_empty"),
	MSG_SAVE_OK("msg_save_ok"),
	CONSTANTE_COMANDO_SU("su"),
	CONSTANTE_GUION("-"),
	CONSTANTE_ARROBA("@"),
	CONSTANTE_DOS_PUNTOS(":"),
	CONSTANTE_EXT_ZIP(".zip"),
	CONSTANTE_COMANDO_CHOWN("chown"),
	CONSTANTE_TODOS_PERMISOS("777"),
	CONSTANTE_COMANDO_CHMOD("chmod"),
	ESTATUS_PRODUCTO_IMPORTADO(Integer.valueOf(1)),
	ESTATUS_PRODUCTO_APROBADO(Integer.valueOf(5)),
	ESTATUS_PRODUCTO_INACTIVO(Integer.valueOf(6)),
	USUARIO_SISTEMA(0),
	MSG_RESOLUCION_DONT_CONFIGURE("msg_resolucion_dont_configure"),
	EXT_JPG("jpg"),
	EXT_JPEG("jpeg"),
	EXT_JPE("jpe"),
	EXT_JIF("jif"),
	EXT_JFIF("jfif"),
	EXT_JFI("jfi"),
	EXT_TXT("txt"),
//	Monitor Message
	LOGGER_FATAL_ERROR_MESSAGE("Se env�o correo electronico al administrador para notificar una Exception "),
	DETAIL_MAIL_ERROR_MONITOR("El detalle del error es el siguiente: "),
	SUBJECT_MAIL_ERROR_MONITOR("Error fatal en el aplicativo Fotos y Descripciones"),
	HEADER_MAIL_ERROR_MONITOR("El proceso de monitoreo de Fotos y Descripciones ha detectado un error que podr�a comprometer la operaci�n del aplicativo."),
	CONSIDERATION_MAIL_ERROR_MONITOR("Favor de reportar este incidente al area de Infraestructura."),
	PROCESS_MAIL_ERROR_MONITOR("Monitoreo de sistema"),
	TITLE_MAIL_ERROR_MONITOR("Error fatal del sistema Fotos y Descripciones"),
	CALIDAD_IMG_EXTRACCION(Float.valueOf((float) 0.7)),
	CALIDAD_IMG_SUBIDA(Float.valueOf((float) 1.0)),
	CUADRANTE_SUPERIOR(Integer.valueOf(1)),
	CHAR_PIPE ("|"),
	CHAR_COMA (","),
	CHAR_IGUAL ("="),
	EXTRACCION_CHUNK(10000),
	DEFAULT_CHARSET("UTF-8"),
	CONSTANTE_CARACTER_PUNTO(".")
	,
	LOGATT_MINUTOS_CUENTA_BLOQUEADA(120),
	LOGATT_NUMERO_INTENTOS_PERMITIDOS(2),
	;
	
	private String string;
	private Integer integer;
	private Long longValue;
	private Float floatValue;

	
	private Constants(String string){
		this.string = string;
	}
	
	private Constants(Integer integer){
		this.integer = integer;
	}
	
	private Constants(Long value) {
		longValue = value;
	}
	
	private Constants(Float value) {
		floatValue = value;
	}
	
	public String getString() {
		return string;
	}
	
	public Integer getInteger() {
		return integer;
	}
	
	public Long getLong() {
		return longValue;
	}
	
	public Float getFloat() {
		return floatValue;
	}
}
