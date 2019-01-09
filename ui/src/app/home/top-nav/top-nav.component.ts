import { Component, OnInit } from '@angular/core';
import { UserService } from '../../tools/user.service';
import { TopNavService } from './top-nav.service';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  mouseOverCategory: any;

  constructor(private topNavService: TopNavService,
    public user: UserService) { }

  ngOnInit() {
    //初始化菜单数据
    this.topNavService.queryCategory();
  }

  mouseOver(category) {
    this.mouseOverCategory = category;
  }

  mouseOut() {
    this.mouseOverCategory = undefined;
  }

}
