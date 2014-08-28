package kr.web.dev.model;

public class CommonBean {
	public enum ReturnType {
		success, warning, error
	};
	
	ReturnType	returnType;
	String	errorCode;
	String	errorMessage;
	
	public ReturnType getReturnType() {
		return returnType;
	}
	
	public void setReturnType(ReturnType returnType) {
		this.returnType = returnType;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "CommonBean [returnType=" + returnType + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}
	
}
