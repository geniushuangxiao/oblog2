import { Component, OnInit } from '@angular/core';
import { UserService } from '../../tools/user.service';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

  constructor(public user: UserService) { }

  ngOnInit() {
  }

}
