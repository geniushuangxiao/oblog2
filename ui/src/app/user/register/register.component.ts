import { Component, OnInit } from '@angular/core';
import { MyValidators } from '../../validator/my-validators';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { HttpService } from '../../service/http.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  validateForm: FormGroup;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if(this.validateForm.valid) {
      let formValue = this.validateForm.value;
      if(formValue.password !== formValue.password2) {
        this.message.error("两次输入的密码不相同");
      } else {
        this.httpService.post("user/register", formValue).subscribe(response => {
          if(response.success) {
            this.router.navigate(["/registeractivate", formValue.email]);
          } else {
            this.message.error(response.message);
          }
        })
      }
    }
  }

  constructor(private fb: FormBuilder,
    private httpService: HttpService,
    private message: NzMessageService,
    private router: Router,) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      username: [null, [ MyValidators.required ]],
      password: [null, [ MyValidators.required ]],
      password2: [null, [ MyValidators.required ]],
      email: [null, [ MyValidators.required, MyValidators.email]]
    });
  }

}
