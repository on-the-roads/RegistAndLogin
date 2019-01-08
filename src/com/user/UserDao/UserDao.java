package com.user.UserDao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.user.UserBean.User;

/**
 * 数据处理类
 * 功能：
 *  1.查询
	2.添加
 */

/* document对象在内存中是以"UTF-8"编码形式存在，用FileWriter将document对象以字符流的形式写入xml文档默认是用本地码表"gb2312"编码
 * 乱码问题总结：
 * 1.用字符流向文件写入数据要考虑乱码问题，而用字节流就不必考虑乱码问题
 *   用字符流向文件写入数据默认使用本地码表即"gb2312"
 * 2.任何对象读入内存都是以"UTF-8"编码的形式存在
 * 默认情况下XMLWriter的write方法是以"UTF-8"的编码形式将内存中的document对象传给FileWriter，所以要想不发生乱码问题，
 * 就要使用包装流OutputStreamWriter并给定写入文件时所使用的编码表，或者使用OutputFormat的setEncoding方法指定传给
 * 流对象时所使用的编码格式。
 * */
public class UserDao {
	private  final String path="G:/works/eclipse/LoginAndRegist/User.xml";
	
	public  User UserFindByName(String username) {
		
		//1.获得document
		SAXReader reader=new SAXReader();
		Document doc=null;
		try {
//		 doc=reader.read(new InputStreamReader(new FileInputStream(new File(path))),"UTF-8");
			doc=reader.read(path);
		//2.利用xpath获得element
		Element element=(Element) doc.selectSingleNode("//user[@username='"+username+"']");
		
		if(element==null){
			return null;
		}
		else {
			User user=new User();
			user.setUsername(element.attributeValue("username"));
			user.setPassword(element.attributeValue("password"));
			return user;
		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public  void add(User user)  {
		//1.获得document
		SAXReader reader=new SAXReader();
		Document doc=null;
		try {
			doc = reader.read(path);
			//2.得到根标签
			Element eleUsers= doc.getRootElement();
			
			Element eleUser=eleUsers.addElement("user");
			eleUser.addAttribute("username", user.getUsername());
			eleUser.addAttribute("password", user.getPassword());
			
			// 创建格式化器，使用\t缩进，添加换行
			OutputFormat format = new OutputFormat("\t", true);
			// 清空数据中原有的换行
			format.setTrimText(true);
			// 创建XML输出流对象
//			XMLWriter writer = new XMLWriter(new FileWriter(path), format);
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)), format);
			// 输出Document
			writer.write(doc);
			// 关闭流
			writer.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}



	}
}

