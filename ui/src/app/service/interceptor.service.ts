import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from "@angular/common/http";
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';


@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private message: NzMessageService,
    private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(req).pipe(
        catchError((error) => {
          console.log(error);
          //用户没权限操作
          if (error.error && error.error.status === 401 && error.error.error === "Unauthorized") {
            this.message.error(error.error.message);
            return [];
          }
          //后台重定向到登陆页面
          var reg = RegExp(/login/);
          if (error.url && error.url.match(reg)) {
            this.router.navigate(["/login"]);
            return [];
          }
          //用户没权限操作
          if (error.error && error.error.status === 403 && error.error.error === "Forbidden") {
            this.message.error("您没有操作权限");
            return [];
          }
          return throwError('Something bad happened; please try again later.');
        }),
      );
    }
}
