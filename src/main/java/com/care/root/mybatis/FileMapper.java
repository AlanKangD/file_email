package com.care.root.mybatis;

import java.util.List;

import com.care.root.dto.ShoesDTO;

public interface FileMapper {
	public void saveData(ShoesDTO dto); //이미지 저장
	public List<ShoesDTO> getData(); //값을 가져옴
}
