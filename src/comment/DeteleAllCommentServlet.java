package comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.CommentBean;
import model.beans.ResultBean;
import model.utils.StringUtils;

/**
 * Servlet implementation class DeteleAllCommentServlet
 */
@WebServlet(description = "删除一个用户的已经评论过的所有评论", urlPatterns = { "/deleteComment" })
public class DeteleAllCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeteleAllCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 参数编码
		response.setContentType("application/json;charset=utf-8");// 返回内容编码，json格式
		ResultBean resultbean = new ResultBean();
		CommentBean comm = new CommentBean();
		comm.setUser_iduser(Long.parseLong(request.getParameter("userid")));
		if (comm.delete()) {
			resultbean.setCode(ResultBean.SUCCESS);
		} else {
			resultbean.setCode(ResultBean.FAILED);
		}
		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson = new Gson();
		String result = gson.toJson(resultbean);
		result = StringUtils.deleteId(result);
		result = StringUtils.deleteMessage(result);
		
		response.getWriter().write(result);
		response.flushBuffer();
	}

}
