package com.fsoft.team.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.team.entity.CategoryEntity;
import com.fsoft.team.repository.CategoryRepository;
import com.fsoft.team.service.CategoryService;
@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> getAll() {
		return categoryRepository.findAll();
	}

}
