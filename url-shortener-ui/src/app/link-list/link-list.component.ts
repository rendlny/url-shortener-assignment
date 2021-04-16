import { Component, OnInit } from '@angular/core';
import { CrudService } from 'src/app/service/crud.service';
import { GlobalVariable } from 'src/app/global';

@Component({
  selector: 'app-link-list',
  templateUrl: './link-list.component.html',
  styleUrls: ['./link-list.component.scss']
})

export class LinkListComponent implements OnInit {

  links: any;
  currentLinks = null;
  baseApiUrl = GlobalVariable.BASE_API_URL;

  constructor(private crudService: CrudService) { }

  ngOnInit(): void {
    this.retrieveLinks();  
  }

  retrieveLinks(): void {
    this.crudService.getLinks()
      .subscribe(
        data => {
          console.log(data._embedded.linkList);
          this.links = data._embedded.linkList;
        },
        error => {
          console.log(error);
        }); 
  }

  refreshList(): void {
    this.retrieveLinks();
    this.currentLinks = null;
  }

}
