package com.alimu.probe.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数处理工具类
 * 
 * @author Administrator
 * 
 */

public class PramUtil {
	public static boolean isBlank(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	public static boolean isNumber(String value) {
		if (isBlank(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(value);
		boolean flag = matcher.matches();
		if (flag) {
			return true;
		}
		return false;
	}

	public static boolean isDouble(String value) {
		if (isBlank(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("\\d+[\\.]{0,1}[\\d]*+");
		Matcher matcher = pattern.matcher(value);
		boolean flag = matcher.matches();
		if (flag) {
			return true;
		}
		return false;
	}

	public static boolean isDate(String value) {
		if (isBlank(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
		Matcher matcher = pattern.matcher(value);
		boolean flag = matcher.matches();
		if (flag) {
			return true;
		}
		return false;
	}

	public static boolean isDateTime(String value) {
		if (isBlank(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
		boolean flag = pattern.matcher(value).matches();
		if (flag) {
			return true;
		}
		return false;
	}

	public static Date formatDate(String value) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = dateFormat.parse(value);
		} catch (Exception e) {
			System.out.println("时间格式出错");
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date formatDate1(String value) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			date = dateFormat.parse(value);
		} catch (Exception e) {
			System.out.println("时间格式出错");
			e.printStackTrace();
		}
		return date;
	}
	
	public static String formatDate(Date date) {
		String dateStr = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = dateFormat.format(date);
		} catch (Exception e) {
			System.out.println("时间格式出错");
			e.printStackTrace();
		}
		return dateStr;
	}
	
	public static String formatDate1(Date date) {
		String dateStr = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			dateStr = dateFormat.format(date);
		} catch (Exception e) {
			System.out.println("时间格式出错");
			e.printStackTrace();
		}
		return dateStr;
	}

	public static Date formatDate(String value, String format) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(value);
		} catch (Exception e) {
			System.out.println("时间格式出错");
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 去除重复
	 * @param list
	 * @return
	 */
	public static List<Object> removeRepetitionForObject(List<Object> list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}
	public static List<Long> removeRepetitionForLong(List<Long> list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}
	
	/**
	 * 返回开始下标，分页有用
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static Integer toStartIndex(Integer pageNo, Integer pageSize) {
		Integer startIndex = 0;
		if (pageNo == null) {
			startIndex = 0;
		} else {
			startIndex = (pageNo - 1) * pageSize;
		}
		return startIndex;
	}
	
	/**
	 * 判断此链接是否可用
	 * @param url
	 * @return
	 */
	public static boolean getUrl(String url) {
		try {
			URL urlStr = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();
			int state = connection.getResponseCode();
			if (state == 200) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static List<Long> strToList(String str, String split){
		List<Long> nums = new ArrayList<Long>();
		String[] strs = str.split(split);
		for (String string : strs) {
			nums.add(Long.parseLong(string));
		}
		return nums;
	}
	
	public static String ranNumAndStr(int l) {
		Random r = new Random();
		char[] str="0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] rstr=new char[l];
		for(int i=0;i<l;i++) {
			rstr[i]=str[r.nextInt(72)];
		}		
		return new String(rstr);
	}
	
	/**
	 * 获取空的MAP
	 * @param values
	 * @return
	 */
	public static List<String> getPrams(Map<String, Object> values) {
		List<String> list = new ArrayList<String>();
		if (values != null) {
			if (!values.isEmpty()) {
				Set<String> keys = values.keySet();
				for (String paramKey : keys) {
					Object paramVal = values.get(paramKey);
					if(paramVal == null) {
						list.add(paramKey);
					}
				}
			}
		}
		return list;
	}
	
	public static boolean isPhone(String value) {
		if (isBlank(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}");
		Matcher matcher = pattern.matcher(value);
		boolean flag = matcher.matches();
		if (flag) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将集合中需要含有下划线的key值改为驼峰标准的key值
	 * @param list	需要转换的集合
	 * @return 
	 * @author 黄进昌
	 * @date on 2017年2月21日
	 */
	public static List<Map<String, Object>> changeListFrom(List<Map<String, Object>> list){
		if(list == null || list.isEmpty()){
			return list;
		}
		StringBuffer tempKey = new StringBuffer();
		Set<String> tempSet = new HashSet<String>();
		//遍历list
		for(Map<String, Object> map : list){
			//遍历map
			tempSet.clear();
			//拷贝一份keySet，防止出错
			for(String key : map.keySet()){
				tempSet.add(key);
			}
			for(String key :tempSet){
				if(key.contains("_")){
					tempKey.setLength(0);
					Object value = map.get(key);
					//把带“_”的key转换符合驼峰标准的key值
					String[] strs = key.split("_");
					for (int i = 0; i < strs.length; i++) {	
						tempKey.append(strs[i].toLowerCase());
					}
					//重新将map中key的可以转换为新的key
					map.put(tempKey.toString(), value.toString());
					map.remove(key);
				}else{
					tempKey.setLength(0);
					Object value = map.get(key);
					tempKey.append(key.toLowerCase());
					map.put(tempKey.toString(), value.toString());
				}
			}
		}
		return list;
	}

	public static Map<String, Object> changeObjectString(Map<String, Object> conf) {
		if (conf != null && !conf.isEmpty()) {
			for(String key : conf.keySet()){
				conf.put(key, conf.get(key).toString());
			}
		}
		return conf;
	}
	
}
