import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { CategoryService } from '../../service/category.service';
import { Category } from '../../dto/category';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.css']
})
export class BreadcrumbComponent implements OnInit {
  @Input() idPath:string;
  @Output() idPathChange = new EventEmitter<Category>();

  constructor(private categoryService: CategoryService) {}

  ngOnInit() {
  }

  breadcrumbCategories() :Array<Category> {
    if(this.idPath) {
      return this.categoryService.findCategoryPath(this.idPath);
    } else {
      return [];
    }
  }

  breadcrumbClick(category: Category) :void {
    this.idPathChange.emit(category);
  }

}
