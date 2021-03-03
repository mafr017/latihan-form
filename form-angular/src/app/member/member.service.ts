import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class MemberService {

  constructor(private httpClient: HttpClient) { }

  getMemberList(): Observable<any> {
    return this.httpClient.get(environment.baseUrl + 'listmemberjson/')
    .pipe(map( data => data ));
  }
  
}
