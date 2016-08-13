package me.utlight.struts;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{

	private static final long serialVersionUID = -5001914979396254647L;

	private String downloadPath;
	private String filename;
	
	
	public String getDownloadPath() {
		return downloadPath;
	}


	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public InputStream getTargetFile(){
		
		String realPath = downloadPath + "/" + getFilename();
		return ServletActionContext.getServletContext().getResourceAsStream(realPath);
	}
	
	public String execute(){
		return "success";
	}
	
}
