import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { HttpService } from '../../tools/http.service';
import { UserService } from '../../tools/user.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class BlogEditService {
  blog: any = {};

  constructor(private message: NzMessageService,
    private router: Router,
    private http: HttpService,
    private user: UserService,
    private location: Location) { }

  save(blogContent):void {
    this.blog.author = this.user.userName();
    // this.blog.card = this.cardDetail.cardName;
    this.blog.release = false;
    this.blog.star = false;
    this.blog.content = blogContent;

    this.http.post("blog", this.blog).subscribe(response =>{
      if((<any>response).success) {
        this.location.back();
        this.message.success("保存成功");
      } else {
        this.message.error((<any>response).message);
      }
    });
  }
}
