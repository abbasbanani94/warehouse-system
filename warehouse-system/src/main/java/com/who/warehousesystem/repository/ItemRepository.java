package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query(value = "select * from items where name = :name and min_temp = :minTemp and max_temp = :maxTemp " +
            "and description = :description", nativeQuery = true)
    Optional<Item> checkItemExistence(@Param(value = "name") String name,
                                      @Param(value = "minTemp") BigDecimal minTemp,
                                      @Param(value = "maxTemp") BigDecimal maxTemp,
                                      @Param(value = "description") String description);

    @Query(value = "select * from items where active = 1 order by item_id desc", nativeQuery = true)
    Optional<List<Item>> findAllItems();

    @Query(value = "select * from items where item_id = :id and active = 1", nativeQuery = true)
    Optional<Item> findItemById(@Param(value = "id") Integer id);

    @Query(value = "select * from items where name = :name and min_temp = :minTemp and max_temp = :maxTemp and " +
            "description = :description", nativeQuery = true)
    Item findItemByDetails(@Param(value = "name") String itemName,
                                     @Param(value = "minTemp") BigDecimal minTemp,
                                     @Param(value = "maxTemp") BigDecimal maxTemp,
                                     @Param(value = "description") String itemDescription);
}
