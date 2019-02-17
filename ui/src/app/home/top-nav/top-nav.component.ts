import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { CategoryService } from '../../service/category.service';
import { Category } from '../../dto/category';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  mouseOverCategory: Category;

  constructor(private cagegoryServcie: CategoryService,
    public user: UserService) { }

  ngOnInit() {
  }

  mouseOver(category) {
    this.mouseOverCategory = category;
  }

  mouseOut() {
    this.mouseOverCategory = undefined;
  }

  showFlyout() {
    return this.mouseOverCategory && this.mouseOverCategory.children;
  }

}
