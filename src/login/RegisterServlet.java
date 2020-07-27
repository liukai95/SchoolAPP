package login;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.JobInfoBean;
import model.beans.ResultBean;
import model.beans.UserBean;
import model.utils.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "登录验证信息", urlPatterns = { "/regist" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//参数编码
		response.setContentType("application/json;charset=utf-8");//返回内容编码，json格式
		ResultBean resultbean=new ResultBean();
		
		UserBean user=new UserBean();
		user.setTel(request.getParameter("tel"));
		user.setPassword(request.getParameter("password"));
		user.setJobTitle(request.getParameter("jobtitle"));
		user.setNickName(request.getParameter("nickname"));
		if(user.isRegistedByTel()){
			resultbean.setCode(ResultBean.ISREGISTED);
		}
		else{
			if(user.doRegist()){
				resultbean.setCode(ResultBean.SUCCESS);
				resultbean.addResult(user);
			}
			else{
				resultbean.setCode(ResultBean.FAILED);
			}
		}

		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson=new Gson();
		String result=gson.toJson(resultbean);
		result=StringUtils.deleteId(result);
		response.getWriter().write(result);
		response.flushBuffer();
	}

}
