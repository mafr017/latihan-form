import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Members } from '../model/members';
import { Lesson } from '../model/lesson';

@Injectable()
export class MemberService {

  constructor(private httpClient: HttpClient) { }

  getMemberList(): Observable<any> {
    return this.httpClient.get(environment.baseUrl + 'listmemberjson/')
    .pipe(map( data => data ));
  }

  addMember(members: Members, lesson: Lesson):  Observable<any> {
    return this.httpClient.post(environment.baseUrl + 'addmemberjson', {members, lesson});
  }
  
}
