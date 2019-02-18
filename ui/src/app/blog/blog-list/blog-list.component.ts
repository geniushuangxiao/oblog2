import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Blog } from '../../dto/blog';
import { BlogListService } from './blog-list.service';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router,
    public blogListService: BlogListService,
    public userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.blogListService.queryBlogList(params.id);
    });
  }

  add(): void {
    this.router.navigate(["edit", this.blogListService.categoryId]);
  }

  delete(blog: Blog): void {
    this.blogListService.delete(blog);
  }

  edit(blog: Blog) {
    this.router.navigate(["edit", blog.id]);
  }

  blogDetail(blog: Blog) {
    this.router.navigate(["blogdetail", blog.id]);
  }

  // 博客作者是否是当前登陆用户
  loginUserBlog(blog: Blog): boolean {
    return this.userService.userName() === blog.author;
  }

  // 博客列表中是否展示博客
  showBlog(blog: Blog): boolean {
    return blog.release || this.userService.isAdmin() || this.userService.userName() === blog.author
  }

  // 博客已经发布
  showReleaseIcon(blog: Blog): boolean {
    return (this.userService.isAdmin() || this.userService.userName() === blog.author) && blog.release;
  }

  // 博客未发布
  showNonReleaseIcon(blog: Blog): boolean {
    return (this.userService.isAdmin() || this.userService.userName() === blog.author) && !blog.release;
  }
}
