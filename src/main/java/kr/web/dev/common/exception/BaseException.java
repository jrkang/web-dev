package kr.web.dev.common.exception;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import kr.web.dev.model.CommonBean;
import kr.web.dev.model.CommonBean.ReturnType;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BaseException extends Exception {
	
	private static final long	serialVersionUID	= 7582859848070204043L;
	
	protected String			message				= null;
	protected String			messageKey			= null;
	protected Object[]			messageParameters	= null;
	protected Exception			wrappedException	= null;
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
	
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public Object[] getMessageParameters() {
		return this.messageParameters;
	}
	
	public void setMessageParameters(Object[] messageParameters) {
		this.messageParameters = messageParameters;
	}
	
	public Throwable getWrappedException() {
		return this.wrappedException;
	}
	
	public void setWrappedException(Exception wrappedException) {
		this.wrappedException = wrappedException;
	}
	
	public BaseException() {
		this("BaseException without message", null, null);
	}
	
	public BaseException(String defaultMessage) {
		this(defaultMessage, null, null);
	}
	
	public BaseException(Throwable wrappedException) {
		this("BaseException without message", null, wrappedException);
	}
	
	public BaseException(String defaultMessage, Throwable wrappedException) {
		this(defaultMessage, null, wrappedException);
	}
	
	public BaseException(String defaultMessage, Object[] messageParameters,
			Throwable wrappedException) {
		super(wrappedException);
		
		String userMessage = defaultMessage;
		if (messageParameters != null) {
			userMessage = MessageFormat.format(defaultMessage, messageParameters);
		}
		this.message = userMessage;
	}
	
	public BaseException(MessageSource messageSource, String messageKey) {
		this(messageSource, messageKey, null, null, LocaleContextHolder.getLocale(), null);
	}
	
	public BaseException(MessageSource messageSource, String messageKey, Throwable wrappedException) {
		this(messageSource, messageKey, null, null, LocaleContextHolder.getLocale(),
				wrappedException);
	}
	
	public BaseException(MessageSource messageSource, String messageKey, Locale locale,
			Throwable wrappedException) {
		this(messageSource, messageKey, null, null, locale, wrappedException);
	}
	
	public BaseException(MessageSource messageSource, String messageKey,
			Object[] messageParameters, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, LocaleContextHolder.getLocale(),
				wrappedException);
	}
	
	public BaseException(MessageSource messageSource, String messageKey,
			Object[] messageParameters, Locale locale, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, locale, wrappedException);
	}
	
	public BaseException(MessageSource messageSource, String messageKey,
			Object[] messageParameters, String defaultMessage, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, defaultMessage, LocaleContextHolder.getLocale(),
				wrappedException);
	}
	
	public BaseException(MessageSource messageSource, String messageKey,
			Object[] messageParameters, String defaultMessage, Locale locale,
			Throwable wrappedException) {
		super(wrappedException);
		
		this.messageKey = messageKey;
		this.messageParameters = messageParameters;
		this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage,
				locale);
	}
	
	public CommonBean getErrorBean(CommonBean bean) {
		bean.setReturnType(ReturnType.error);
		bean.setErrorCode(messageKey);
		bean.setErrorMessage(message);
		return bean;
	}
	
	public List<CommonBean> getErrorBean(List<CommonBean> beans) {
		CommonBean bean = beans.get(0);
		bean.setReturnType(ReturnType.error);
		bean.setErrorCode(messageKey);
		bean.setErrorMessage(message);
		return beans;
	}
}
