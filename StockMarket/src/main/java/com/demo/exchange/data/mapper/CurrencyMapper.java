package com.demo.exchange.data.mapper;

import com.demo.exchange.data.model.Currency;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CurrencyMapper {

    @Select("SELECT * FROM market")
    List<Currency> findAll();
    
    @Select("SELECT * FROM market WHERE currencyId = #{id}")
    Optional<Currency> findById(final Integer id);
    
    @Update("UPDATE market set sale=#{sale}, buy=#{buy} WHERE currencyId=#{currencyId}")
    void update(Currency curr);
    
}
