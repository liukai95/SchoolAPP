package comment;

import java.io.IOException;
import java.util.List;

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
 * 列出某一条下的所有评论 Servlet implementation class ListAllCommentServlet
 */
@WebServlet(description = "列出某一条下的所有评论", urlPatterns = { "/listComment" })
public class ListAllCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAllCommentServlet() {
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
		System.out.println(type);
		ResultBean resultbean = new ResultBean();

		if (type >= 0 && type <= 2) {
			CommentBean comm = new CommentBean();
			comm.setPost_id(Long.parseLong(request.getParameter("postid")));
			comm.setType(type);
			List<CommentBean> list = comm.listCom();// 得到所有评论信息
			if (list != null && list.size()>0) {
				resultbean.setCode(ResultBean.SUCCESS);
				for (CommentBean cb : list) {
					resultbean.addResult(cb);// 添加
				}
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}

		} else {
			resultbean.setCode(ResultBean.PARAMETERERROR);
		}
		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson = new Gson();
		String result = gson.toJson(resultbean);
		result = StringUtils.deleteId(result);
		//没有查询成功，只返回一个code
		if(resultbean.getCode()!=1){
			result = StringUtils.deleteMessage(result);
		}
		response.getWriter().write(result);
		response.flushBuffer();
	}

}
