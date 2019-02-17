import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-register-activate',
  templateUrl: './register-activate.component.html',
  styleUrls: ['./register-activate.component.css']
})
export class RegisterActivateComponent implements OnInit {
  email: string;

  constructor(private route: ActivatedRoute,
    private router: Router,) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.email = params.id;
    });
  }

}
