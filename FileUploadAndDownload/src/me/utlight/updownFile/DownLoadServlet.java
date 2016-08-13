package me.utlight.updownFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DownLoadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filename = request.getParameter("filename");
		filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");
		
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String path = findFileSavePathByFileName(filename, fileSaveRootPath);
		
		File file = new File(path + "\\" + filename);
		
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已上天...");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
			return;
		}
		
		String realName = filename.substring(filename.indexOf("_")+1);
		//设置响应头 控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(realName,"UTF-8"));;
		
		FileInputStream in = new FileInputStream(path + "\\" + filename);
		
		OutputStream out = response.getOutputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		
		while ((len=in.read(buffer))>0) {
			out.write(buffer,0,len);
			
		}
		
		in.close();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String findFileSavePathByFileName(String filename, String saveRootPath){
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;
		int dir2 = (hashcode&0xf0)>>4;
		
		String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;
		
		return dir;
	}
}
