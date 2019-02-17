import { Injectable } from '@angular/core';
import { Blog } from '../../dto/blog';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpService } from '../../service/http.service';
import { UserService } from '../../service/user.service';


@Injectable({
  providedIn: 'root'
})
export class BlogListService {
  blogs: Array<Blog>;
  categoryId: string;

  constructor(private message: NzMessageService, 
    private http: HttpService,
    private userService: UserService) { }

  queryBlogList(categoryId: string): void {
    this.blogs = [];
    this.categoryId = categoryId;
    this.http.get("blogs/" + this.categoryId).subscribe(response => {
      if(response.success) {
        this.blogs = response.data;
      } else {
        this.message.error(response.message);
      }
    })
  }

  delete(blog: Blog) {
    this.http.delete("blog/" + blog.id).subscribe(response => {
      if(response.success) {
        this.message.success(response.message);
        this.queryBlogList(this.categoryId);
      } else {
        this.message.error(response.message);
      }
    })
  }
}
