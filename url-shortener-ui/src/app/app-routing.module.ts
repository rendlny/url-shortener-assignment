import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';

import { HomeComponent } from './home/home.component';
import { LinkListComponent } from './link-list/link-list.component';
import { AddLinkComponent } from './add-link/add-link.component';
import { LinkDetailComponent } from './link-detail/link-detail.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'links-list', component: LinkListComponent },
  { path: 'add-link', component: AddLinkComponent },
  { path: 'edit-link/:link', component: LinkDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
