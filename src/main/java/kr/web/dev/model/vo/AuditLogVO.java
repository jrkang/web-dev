package kr.web.dev.model.vo;

public class AuditLogVO {

	private String actor;
	private String message;
	private String executeMethod;
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExecuteMethod() {
		return executeMethod;
	}
	public void setExecuteMethod(String executeMethod) {
		this.executeMethod = executeMethod;
	}
	
	@Override
	public String toString() {
		return "AuditLogVO [actor=" + actor + ", message=" + message
				+ ", executeMethod=" + executeMethod + "]";
	}
	
}
