package com.mafr.latihan.form.repository;

import com.mafr.latihan.form.model.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class MasterData {

    @Autowired
    private Sql2o sql2oo;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Members> fetchMembersJDBC(String cari) {
        if (ObjectUtils.isEmpty(cari)) {
            cari = "";
        }
        final String query = "SELECT * FROM members WHERE name LIKE CONCAT ('%',?,'%');";
        return jdbcTemplate.query(
                query,
                (resultSet, rowNumber) -> {
                    Members members = new Members();
                    members.setMemberid(resultSet.getInt("memberid"));
                    members.setProductName(resultSet.getString("name"));
                    members.setSupplierId(resultSet.getInt("birthday"));
                    members.setCategoryId(resultSet.getInt("parentsname"));
                    members.setQuantityperUnit(resultSet.getString("phonephone"));
                    members.setUnitPrice(resultSet.getInt("email"));
                    members.setUnitsInStock(resultSet.getInt("hadlesson"));
                    return members; },
                cari);
    }

}
