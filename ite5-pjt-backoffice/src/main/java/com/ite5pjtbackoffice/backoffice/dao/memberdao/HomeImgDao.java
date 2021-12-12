package com.ite5pjtbackoffice.backoffice.dao.memberdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.vo.HomeImg;

@Mapper
public interface HomeImgDao {
	public List<HomeImg> selectHomeImg();
}
