package me.utlight.struts;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String uploader;
	//注意以下三个需保持一致前缀名
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private String savePath;
	
	
	
	@Override
	public String execute() throws Exception {
		//设置文件保存路径
		String realpath = this.getSavePath();
		//判断上传文件是否为空
		if (upload != null) {
			File saveFile = new File(realpath, getUploadFileName());
			
			//判断路径是否存在
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			
			FileUtils.copyFile(upload, saveFile);
			
			ActionContext.getContext().put("message", "upload succeed!");
			
			return "success";
		}
		
		return "error";
	}
	
	
	
	
	
	

	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	
}
