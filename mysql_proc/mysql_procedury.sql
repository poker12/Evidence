DELIMITER //
create procedure returnProductCategoryHierarchy (IN enter_supercategory_id INT /*pro vstup z aplikace je NULL nebo 0**/, IN enter_lvl INT /*pro vstup z aplikace je -1**/, 
	INOUT level_list varchar(600), INOUT id_list varchar(1300), INOUT name_list varchar(8000))
begin
	DECLARE done INT DEFAULT FALSE;
	
	DECLARE enter_level INT;
    
    DECLARE v_id INT;
    DECLARE v_name varchar(30);
    
    DECLARE cur1 CURSOR FOR SELECT id, name FROM product_categories WHERE supercategory_id = enter_supercategory_id ORDER BY name ASC;
    
    DECLARE cur2 CURSOR FOR SELECT id, name FROM product_categories WHERE supercategory_id IS NULL ORDER BY name ASC;
	
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    SET max_sp_recursion_depth=255;
    SET enter_level = enter_lvl + 1;
    
    IF enter_supercategory_id > 0 THEN
		
		OPEN cur1;
		
		read_loop : LOOP
			FETCH cur1 INTO v_id, v_name;
			IF done THEN
				LEAVE read_loop;
			END IF;
			SET level_list = CONCAT(level_list, enter_level, ";");
			SET id_list = CONCAT(id_list, v_id, ";");
			SET name_list = CONCAT(name_list, v_name, ";");
			CALL returnProductCategoryHierarchy (v_id, enter_level, level_list, id_list, name_list);
		END LOOP;
		
		CLOSE cur1;
    
    ELSEIF enter_supercategory_id = 0 OR enter_supercategory_id IS NULL THEN
		
		
		OPEN cur2;
		
		read_loop : LOOP
			FETCH cur2 INTO v_id, v_name;
			IF done THEN
				LEAVE read_loop;
			END IF;
			SET level_list = CONCAT(level_list, enter_level, ";");
			SET id_list = CONCAT(id_list, v_id, ";");
			SET name_list = CONCAT(name_list, v_name, ";");
			CALL returnProductCategoryHierarchy (v_id, enter_level, level_list, id_list, name_list);
		END LOOP;
		
		CLOSE cur2;

	END IF;

end //
DELIMITER ;

drop procedure returnProductCategoryHierarchy;

delimiter //
create procedure test ()
begin
	declare level_list varchar(600); 
    declare id_list varchar(1300); 
    declare name_list varchar(8000);
    set level_list = "";
    set id_list = "";
    set name_list = "";
	call returnProductCategoryHierarchy(0, -1, level_list, id_list, name_list);
    select level_list, id_list, name_list;
end //
delimiter ;

drop procedure test;

call test();
