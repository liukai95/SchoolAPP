package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.beans.ResultBean;
import model.beans.UserBean;
import model.utils.ModelDataExecute;
import model.utils.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "用户登录接口", urlPatterns = { "/login" })
//@MultipartConfig()(多参数时使用)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//参数编码
		response.setContentType("application/json;charset=utf-8");//返回内容编码，json格式
//		//获取传递进来的login body中的json数据
//		BufferedReader read=new BufferedReader(new InputStreamReader(request.getPart("aaa").getInputStream()));
//		String loginRequest=new String();
//		String buffer=new String();
//		while((buffer=read.readLine())!=null){
//			loginRequest+=buffer;
//		}
//		System.out.println(loginRequest);
		
		
		//到数据库中查询相关信息,并返回信息
		UserBean user=new UserBean();
		user.setTel(request.getParameter("tel"));
		user.setPassword(request.getParameter("password"));
		ResultBean resultbean=new ResultBean();
		if(user.doLogin()){
			resultbean.setCode(ResultBean.SUCCESS);
			resultbean.addResult(user);
			resultbean.setMessageNum(resultbean.getMessage().size());
		}
		else{
			resultbean.setCode(ResultBean.FAILED);
			resultbean.setMessageNum(0);
		}

		Gson gson=new Gson();
		String result=gson.toJson(resultbean);
		result=StringUtils.deleteId(result);
		response.getWriter().write(result);
		response.flushBuffer();
		
		//response.getWriter().append("Served aa啊t: ").append(request.getContextPath());//测试使用
//		try {
//			if(result.next()) {
//				response.getWriter().write("true");
//			}
//			else{
//				response.getWriter().write("false");
//			}
//			response.flushBuffer();
//			
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
	}

}
