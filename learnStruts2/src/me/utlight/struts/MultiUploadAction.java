package me.utlight.struts;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MultiUploadAction extends ActionSupport{

	private static final long serialVersionUID = 7214969193877624157L;
	
	private File[] uploads;
	private String[] uploadsContentType;

	private String[] uploadsFileName;
	private String savePath;
	
	
	@Override
	public String execute() throws Exception {
		
		String realPath = this.getSavePath();
		if (uploads != null) {
			File savePath = new File(realPath);
			
			if (!savePath.exists()) {
				savePath.mkdirs();
			}
			
			for (int i = 0; i < uploads.length; i++) {
				File saveFile = new File(savePath,getUploadsFileName()[i]);
				FileUtils.copyFile(uploads[i], saveFile);
			}
			
			ActionContext.getContext().put("message", "upload succeed!");
			return "success";
		}
		
		return "error";
	}
	
	public File[] getUploads() {
		return uploads;
	}
	public void setUploads(File[] uploads) {
		this.uploads = uploads;
	}
	
	public String[] getUploadsFileName() {
		return uploadsFileName;
	}
	public void setUploadsFileName(String[] uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String[] getUploadsContentType() {
		return uploadsContentType;
	}

	public void setUploadsContentType(String[] uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}
}
