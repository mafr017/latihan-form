package com.mafr.latihan.form.ui;

import com.mafr.latihan.form.model.Lesson;
import com.mafr.latihan.form.model.Members;
import com.mafr.latihan.form.model.Users;
import com.mafr.latihan.form.repository.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DoubleBody {
    public Members members;
    public Lesson lesson;
}

@Controller
public class HomeAction {

    @Autowired
    private MasterData masterData;

    @PostMapping("api/loginjson")
    public ResponseEntity<Map> loginjson(@Valid @RequestBody Users users, BindingResult bindingResult) {
        Map<String, Object> status = new HashMap<>();
        if (bindingResult.hasErrors()) {
            status.put("status", false);
            return ResponseEntity.ok(status);
        } else {
            masterData.updateIsLogin(users);
            Users newUser = masterData.checkUsers(users);
//            System.out.println("id " + newUser.getId() + " username " + newUser.getUsername() +
//                    " password " + newUser.getPassword() + " islogin " + newUser.getIslogin());
            if (newUser.getIslogin() == true) {
                status.put("status", true);
                status.put("user", newUser);
                System.out.println("BERHASIL");
            } else {
                status.put("status", false);
                status.put("user", "fail");
                System.out.println("GAGAL");
            }
            return ResponseEntity.ok(status);
        }
    }

    @PostMapping("api/logoutjson")
    public ResponseEntity<String> logoutjson(@Valid @RequestBody Users users) {
        masterData.updateIsLogin(users);
        return ResponseEntity.ok("Logout");
    }

    @GetMapping("api/listmemberjson")
    public ResponseEntity<Map> listmemberjson(@RequestParam(required = false, name = "cari") String cari) {
        Map<String, Object> result = new HashMap<>();
        result.put("members", masterData.fetchMembersLeftJoinJDBC(cari).get(0));
        result.put("lesson", masterData.fetchMembersLeftJoinJDBC(cari).get(1));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/datamemberjson/{id}")
    public ResponseEntity<Map> datamemberjson(@PathVariable(required = false, name = "id") int id) {
        Map<String, Object> result = new HashMap<>();
        result.put("members", masterData.fetchMemberByIdJDBC(id).get(0));
        result.put("lesson", masterData.fetchMemberByIdJDBC(id).get(1));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/addmemberjson")
    public ResponseEntity<Lesson> addmemberjson(@RequestBody DoubleBody dbBy) {
        System.out.println(dbBy.lesson.getMemberid() + " : " + dbBy.members.getName() + " : " + dbBy.members.getBirthday());
        masterData.insertMemberJdbc(dbBy.members, dbBy.lesson);
        return ResponseEntity.ok(dbBy.lesson);
    }

    @PostMapping("/api/updatememberjson")
    public ResponseEntity<Lesson> updatememberjson(@RequestBody DoubleBody dbBy) {
        System.out.println(dbBy.lesson.getMemberid() + " : " + dbBy.members.getName() + " : " + dbBy.members.getBirthday());
        masterData.updateMemberJdbc(dbBy.members, dbBy.lesson);
        return ResponseEntity.ok(dbBy.lesson);
    }

    @DeleteMapping("/api/deletememberjson/{id}")
    public ResponseEntity<Map> deletememberjson(@PathVariable("id") int id) {
        System.out.println("Proses delete " + id);
        masterData.deleteMemberJdbc(id);
        String cari = "";
        Map<String, Object> result = new HashMap<>();
        result.put("members", masterData.fetchMembersLeftJoinJDBC(cari).get(0));
        result.put("lesson", masterData.fetchMembersLeftJoinJDBC(cari).get(1));
        return ResponseEntity.ok(result);
    }

}
