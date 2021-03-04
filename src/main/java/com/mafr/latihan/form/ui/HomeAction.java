package com.mafr.latihan.form.ui;

import com.mafr.latihan.form.model.Lesson;
import com.mafr.latihan.form.model.Members;
import com.mafr.latihan.form.model.Users;
import com.mafr.latihan.form.repository.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<Map> fetchMembersLeftJoinJDBC(@RequestParam(required = false, name = "cari") String cari) {
        Map<String, Object> result = new HashMap<>();
        result.put("members", masterData.fetchMembersLeftJoinJDBC(cari).get(0));
        result.put("lesson", masterData.fetchMembersLeftJoinJDBC(cari).get(1));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/addmemberjson")
    public ResponseEntity<DoubleBody> saveproductjson(@RequestBody DoubleBody dbBy) {
        System.out.println(dbBy.lesson.getMemberid() + " : " + dbBy.members.getName() + " : " + dbBy.members.getBirthday());
        masterData.insertMemberJdbc(dbBy.members, dbBy.lesson);
        return ResponseEntity.ok(dbBy);
    }

}
