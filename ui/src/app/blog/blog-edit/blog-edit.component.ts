import { Component, OnInit, ViewEncapsulation  } from '@angular/core';
import { BlogEditService } from './blog-edit.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-blog-edit',
  // encapsulation: ViewEncapsulation.None,
  templateUrl: './blog-edit.component.html',
  styleUrls: ['./blog-edit.component.css']
})
export class BlogEditComponent implements OnInit {
  quillHeight = {
    'height': '460px'
  };
  quillEditorModules = {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],
      // [{ 'header': 1 }, { 'header': 2 }],               // custom button values
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'script': 'sub' }, { 'script': 'super' }],      // superscript/subscript
      [{ 'indent': '-1' }, { 'indent': '+1' }],          // outdent/indent
      //[{ 'direction': 'rtl' }],                         // text direction
      // [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
      [{ 'header': [3, 4, 5, 6, false] }],
      [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
      [{ 'font': [] }],
      [{ 'align': [] }],
      ['clean'],                                         // remove formatting button
      ['link', 'image', 'video']                         // link and image, video
    ]
  };

  constructor(public blogEditService: BlogEditService,
    private route: ActivatedRoute,
    private location: Location) { }

  ngOnInit() {
    this.quillHeight.height = (window.innerHeight - 155) + 'px';
    this.route.params.subscribe(params => {
      this.blogEditService.init(params.id);
    });
  }

  contentChange($event) {
    this.blogEditService.blog.desc = $event.text.substring(0, 500);
  }

  save(release: boolean): void {
    this.blogEditService.saveBlog(release);
  }

  cancle(): void {
    this.location.back();
  }
}
