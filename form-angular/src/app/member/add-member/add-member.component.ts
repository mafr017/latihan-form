import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Lesson } from 'src/app/model/lesson';
import { Members } from 'src/app/model/members';
import { MemberService } from '../member.service';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  providers: [MemberService]
})

export class AddMemberComponent implements OnInit {

  memberForm!: FormGroup;

  myVar2 = false;
  tessss = "";

  constructor(private memberService: MemberService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.memberForm = new FormGroup({
      memberid: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.pattern(/^-?(0|[0-9]\d*)?$/)]),
      name: new FormControl(null, [Validators.required]),
      birthday: new FormControl(null, [Validators.required]),
      parentsname: new FormControl(null, [Validators.required]),
      phone: new FormControl(null, [Validators.required, Validators.minLength(10), Validators.pattern(/^-?(0|[0-9]\d*)?$/)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      hadlesson: new FormControl(false),
      //had a lesson checkbox group
      gtrklasik: new FormControl(false), gtrpop: new FormControl(false), gtrelektrik: new FormControl(false),
      basselektrik: new FormControl(false), pianoklasik: new FormControl(false), pianopop: new FormControl(false),
      keyboard: new FormControl(false), drum: new FormControl(false), biola: new FormControl(false),
      vocal: new FormControl(false), terapimusikautis: new FormControl(false)
    });
  }

  ngOnInit(): void {
  }

  simpan() {
    if (this.memberForm?.valid) {
      const members = new Members();
      members.memberid = this.memberForm?.controls.memberid.value;
      members.name = this.memberForm?.controls.name.value;
      members.birthday = this.memberForm?.controls.birthday.value;
      members.parentsname = this.memberForm?.controls.parentsname.value;
      members.phone = this.memberForm?.controls.phone.value;
      members.email = this.memberForm?.controls.email.value;
      members.hadlesson = this.memberForm?.controls.hadlesson.value;
      // console.log(members.name);
      
      const lesson = new Lesson();
      lesson.memberid = this.memberForm?.controls.memberid.value;
      lesson.gtrklasik = this.memberForm?.controls.gtrklasik.value;
      lesson.gtrpop = this.memberForm?.controls.gtrpop.value;
      lesson.gtrelektrik = this.memberForm?.controls.gtrelektrik.value;
      lesson.basselektrik = this.memberForm?.controls.basselektrik.value;
      lesson.pianoklasik = this.memberForm?.controls.pianoklasik.value;
      lesson.pianopop = this.memberForm?.controls.pianopop.value;
      lesson.keyboard = this.memberForm?.controls.keyboard.value;
      lesson.drum = this.memberForm?.controls.drum.value;
      lesson.biola = this.memberForm?.controls.biola.value;
      lesson.vocal = this.memberForm?.controls.vocal.value;
      lesson.terapimusikautis = this.memberForm?.controls.terapimusikautis.value;
      // console.log(lesson.gtrklasik);
      this.memberService.addMember(members, lesson).subscribe( hasil => {
        console.log(hasil);
        alert('Registrasi berhasil!');
        this.router.navigateByUrl('/login');
      }, error => {
        console.log(error);
        alert('Registrasi gagal!');
      });
    } else {
      alert("Wajib diisi!");
    }
  }

}
