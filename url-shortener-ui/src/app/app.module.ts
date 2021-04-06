import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AddLinkComponent } from './add-link/add-link.component';
import { LinkDetailComponent } from './link-detail/link-detail.component';
import { LinkListComponent } from './link-list/link-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddLinkComponent,
    LinkDetailComponent,
    LinkListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
