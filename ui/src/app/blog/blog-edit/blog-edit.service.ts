import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { HttpService } from '../../tools/http.service';
import { UserService } from '../../tools/user.service';
import { Location } from '@angular/common';
import { Blog } from '../../dto/blog';

@Injectable({
  providedIn: 'root'
})
export class BlogEditService {
  blog: Blog = new Blog();

  constructor(private message: NzMessageService,
    private router: Router,
    private http: HttpService,
    private user: UserService,
    private location: Location) {}

  init(pathVariable: any): void {
    let regEx = /[0-9]+/;
    if(regEx.test(pathVariable)) {//编辑
      this.queryBlog(pathVariable);
    } else {//新建
      this.blog = new Blog();
      this.blog.categoryId = pathVariable;
    }
  }

  queryBlog(id: number) {
    this.http.get("blog/" + id).subscribe(response => {
      if(response.success) {
        this.blog = response.data;
      } else {
        this.message.error(response.message);
      }
    })
  }

  saveBlog(release: boolean):void {
    this.blog.author = this.user.userName();
    this.blog.release = release;
    this.http.post("blog", this.blog).subscribe(response => {
      if(response.success) {
        this.location.back();
      } else {
        this.message.error(response.message);
      }
    })
  }
}
