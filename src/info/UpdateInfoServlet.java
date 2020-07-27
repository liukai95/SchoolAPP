package info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.JobInfoBean;
import model.beans.LostAndFoundBean;
import model.beans.PostgraduateBean;
import model.beans.ResultBean;
import model.utils.StringUtils;

/**
 * Servlet implementation class UpdateInfoServlet
 */
@WebServlet(description = "修改某用户发布过得信息", urlPatterns = { "/updateInfo" })
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateInfoServlet() {
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
		long userid = Long.parseLong(request.getParameter("userid"));
		long postid = Long.parseLong(request.getParameter("postid"));// 帖子ID
		boolean flag = true;
		ResultBean resultbean = new ResultBean();
		if (type == 0) {// 修改招聘类信息
			JobInfoBean job = new JobInfoBean();
			job.setUser_iduser(userid);
			job.setIdjobinfo(postid);
			if (job.isFlagById()) {
				String post = request.getParameter("post");// 职位
				String company = request.getParameter("company");// 招聘公司
				String money = request.getParameter("money");// 薪资
				String workingplace = request.getParameter("workingplace");// 工作地点
				String jobcontent = request.getParameter("jobcontent");// 工作职责
				String workhour = request.getParameter("workhour");// 工作时长

				if (post != null && flag) {
					job.setPost(post);
					flag = job.updatePost();
				}
				if (company != null && flag) {
					job.setCompany(company);
					flag = job.updateCompany();
				}
				if (money != null && flag) {
					job.setMoney(money);
					job.updateMoney();
				}
				if (workingplace != null && flag) {
					job.setWorkingplace(workingplace);
					job.updateWorkingplace();
				}
				if (jobcontent != null && flag) {
					job.setJobcontent(jobcontent);
					job.updateJobcontent();
				}
				if (workhour != null) {
					job.setWorkhour(workhour);
					job.updateWorkhour();
				}
				if (post == null && company == null && money == null && workingplace == null && jobcontent == null
						&& workhour == null)
					flag = false;

				if (flag) {
					resultbean.setCode(ResultBean.SUCCESS);
				} else {
					resultbean.setCode(ResultBean.FAILED);
				}
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}

		} else if (type == 1) {// 失物招领信息
			LostAndFoundBean lost = new LostAndFoundBean();
			lost.setUser_iduser(userid);
			lost.setIdLostandFound(postid);
			if (lost.isFlagById()) {
				int school_idschool = Integer.parseInt(request.getParameter("school_idschool"));// 学校
				String itemtype = request.getParameter("itemtype");// 失物类型
				String description = request.getParameter("description");// 描述
				String contactinformation = request.getParameter("contactinformation");// 联系方式
				String pic = request.getParameter("pic");// 图片

				if (String.valueOf(school_idschool) != null && flag) {
					lost.setSchool_idschool(school_idschool);
					flag = lost.updateSchool();
				}
				if (itemtype != null && flag) {
					lost.setItemtype(itemtype);
					flag = lost.updateIitemtype();
				}
				if (description != null && flag) {
					lost.setDescription(description);
					lost.updateDescription();
				}
				if (contactinformation != null && flag) {
					lost.setContactinformation(contactinformation);
					lost.updateContactin();
				}
				if (pic != null && flag) {
					lost.setPic(pic);
					lost.updatePic();
				}
				if (String.valueOf(school_idschool) == null && itemtype == null && description == null
						&& contactinformation == null && pic == null)
					flag = false;

				if (flag) {
					resultbean.setCode(ResultBean.SUCCESS);
				} else {
					resultbean.setCode(ResultBean.FAILED);
				}
			} else {
				resultbean.setCode(ResultBean.FAILED);
			}
		} else if (type == 2) {// 考研信息模块
			PostgraduateBean post = new PostgraduateBean();
			post.setUser_iduser(userid);
			post.setIdpostgraduate(postid);
			String content = request.getParameter("content");// 内容
			String pic = request.getParameter("pic");// 图片
			if (content != null && flag) {
				post.setContent(content);
				post.updateContent();
			}
			if (pic != null && flag) {
				post.setPic(pic);
				post.updatePic();
			}
		} else {
			resultbean.setCode(ResultBean.PARAMETERERROR);
		}
		resultbean.setMessageNum(resultbean.getMessage().size());
		Gson gson = new Gson();
		String result = gson.toJson(resultbean);
		result = StringUtils.deleteId(result);
		result = StringUtils.deleteMessage(result);
		// 没有查询成功，只返回一个code
		if (resultbean.getCode() != 1) {
			result = StringUtils.deleteMessage(result);
		}

		response.getWriter().write(result);
		response.flushBuffer();
	}

}
