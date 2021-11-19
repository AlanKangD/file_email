package com.care.root.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	public static final String IMAGE_REPO = "C:/Users/samsung/Desktop/Spring/image_test"; 
	//파일 저장소라고 생각하면 됩니다. 원하는 경로를 지정해줍니다. 이 위치에다가 file을 관리하겠다 
	public void fileProcess(MultipartHttpServletRequest mul); // 파일 업로드 로직
	public void getData(Model model); //값을 가져오는 로직

}
