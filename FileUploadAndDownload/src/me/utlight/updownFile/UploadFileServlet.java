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
		
		//获取上传文件的保存目录
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		

		if(!file.exists() && !file.isDirectory())
		{
			System.out.println("目录不存在...需要重新创建");
			file.mkdir();
		}
		//获取生成的临时文件保存目录 比如图片的缩略图。。
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		
		
		String message = "";
		
		try {
			//使用Apache文件上传组件处理文件上传
			//1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置工厂的缓冲区大小。当上传文件的文件大小超过缓冲区时，就会生成一个临时文件存放到指定的临时目录。
			factory.setSizeThreshold(1024*100);//100KB。默认为10KB
			factory.setRepository(tmpFile);//设置临时文件的保存目录
			
			//2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				
				@Override
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为："+pContentLength+",当前已处理："+pBytesRead);
				}
			});
			upload.setHeaderEncoding("UTF-8");
			
			//3.判断是否是表单上传的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			//设置单个文件上传的最大值
			upload.setFileSizeMax(1024*1024);
			//设置上传文件总量的最大值
			upload.setSizeMax(1024*1024*10);
			//4.解析上传数据。每个fileitem对应表单中的一项
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				
				//如果fileitem是普通数据
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
					//获取文件的文件名部分
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//获取文件的后缀名以判断是否合法
					String fileExtname = filename.substring(filename.lastIndexOf(".")+1);
					System.out.println("上传文件的扩展名为:"+fileExtname);
					
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
					item.delete();//删除文件上传时生成的临时文件
					message = String.format(
				                "文件上传成功！本页将在3秒后跳到文件列表！！<meta http-equiv='refresh' content='3;url=%s'", 
				                request.getContextPath()+"/FileListServlet");
					 request.setAttribute("message", message);
					 request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
				}
				
			}
		}catch(FileUploadBase.FileSizeLimitExceededException e){
			e.printStackTrace();
			request.setAttribute("message", "单个文件超出最大值！");
			request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
		}catch(FileUploadBase.SizeLimitExceededException e){
			e.printStackTrace();
			request.setAttribute("message", "上传文件的总的大小超出限制的最大值!");
			request.getRequestDispatcher("/FileOp/message.jsp").forward(request, response);
		}
		catch (Exception e) {
			message = "文件上传失败!";
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	//获取唯一文件名..也可以用md5
	private String makeFileName(String filename){
		return UUID.randomUUID().toString()+"_"+filename;
	}
	//根据文件名生成存储目录。。。简单点 可以直接按天分。。。
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
