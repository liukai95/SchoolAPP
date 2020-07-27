package info;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import model.beans.*;
import model.utils.StringUtils;

/**
 * Servlet implementation class AddInfoServlet
 */
@WebServlet(description = "增加三类信息", urlPatterns = { "/addinfo" })
public class AddInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");// 参数编码
		response.setContentType("application/json;charset=utf-8");// 返回内容编码，json格式
		int type = Integer.parseInt(request.getParameter("type"));
		ResultBean resultbean = new ResultBean();
		if (type == 0) {// 招聘类信息
			JobInfoBean job = new JobInfoBean();
			job.setUser_iduser(Long.parseLong(request.getParameter("userid")));
			job.setPost(request.getParameter("post"));
			job.setCompany(request.getParameter("company"));
			job.setMoney(request.getParameter("money"));
			job.setWorkingplace(request.getParameter("workingplace"));
			job.setJobcontent(request.getParameter("jobcontent"));
			job.setWorkhour(request.getParameter("workhour"));
			int id = job.doInsert();
			if (id != 0) {
				resultbean.setCode(ResultBean.SUCCESS);
				resultbean.setId(id);
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}
		} else if (type == 1) {

		} else if (type == 2) {

		} else {
			resultbean.setCode(ResultBean.PARAMETERERROR);
		}
		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson = new Gson();
		String result = gson.toJson(resultbean);
		result = StringUtils.deleteId(result);
		response.getWriter().write(result);
		response.flushBuffer();
	}

}
