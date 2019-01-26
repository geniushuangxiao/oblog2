import { Component, OnInit, Input } from '@angular/core';
import { Category } from '../../dto/category';
@Component({
  selector: 'app-top-nav-flyout',
  templateUrl: './top-nav-flyout.component.html',
  styleUrls: ['./top-nav-flyout.component.css']
})
export class TopNavFlyoutComponent implements OnInit {
  @Input() parentCategory: Category;
  
  constructor() { }

  ngOnInit() {
  }

}
