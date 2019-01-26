import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import { QuillModule } from 'ngx-quill';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { HomePageComponent } from './home/home-page/home-page.component';
import { TopNavComponent } from './home/top-nav/top-nav.component';
import { CenterBodyComponent } from './home/center-body/center-body.component';
import { LoginComponent } from './user/login/login.component';
import { BlogEditComponent } from './blog/blog-edit/blog-edit.component';
import { TopNavFlyoutComponent } from './home/top-nav-flyout/top-nav-flyout.component';
import { BlogListComponent } from './blog/blog-list/blog-list.component';
import { BreadcrumbComponent } from './common/breadcrumb/breadcrumb.component';
import { MenuSettingComponent } from './setting/menu-setting/menu-setting.component';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    TopNavComponent,
    CenterBodyComponent,
    LoginComponent,
    BlogEditComponent,
    TopNavFlyoutComponent,
    BlogListComponent,
    BreadcrumbComponent,
    MenuSettingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgZorroAntdModule,
    QuillModule,
    FormsModule
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: NZ_I18N, useValue: zh_CN }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
