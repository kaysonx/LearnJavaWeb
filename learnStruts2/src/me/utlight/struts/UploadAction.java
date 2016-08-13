package me.utlight.struts;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String uploader;
	//ע�����������豣��һ��ǰ׺��
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private String savePath;
	
	
	
	@Override
	public String execute() throws Exception {
		//�����ļ�����·��
		String realpath = this.getSavePath();
		//�ж��ϴ��ļ��Ƿ�Ϊ��
		if (upload != null) {
			File saveFile = new File(realpath, getUploadFileName());
			
			//�ж�·���Ƿ����
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
