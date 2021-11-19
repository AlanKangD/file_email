package com.care.root.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper mapper;
	

	@Override
	public void fileProcess(MultipartHttpServletRequest mul) {
		ShoesDTO dto = new ShoesDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		MultipartFile file = mul.getFile("file");
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar calendar = Calendar.getInstance();
		
		
		
		
		if(file.getSize() != 0) { //같은 의미로 if( !file.isEmpty() ) 입니다.
			String sysFileName = fo.format(calendar.getTime());
			sysFileName += file.getOriginalFilename();
			
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName); //파일의 최종 경로라고 생각하면됩니다.
			
			dto.setImgName(sysFileName);
			
			try {
				file.transferTo(saveFile); //파일을 저장하는 명령문
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}else {
			dto.setImgName("nan");
		}
		mapper.saveData(dto);
	}


	@Override
	public void getData(Model model) {
		model.addAttribute("list", mapper.getData());
		
	}

}
