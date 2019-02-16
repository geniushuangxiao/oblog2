import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpService } from '../../tools/http.service';
import { Blog } from '../../dto/blog';

@Component({
  selector: 'app-blog-detail',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.css']
})
export class BlogDetailComponent implements OnInit {
  blog: Blog = new Blog();
  constructor(private route: ActivatedRoute,
    private message: NzMessageService,
    private http: HttpService,
    private sanitize: DomSanitizer) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.queryBlog(params.id);
    });
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

  // 安全网页
  securityBlogContent() {
    return this.sanitize.bypassSecurityTrustHtml(this.blog.content);
  }

}
