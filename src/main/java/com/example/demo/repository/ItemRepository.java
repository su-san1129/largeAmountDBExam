package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	private final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		Integer condition = rs.getInt("condition");
		Integer categoryId = rs.getInt("category");
		String brand = rs.getString("brand");
		Double price = rs.getDouble("price");
		Integer shipping = rs.getInt("shipping");
		String description = rs.getString("description");
		Category category = null;
		if(categoryId != 0)
		  category = categoryRepository.load(categoryId);
		Item item = new Item(id, name, condition, categoryId, brand, price, shipping, description, category);
		return item;
	};
	
	public List<Item> findAll(){
		String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items ORDER BY price;";
		return template.query(sql, ITEM_ROW_MAPPER);
	}
	
	public Item load(Integer id) {
		try {
			String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items WHERE id = :id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Item> findByNameAndCategoryAndBrand(String name, Integer categoryId, String brand){
		String sql = "SELECT "
				     + "id"
				     + ", name"
				     + ", condition"
				     + ", category"
				     + ", brand"
				     + ", price"
				     + ", shipping"
				     + ", description "
				     + "FROM items "
				     + "WHERE name ILIKE :name "
				     + "AND category = :category "
				     + "AND brand ILIKE :brand "
				     + "ORDER BY price";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", "%"+name+"%")
				.addValue("category", categoryId)
				.addValue("brand", "%"+brand+"%");
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	
	public List<Item> findByNameAndBrand(String name, String brand){
		String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items WHERE brand ILIKE :brand AND name ILIKE :name ORDER BY price;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("brand", "%"+brand+"%").addValue("name", name);
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	
	public List<Item> findByNameAndParentCategoryAndBrand(String name, Integer parentId, String brand){
		String sql = "SELECT " 
				   + "id"
				   + ", name"
				   + ", condition"
				   + ", category"
				   + ", brand"
				   + ", price"
				   + ", shipping"
				   + ", description " 
				   + "FROM "
				   + "items "
				   + "WHERE "
				   + "name ILIKE :name "
				   + "AND "
				   + "brand ILIKE :brand "
				   + "AND "
				   + "category IN ("
				                 + "SELECT c3.id "
				                 + "FROM category c1 "
				                 + "JOIN category c2 "
				                 + "ON c1.id = c2.parent "
				                 + "JOIN category c3 "
				                 + "ON c2.id = c3.parent "
				                 + "WHERE c1.id = :id"
				                 + ") "
				   + "ORDER BY price "
				   + "LIMIT 30";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", parentId)
				.addValue("name", "%"+name+"%")
				.addValue("brand", "%"+brand+"%");
		return template.query(sql, param, ITEM_ROW_MAPPER);
		
	}
	public List<Item> findByNameAndChildCategoryAndBrand(String name, Integer parentId, String brand){
		String sql = "SELECT " 
				+ "id"
				+ ", name"
				+ ", condition"
				+ ", category"
				+ ", brand"
				+ ", price"
				+ ", shipping"
				+ ", description " 
				+ "FROM "
				+ "items "
				+ "WHERE "
				+ "name ILIKE :name "
				+ "AND "
				+ "brand ILIKE :brand "
				+ "AND "
				+ "category IN ("
				+ "SELECT c2.id "
				+ "FROM category c1 "
				+ "JOIN category c2 "
				+ "ON c1.id = c2.parent "
				+ "WHERE c1.id = :id"
				+ ") "
				+ "ORDER BY price "
				+ "LIMIT 30";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", parentId)
				.addValue("name", "%"+name+"%")
				.addValue("brand", "%"+brand+"%");
		return template.query(sql, param, ITEM_ROW_MAPPER);
		
	}
	
	/**
	 * ページネーション.
	 * 
	 * @param viewSize 表示する件数
	 * @param pageIndex 表示するページナンバー
	 * @return 表示するアイテム
	 */
	public List<Item> findByPage(Integer viewSize, Integer pageIndex){
		String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items ORDER BY id LIMIT :viewSize OFFSET :pageIndex;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("viewSize", viewSize).addValue("pageIndex", pageIndex);
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	
	public Integer itemCount() {
		String sql = "SELECT COUNT(*) FROM items;";
		return template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}
	

}
