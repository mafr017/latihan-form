import { Component, OnInit } from '@angular/core';
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

  constructor(private route: ActivatedRoute, private router: Router, private memberService: MemberService) {}

  ngOnInit(): void {
    this.memberService.getMemberList().subscribe((data : any) => {
      console.log("ini data member " + data.members[0].email);
      this.listMember = data.members;
    })
  }

}
