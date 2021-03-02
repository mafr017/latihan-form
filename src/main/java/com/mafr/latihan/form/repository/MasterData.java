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
                    members.setName(resultSet.getString("name"));
                    members.setBirthday(resultSet.getDate("birthday"));
                    members.setParentsname(resultSet.getString("parentsname"));
                    members.setPhone(resultSet.getInt("phone"));
                    members.setEmail(resultSet.getString("email"));
                    members.setHadlesson(resultSet.getBoolean("hadlesson"));
                    return members; },
                cari);
    }

}