package com.example.web.stu;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.pojo.User;
import com.example.service.UserService;

@RequestMapping("basic")
@Controller
public class BasicWeb {
	@Autowired
	private UserService userSer;

	@RequestMapping("pswPage")
	public String pswPage() {
		return "stu/basic/pass";
	}

	@RequestMapping("dopsw")
	public String dopsw(HttpSession session, Model model, String mpass, String newpass) {
		User user = (User) session.getAttribute("user");
		userSer.dopsw(user, model, mpass, newpass);
		return "stu/basic/tips";
	}

	@RequestMapping("info")
	public String info(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "stu/basic/info";
	}

	@RequestMapping("avatarPage")
	public String avatarPage() {
		return "stu/basic/avatar";
	}

	@RequestMapping("upAvatar")
	public String upAvatar(MultipartFile fileUpload, HttpSession session) {
		// 获取文件名
		String fileName = fileUpload.getOriginalFilename();
		if (fileName == null) {
			return "stu/basic/avatar";
		}
		// 获取文件后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 重新生成文件名
		fileName = UUID.randomUUID() + suffixName;
		// 指定本地文件夹存储图片
		String filePath = "C:\\Users\\JAYNES\\Documents\\旧项目\\Examination\\src\\main\\resources\\static\\images\\";
		try {
			// 将图片保存到static文件夹里
			fileUpload.transferTo(new File(filePath + fileName));
			// 更新数据库中图片信息
			User user = (User) session.getAttribute("user");
			user.setAvatar("images/"+fileName);
			userSer.upAvatar(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "stu/basic/avatar";
	}

}
