package info;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.CommentBean;
import model.beans.JobInfoBean;
import model.beans.LostAndFoundBean;
import model.beans.PostgraduateBean;
import model.beans.ResultBean;
import model.utils.StringUtils;

/**
 * Servlet implementation class InquireAllInfoServlet
 */
@WebServlet(description = "查询三类信息中某一类的所有信息", urlPatterns = { "/inquireAllInfo" })
public class InquireAllInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InquireAllInfoServlet() {
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
		if (type == 0) {// 招聘类信息
			JobInfoBean job = new JobInfoBean();
			List<JobInfoBean> list = job.listJob();
			if (list != null && list.size()>0) {
				resultbean.setCode(ResultBean.SUCCESS);
				for (JobInfoBean j : list) {
					resultbean.addResult(j);
				}
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}
		} else if (type == 1) {//失物招领信息
			LostAndFoundBean lost = new LostAndFoundBean();
			List<LostAndFoundBean> list = lost.listLostInfo();
			if (list != null && list.size()>0) {
				resultbean.setCode(ResultBean.SUCCESS);
				for (LostAndFoundBean l : list) {
					resultbean.addResult(l);
				}
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}
		} else if (type == 2) {//考研信息模块
			PostgraduateBean post = new PostgraduateBean();
			List<PostgraduateBean> list = post.listPostInfo();
			if (list != null && list.size()>0) {
				resultbean.setCode(ResultBean.SUCCESS);
				for (PostgraduateBean p : list) {
					resultbean.addResult(p);
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
