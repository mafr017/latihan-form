package com.mafr.latihan.form.repository;

import com.mafr.latihan.form.model.Lesson;
import com.mafr.latihan.form.model.Members;
import com.mafr.latihan.form.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MasterData {

    @Autowired
    private Sql2o sql2o;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Users checkUsers(Users users) {
        final String query = "SELECT id, username, password, islogin FROM users WHERE username = ? AND password = ?";
        return jdbcTemplate.queryForObject(query,
                (resultSet, rowNumber) -> {
                    Users users1 = new Users();
                    users1.setId(resultSet.getInt("id"));
                    users1.setUsername(resultSet.getString("username"));
                    users1.setPassword(resultSet.getString("password"));
                    users1.setIslogin(resultSet.getBoolean("islogin"));
                    return users1;
                }, users.getUsername(), users.getPassword());
    }

    public void updateIsLogin(Users users) {
        final String query;
        if (users.getIslogin() == true) {
            query = "UPDATE users SET islogin = ?, jam_login = now() WHERE username = ? AND password = ?";
        } else {
            query = "UPDATE users SET islogin = ?, jam_login = null WHERE username = ? AND password = ?";
        }
        jdbcTemplate.update(query, users.getIslogin(), users.getUsername(), users.getPassword());
        System.out.println("Update isLogin = " + users.getIslogin());
    }

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
                    members.setPhone(resultSet.getLong("phone"));
                    members.setEmail(resultSet.getString("email"));
                    members.setHadlesson(resultSet.getBoolean("hadlesson"));
                    return members; },
                cari);
    }

//    public List<Object> fetchMembersLeftJoinJDBC(String cari) {
//        List<Object> res = new ArrayList<>();
//        if (ObjectUtils.isEmpty(cari)) {
//            cari = "";
//        }
//        final String query = " SELECT * FROM members LEFT JOIN lesson ON members.memberid = lesson.memberid" +
//                " WHERE members.name LIKE CONCAT ('%', ?,'%');";
//        return jdbcTemplate.query(
//                query,
//                (resultSet, rowNumber) -> {
//                    Members members = new Members();
//                    members.setMemberid(resultSet.getInt("memberid"));
//                    members.setName(resultSet.getString("name"));
//                    members.setBirthday(resultSet.getDate("birthday"));
//                    members.setParentsname(resultSet.getString("parentsname"));
//                    members.setPhone(resultSet.getLong("phone"));
//                    members.setEmail(resultSet.getString("email"));
//                    members.setHadlesson(resultSet.getBoolean("hadlesson"));
//                    res.add(members);
//
//                    Lesson lesson = new Lesson();
//                    lesson.setMemberid(resultSet.getInt("memberid"));
//                    lesson.setGtrklasik(resultSet.getBoolean("gtrklasik"));
//                    lesson.setGtrpop(resultSet.getBoolean("gtrpop"));
//                    lesson.setGtrelektrik(resultSet.getBoolean("gtrelektrik"));
//                    lesson.setBasselektrik(resultSet.getBoolean("basselektrik"));
//                    lesson.setPianoklasik(resultSet.getBoolean("pianoklasik"));
//                    lesson.setPianopop(resultSet.getBoolean("pianopop"));
//                    lesson.setKeyboard(resultSet.getBoolean("keyboard"));
//                    lesson.setDrum(resultSet.getBoolean("drum"));
//                    lesson.setBiola(resultSet.getBoolean("biola"));
//                    lesson.setVocal(resultSet.getBoolean("vocal"));
//                    lesson.setTerapimusikautis(resultSet.getBoolean("terapimusikautis"));
//                    res.add(lesson);
//                    return res; },
//                cari);
//    }

    public List<Object> fetchMembersLeftJoinJDBC(String cari) {
        List<Object> res = new ArrayList<>();
        List<Object> memberArr = new ArrayList<>();
        List<Object> lessonArr = new ArrayList<>();
        if (ObjectUtils.isEmpty(cari)) {
            cari = "";
        }
        final String query = " SELECT * FROM members LEFT JOIN lesson ON members.memberid = lesson.memberid" +
                " WHERE members.name LIKE CONCAT ('%', ?,'%');";
        jdbcTemplate.query(
                query,
                (resultSet, rowNumber) -> {
                    Members members = new Members();
                    members.setMemberid(resultSet.getInt("memberid"));
                    members.setName(resultSet.getString("name"));
                    members.setBirthday(resultSet.getDate("birthday"));
                    members.setParentsname(resultSet.getString("parentsname"));
                    members.setPhone(resultSet.getLong("phone"));
                    members.setEmail(resultSet.getString("email"));
                    members.setHadlesson(resultSet.getBoolean("hadlesson"));
                    memberArr.add(members);

                    Lesson lesson = new Lesson();
                    lesson.setMemberid(resultSet.getInt("memberid"));
                    lesson.setGtrklasik(resultSet.getBoolean("gtrklasik"));
                    lesson.setGtrpop(resultSet.getBoolean("gtrpop"));
                    lesson.setGtrelektrik(resultSet.getBoolean("gtrelektrik"));
                    lesson.setBasselektrik(resultSet.getBoolean("basselektrik"));
                    lesson.setPianoklasik(resultSet.getBoolean("pianoklasik"));
                    lesson.setPianopop(resultSet.getBoolean("pianopop"));
                    lesson.setKeyboard(resultSet.getBoolean("keyboard"));
                    lesson.setDrum(resultSet.getBoolean("drum"));
                    lesson.setBiola(resultSet.getBoolean("biola"));
                    lesson.setVocal(resultSet.getBoolean("vocal"));
                    lesson.setTerapimusikautis(resultSet.getBoolean("terapimusikautis"));
                    lessonArr.add(lesson);
                    res.add(memberArr);
                    res.add(lessonArr);
                    return res;},
                cari);
        System.out.println(memberArr.toString());
        return res;
    }

}