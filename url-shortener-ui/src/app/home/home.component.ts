import { Component, OnInit } from '@angular/core';
import { Link } from '../service/Link';
import { CrudService } from './../service/crud.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  links:any = [];
  link = new Link;
  linkToShorten = null;

  constructor(private crudService: CrudService) { }

  getAll() {
    this.crudService.getLinks().subscribe((results: any[]) => {
      this.links = results;
    })
  }

  shortenLink(): void {
    this.link.fullLink = this.linkToShorten;
    this.crudService.addLink(this.link)
      .subscribe(
        data => {
          this.link = data;
          console.log(this.link);
          //clear input field
          this.linkToShorten = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  storeLinkInput(event) {
    this.linkToShorten = event.target.value;
    console.log(this.linkToShorten);
  }

}
