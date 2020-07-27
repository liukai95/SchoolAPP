package comment;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.CommentBean;
import model.beans.JobInfoBean;
import model.beans.ResultBean;
import model.utils.StringUtils;

/**
 * 对某一条信息增加评论
 * Servlet implementation class AddCommentServlet
 */
@WebServlet(description = "增加三类评论信息", urlPatterns = { "/addComment" })
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentServlet() {
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
		int type = Integer.parseInt(request.getParameter("type"));
		ResultBean resultbean = new ResultBean();
		if(type>=0 &&type<=2){
			CommentBean comm = new CommentBean();
			comm.setUser_iduser(Long.parseLong(request.getParameter("userid")));
			comm.setPost_id(Long.parseLong(request.getParameter("postid")));
			comm.setType(type);
			comm.setContent(request.getParameter("content"));
			
			int id=comm.insert();
			if (id != 0) {
				resultbean.setCode(ResultBean.SUCCESS);
				resultbean.setId(id);
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}
		
		} else {
			resultbean.setCode(ResultBean.PARAMETERERROR);
		}
		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson = new Gson();
		String result = gson.toJson(resultbean);
		result = StringUtils.deleteMessage(result);
		response.getWriter().write(result);
		response.flushBuffer();
	}

}
