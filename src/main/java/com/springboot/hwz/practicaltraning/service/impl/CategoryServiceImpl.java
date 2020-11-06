package com.springboot.hwz.practicaltraning.service.impl;

import com.springboot.hwz.practicaltraning.common.E3Result;
import com.springboot.hwz.practicaltraning.entity.Category;
import com.springboot.hwz.practicaltraning.repository.CategoryRepository;
import com.springboot.hwz.practicaltraning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public E3Result GetAllCategory()
    {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        // 执行查询
        List<Category> list = CategoryMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return E3Result.build(400, "获取分类标签错误");
        }
        return E3Result.ok(list);

    }
}
