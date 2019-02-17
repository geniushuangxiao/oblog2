import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home/home-page/home-page.component';
import { LoginComponent } from './user/login/login.component';
import { BlogEditComponent } from './blog/blog-edit/blog-edit.component';
import { BlogListComponent } from './blog/blog-list/blog-list.component';
import { MenuSettingComponent } from './setting/menu-setting/menu-setting.component';
import { BlogDetailComponent } from './blog/blog-detail/blog-detail.component';
import { RegisterComponent } from './user/register/register.component';
import { RegisterActivateComponent } from './user/register-activate/register-activate.component';

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'blogs/:id', component: BlogListComponent },
  { path: 'edit/:id', component: BlogEditComponent },
  { path: 'blogdetail/:id', component: BlogDetailComponent },
  { path: 'setting/menu/:id', component: MenuSettingComponent },
  { path: 'setting/menu', component: MenuSettingComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'registeractivate/:id', component: RegisterActivateComponent },
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
