package com.mafr.latihan.form.ui;

import com.mafr.latihan.form.model.Members;
import com.mafr.latihan.form.repository.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeAction {

    @Autowired
    private MasterData masterData;

    @GetMapping("api/listmemberjson")
    public ResponseEntity<List<Members>> daftarMemberJson(@RequestParam("nama") String nama) {
        return ResponseEntity.ok(masterData);
    }

}
