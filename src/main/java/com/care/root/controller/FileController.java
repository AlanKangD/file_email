package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.service.FileService;

@Controller
public class FileController {
	@Autowired FileService fs;
	
	@GetMapping("form")
	public String form() {
		
		return "uploadForm";
	}
	
	@PostMapping("upload")
	public String upload(MultipartHttpServletRequest mul) {
		//그리고 값을 받아올때 MultipartHttpServletRequest mul을 써줘야 값을 빼오고 넣을수 있습니다. 
		/*
		System.out.println(mul.getParameter("id"));
		System.out.println(mul.getParameter("name"));
		
		MultipartFile file = mul.getFile("file");
		System.out.println(file.getOriginalFilename());
		//이렇게 해줘야 파일을 꺼낼 수 있고 확인할 수 있습니다. 
		 * 주석은 디버깅 용입니다.
		*/
		fs.fileProcess(mul);
		
		
		return "redirect:form";
	}
	@GetMapping("views")
	public String views(Model model) {
		fs.getData(model);
		
		return "result";
	}
	@GetMapping("download")
	public String download(@RequestParam("file") String fileName,
							HttpServletResponse response) throws IOException{
		//Content-disposition 은 파일로 응답하겠다라는 의미입니다.
		// attachment : 웹 브라우저 파일로 표현하겠다라는 의미
		// fileName : 다운로드할 이름
		response.addHeader("Content-disposition", 
				"attachment; fileName="+fileName);
		File file = new File(FileService.IMAGE_REPO+"/"+fileName);
		//상단의 코드는 이름과 같은 이미지를 가지고 와라 라는 의미 입니다. 
		FileInputStream fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, response.getOutputStream());
		//상단 의 코드는 파일 입출력을 해주겠다라는 의미입니다.
		
		
		return "";
	}
	
}
