import { Component, OnInit } from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html'
})

export class AddMemberComponent implements OnInit {

  model!: NgbDateStruct;

  constructor() { }

  ngOnInit(): void {
  }

}
