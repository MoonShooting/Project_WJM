package vo;

public class ActionForward {
	   // 필드
	   private String path = null;
	   private boolean isRedirect = false;
	   
	   public String getPath() {
	      return path;
	   }
	   public void setPath(String path) {
	      this.path = path;
	   }
	   public boolean isRedirect() {
	      return isRedirect;
	   }
	   public void setRedirect(boolean b) {
	      this.isRedirect = b;
	   }
	   
	   
}