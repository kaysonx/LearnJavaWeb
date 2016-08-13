package me.utlight.updownFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UploadFileServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡ�ϴ��ļ��ı���Ŀ¼
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		

		if(!file.exists() && !file.isDirectory())
		{
			System.out.println("Ŀ¼������...��Ҫ���´���");
			file.mkdir();
		}
		//��ȡ���ɵ���ʱ�ļ�����Ŀ¼ ����ͼƬ������ͼ����
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		
		
		String message = "";
		
		try {
			//ʹ��Apache�ļ��ϴ���������ļ��ϴ�
			//1.����һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//���ù����Ļ�������С�����ϴ��ļ����ļ���С����������ʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼��
			factory.setSizeThreshold(1024*100);//100KB��Ĭ��Ϊ10KB
			factory.setRepository(tmpFile);//������ʱ�ļ��ı���Ŀ¼
			
			//2.����һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//�����ļ��ϴ�����
			upload.setProgressListener(new ProgressListener() {
				
				@Override
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("�ļ���СΪ��"+pContentLength+",��ǰ�Ѵ���"+pBytesRead);
				}
			});
			upload.setHeaderEncoding("UTF-8");
			
			//3.�ж��Ƿ��Ǳ��ϴ�������
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			//���õ����ļ��ϴ������ֵ
			upload.setFileSizeMax(1024*1024);
			//�����ϴ��ļ����������ֵ
			upload.setSizeMax(1024*1024*10);
			//4.�����ϴ����ݡ�ÿ��fileitem��Ӧ���е�һ��
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				
				//���fileitem����ͨ����
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name+ " = "+ value);
				}else {
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					
					System.out.println(filename);
					//��ȡ�ļ����ļ�������
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//��ȡ�ļ��ĺ�׺�����ж��Ƿ�Ϸ�
					String fileExtname = filename.substring(filename.lastIndexOf(".")+1);
					System.out.println("�ϴ��ļ�����չ��Ϊ:"+fileExtname);
					
					InputStream in = item.getInputStream();
					
					String saveFilename = makeFileName(filename);
					String realSavePath = makePath(saveFilename,savePath);
					
					FileOutputStream out = new FileOutputStream(realSavePath+"\\" + saveFilename);
					
					byte[] buffer = new byte[1024];
					int len = 0;
					
					while((len=in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					
					in.close();
					out.close();
					item.delete();//ɾ���ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					message = String.format(
				                "�ļ��ϴ��ɹ�����ҳ����3��������ļ��б���<meta http-equiv='refresh' content='3;url=%s'", 
				                request.getContextPath()+"/FileListServlet");
					 request.setAttribute("message", message);
					 request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
				}
				
			}
		}catch(FileUploadBase.FileSizeLimitExceededException e){
			e.printStackTrace();
			request.setAttribute("message", "�����ļ��������ֵ��");
			request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
		}catch(FileUploadBase.SizeLimitExceededException e){
			e.printStackTrace();
			request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ!");
			request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
		}
		catch (Exception e) {
			message = "�ļ��ϴ�ʧ��!";
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	//��ȡΨһ�ļ���..Ҳ������md5
	private String makeFileName(String filename){
		return UUID.randomUUID().toString()+"_"+filename;
	}
	//�����ļ������ɴ洢Ŀ¼�������򵥵� ����ֱ�Ӱ���֡�����
	private String makePath(String filename, String savePath){
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;
		int dir2 = (hashcode&0xf0)>>4;
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;
		File file = new File(dir);
		
		if (!file.exists()) {
			file.mkdirs();
		}
		
		return dir;
	}

}
