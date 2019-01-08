import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpService } from '../../tools/http.service';

@Injectable({
  providedIn: 'root'
})
export class TopNavService {
  categories: [] = [];

  constructor(private message: NzMessageService,
    private http: HttpService) { }

  queryCategory() {
    this.http.get("config/category").subscribe(response => {
      if((<any>response).success) {
        this.categories = (<any>response).data;
      } else {
        this.message.error((<any>response).message);
      }
    })
  }
}
