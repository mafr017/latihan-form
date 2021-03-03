import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Members } from 'src/app/model/members';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html'
})

export class AddMemberComponent implements OnInit {

  model!: NgbDateStruct;

  myVar2 = true;

  constructor() {
  }

  ngOnInit(): void {
  }

}
