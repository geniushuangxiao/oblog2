import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/category.service';
import { Category } from '../../dto/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-center-body',
  templateUrl: './center-body.component.html',
  styleUrls: ['./center-body.component.css']
})
export class CenterBodyComponent implements OnInit {
  constructor(public categoryService: CategoryService,
    private router: Router) { }

  ngOnInit() {
  }

  toBlogList(category: Category): void {
    this.router.navigate(["blogs", category.idPath]);
  }

}
