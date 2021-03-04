import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Lesson } from '../model/lesson';
import { Members } from '../model/members';
import { MemberService } from './member.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  providers: [MemberService]
})
export class MemberComponent implements OnInit {

  listMember!: Members[];
  listLesson!: Lesson[];
  resultLesson: string[] = [];

  searchMemberForm!: FormGroup;
  id!: string;

  constructor(private route: ActivatedRoute, private router: Router, private memberService: MemberService) {
    this.searchMemberForm = new FormGroup( {
      memberName: new FormControl(null)
    });
  }

  ngOnInit(): void {
    this.memberService.getMemberList('').subscribe((data : any) => {
      this.listMember = data.members;
      this.listLesson = data.lesson;
      
      this.getArrLesson();
    });
  }

  hapus(idd: number) {
    console.log(idd);
    this.memberService.deleteMemberById(idd).subscribe( (data : any) => {
      this.listMember = data.members;
      this.listLesson = data.lesson;

      this.getArrLesson();
    });
  }

  cari() {
    this.id = this.searchMemberForm?.controls.memberName.value;
    console.log("Cari data " + this.id);
    if (this.id) {
      this.memberService.getMemberList(this.id).subscribe((data : any) => {
        this.listMember = data.members;
        this.listLesson = data.lesson;
        
        this.getArrLesson();
      });
    } else {
      this.ngOnInit();
    }
  }

  getArrLesson() {
    this.listLesson.forEach(element => {
      var result = "";
      if(element.gtrklasik == true) {
        result += "[Gitar Klasik] ";
      }
      if(element.gtrpop == true) {
        result += "[Gitar Pop] ";
      }
      if(element.gtrelektrik == true) {
        result += "[Gitar Elektrik] ";
      }
      if(element.basselektrik == true) {
        result += "[Bass Elektrik] ";
      }
      if(element.pianoklasik == true) {
        result += "[Piano Klasik] ";
      }
      if(element.pianopop == true) {
        result += "[Piano Pop] ";
      }
      if(element.keyboard == true) {
        result += "[Keyboard] ";
      }
      if(element.drum == true) {
        result += "[Drum] ";
      }
      if(element.biola == true) {
        result += "[Biola] ";
      }
      if(element.vocal == true) {
        result += "[Vocal] ";
      }
      if(element.terapimusikautis == true) {
        result += "[Terapi Musik Autis]";
      }
      this.resultLesson.push(result);
    });
  }

}
