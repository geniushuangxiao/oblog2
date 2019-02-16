import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../service/category.service';
import { Category } from 'src/app/dto/category';

@Component({
  selector: 'app-menu-setting',
  templateUrl: './menu-setting.component.html',
  styleUrls: ['./menu-setting.component.css']
})
export class MenuSettingComponent implements OnInit {
  id: string = '';

  constructor(private route: ActivatedRoute,
    private router: Router,
    private category: CategoryService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params.id;
    });
  }

  queryCurrentCategories(): Array<Category> {
    return this.category.findCurrentArray(this.id);
  }

  remove(category: Category) {
    let currentArray = this.category.findCurrentArray(this.id);
    for(let i = 0; i < currentArray.length; i ++) {
      if(category == currentArray[i]) {
        currentArray.splice(i, 1);
        break;
      }
    }
  }

  up(category: Category) {
    let currentArray = this.category.findCurrentArray(this.id);
    for(let i = 0; i < currentArray.length; i ++) {
      if(category == currentArray[i]) {
        let temp = currentArray[i];
        currentArray[i] = currentArray[i - 1];
        currentArray[i - 1] = temp;
        break;
      }
    }
  }

  down(category: Category) {
    let currentArray = this.category.findCurrentArray(this.id);
    for(let i = 0; i < currentArray.length; i ++) {
      if(category == currentArray[i]) {
        let temp = currentArray[i];
        currentArray[i] = currentArray[i + 1];
        currentArray[i + 1] = temp;
        break;
      }
    }
  }

  toChildren(category: Category) {
    this.router.navigate(["/setting/menu", category.idPath]);
  }

  add(): void {
    this.category.findCurrentArray(this.id).push(new Category());
  }

  // 保存到数据库
  save(): void {
    this.category.saveCategory();
  }

  // 取消修改
  cancleModify(): void {
    this.category.queryCategory();
  }

  idChange(category: Category) {
    if(category) {
      this.router.navigate(["/setting/menu", category.idPath]);
    } else {
      this.router.navigate(["/setting/menu"]);
    }
  }
}
