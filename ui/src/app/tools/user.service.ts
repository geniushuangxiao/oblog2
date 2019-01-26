import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private principal: any;

  constructor(private http: HttpService, private router: Router) {
    this.user();
  }

  login(creds) {
    this.http.post("login", creds).subscribe(res => {
      this.user();
      this.router.navigate(["/home"]);
    });
  }

  logout() {
    this.http.post('logout', {}).subscribe();
    this.user();
  }

  user() {
    this.http.get("user").subscribe(res=>{
      this.principal = res.data;
    });
  }

  userName() {
    return this.principal? this.principal.name : '未登陆';
  }

  authenticated() {
    return this.principal && this.principal.authenticated===true;
  }

  isAdmin() {
    return this.authenticated() && this.principal.authorities[0] &&  this.principal.authorities[0].role === "ADMIN";
  }

}
