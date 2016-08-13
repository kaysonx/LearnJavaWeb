package me.utlight.updownFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FileListServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		Map<String, String> fileNameMap = new HashMap<String, String>();
		
		listFile(new File(uploadFilePath),fileNameMap);
		
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/FileOp/FileList.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	//�ݹ����ָ��Ŀ¼�µ������ļ�
	public void listFile(File file,Map<String, String> map){
		
		if (!file.isFile()) {
			File[] files = file.listFiles();
			for(File f : files){
				//�ݹ�
				listFile(f, map);
			}
		}else {
			//��ȡ�ļ���ʵ����
			String realName = file.getName().substring(file.getName().indexOf("_")+1);
			map.put(file.getName(), realName);
		}
	}
}
