package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.ResultBean;
import model.beans.UserBean;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet(description = "修改用户密码", urlPatterns = { "/updatePwd" })
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 参数编码

		// 到数据库中查询相关信息,并返回信息
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setTel(tel);
		user.setPassword(password);
		ResultBean resultbean = new ResultBean();
		if (user.updatePassword()) {
			resultbean.setCode(ResultBean.SUCCESS);
			resultbean.setMessageNum(resultbean.getMessage().size());
		} else {
			resultbean.setCode(ResultBean.FAILED);
			resultbean.setMessageNum(0);
		}

		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(resultbean));
		response.flushBuffer();
	}

}
