package me.utlight.jdbcup;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.utlight.utils.JdbcUtils_C3P0;
import me.utlight.utils.JdbcUtils_JNDI;

//JNDI只能通过servlet测试。。因为tomcat
@WebServlet("/testJNDI")
public class testJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public testJNDI() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
        	conn = JdbcUtils_C3P0.getConnection();
			String sql = "insert into account(name,money) values(?,?)";
			//高版本的mysql驱动得要Statement.RETURN_GENERATED_KEYS 才能获得自动生成的主键
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, "9999");
			pst.setObject(2, 555);
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				System.out.println(rs.getInt(1));
			}
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            JdbcUtils_JNDI.release(conn, pst, rs);
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
