package com.example.demo.repository;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

@Repository
public class CategoryRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		Integer parent = rs.getInt("parent");
		String name = rs.getString("name");
		String nameAll = rs.getString("name_all");
		Category parentCategory = null;
		if(parent != 0)
			parentCategory = load(parent);
		Category category = new Category(id, parent, name, nameAll, parentCategory);
		return category;
	};
	
	public Category load(Integer id) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
	}
	
	public List<Category> findByParent(Integer parent){
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent = :parent ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parent", parent);
		return template.query(sql, param, CATEGORY_ROW_MAPPER);
	}
	
	public List<Category> parentCategoryAll(){
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent is NULL ORDER BY id";
		return template.query(sql, CATEGORY_ROW_MAPPER);
	}
	

}
