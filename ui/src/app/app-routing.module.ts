import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home/home-page/home-page.component';
import { LoginComponent } from './user/login/login.component';
import { BlogEditComponent } from './blog/blog-edit/blog-edit.component';
import { BlogListComponent } from './blog/blog-list/blog-list.component';
import { MenuSettingComponent } from './setting/menu-setting/menu-setting.component';
import { BlogDetailComponent } from './blog/blog-detail/blog-detail.component';

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'blogs/:id', component: BlogListComponent },
  { path: 'edit/:id', component: BlogEditComponent },
  { path: 'blogdetail/:id', component: BlogDetailComponent },
  { path: 'setting/menu/:id', component: MenuSettingComponent },
  { path: 'setting/menu', component: MenuSettingComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {enableTracing: false, useHash:true})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
