import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Blog } from '../../dto/blog';
import { BlogListService } from './blog-list.service';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router,
    private blogListService: BlogListService) {}

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
}
