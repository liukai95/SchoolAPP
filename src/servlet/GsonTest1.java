package servlet;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 用GSON解析单条Json数据
 *
 */
public class GsonTest1 {
	public static void main(String[] args) {
		String jsonData = "{'name':'John', 'age':20}";
		Person02 person = GsonUtil.parseJsonWithGson(jsonData, Person02.class);
		System.out.println(person.getName() + "," + person.getAge());

		String jsonData2 = "{'name':'John', 'age':20,'grade':{'course':'English','score':100,'level':'A'}}";
		Student student = GsonUtil.parseJsonWithGson(jsonData2, Student.class);
		System.out.println(student);

		// Json数组最外层要加"[]"
		String jsonData3 = "[{'name':'John', 'grade':[{'course':'English','score':100},{'course':'Math','score':78}]},{'name':'Tom', 'grade':[{'course':'English','score':86},{'course':'Math','score':90}]}]";
		List<Student> students = GsonUtil.parseJsonArrayWithGson(jsonData3, Student.class);
		System.out.println(students);
	}

}

/*
 * 封装的GSON解析工具类，提供泛型参数
 */
class GsonUtil {
	// 将Json数据解析成相应的映射对象
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	// 将Json数组解析成相应的映射对象列表
	public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
		}.getType());
		return result;
	}
}

class Person {
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}

class Student {
	private String name;
	private String age;

	private Grade grade;

	public class Grade { // 内部类要定义成public的
		private String course;
		private String score;
		private String level;

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		// 重写toString方法
		@Override
		public String toString() {
			return "Grade:[course = " + course + ", score = " + score + ", level = " + level + "]";
		}
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "Student:[name = " + name + ", age = " + age + ", grade = " + grade + "]";
	}
}
