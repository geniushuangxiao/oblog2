import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpService } from '../tools/http.service';
import { Category } from '../dto/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  categories: Array<Category>;

  constructor(private message: NzMessageService, private http: HttpService) {
    this.queryCategory();
  }

  queryCategory(): void {
    this.http.get("config/category").subscribe(response => {
      if(response.success) {
        this.categories = response.data;
      } else {
        this.categories = [];
        this.message.error(response.message);
      }
    })
  }

  saveCategory(): void {
    this.http.post("config/category", this.categories).subscribe(response => {
      if(response.success) {
        this.message.success(response.message);
      } else {
        this.message.error(response.message);
      }
    })
  }

  // 根据idPath，寻找idPath经过的Category路径
  findCategoryPath(idPath: string): Array<Category> {
    let result = new Array<Category>();
    // 无category数据
    if(!this.categories || !this.categories.length) {
      return result;
    }
    let path = idPath.split('.');
    for(let i = 0; i < path.length; i ++) {
      let id = path[i];
      let current;
      if(result.length === 0) {
        current = this.categories;
      } else {
        current = result[i - 1].children;
      }
      for(let category of current) {
        if(id === category.id) {
          result.push(category);
          continue;
        }
      }
    }
    if(result.length !== path.length) {
      this.message.error("category路径解析失败，请F12收集日志，反馈给管理员");
      console.error("Categories:")
      console.error(this.categories);
      console.error("idPath:");
      console.error(idPath);
    }
    return result;
  }

  // 寻找当前Category所处的数组
  findCurrentArray(idPath: string): Array<Category> {
    if(!idPath) {
      return this.categories;
    } else {
      let path = this.findCategoryPath(idPath);
      if(!path.length) {
        return this.categories;
      }
      return path[path.length - 1].children;
    }
  }
}
