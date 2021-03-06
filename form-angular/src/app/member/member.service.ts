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

  getMemberList(id: string): Observable<any> {
    if (id != null || id != undefined) {
      //get list member by memberid
      return this.httpClient.get(environment.baseUrl + 'listmemberjson?cari=' + id)
      .pipe(map( data => data ));
    } else {
      //get list member
      return this.httpClient.get(environment.baseUrl + 'listmemberjson/')
      .pipe(map( data => data ));
    }
  }

  getMemberById(id: bigint): Observable<any> {
    return this.httpClient.get(environment.baseUrl + 'datamemberjson/' + id)
    .pipe(map( data => data ));
  }

  addMember(members: Members, lesson: Lesson):  Observable<any> {
    return this.httpClient.post(environment.baseUrl + 'addmemberjson', {members, lesson});
  }

  updateMember(members: Members, lesson: Lesson):  Observable<any> {
    return this.httpClient.post(environment.baseUrl + 'updatememberjson', {members, lesson});
  }

  deleteMemberById(id: number): Observable<any> {
    return this.httpClient.delete(environment.baseUrl + 'deletememberjson/' + id)
    .pipe(map( data => data ));
  }
  
}
