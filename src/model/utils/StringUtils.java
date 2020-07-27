package model.utils;

public class StringUtils {

	/**
	 * 保证json格式整洁，所以删除一些不用的字段，包括message和id字段，分别用于不同的情况
	 * @param src
	 * @return
	 */
	public static String deleteMessage(String src){
		return src.replaceFirst(",\"MessageNum\":0,\"Message\":\\[\\]", "");
	}
	public static String deleteId(String src){
		return src.replaceFirst("\"id\":([0-9][0-9]*),", "");
		
	}
}
