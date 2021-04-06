import { Component, OnInit } from '@angular/core';
import { CrudService } from './../service/crud.service';

@Component({
  selector: 'app-link-list',
  templateUrl: './link-list.component.html',
  styleUrls: ['./link-list.component.scss']
})

export class LinkListComponent implements OnInit {

  Links:any = [];

  constructor(private crudService: CrudService) { }

  ngOnInit(): void {
    this.crudService.getLinks().subscribe(res => {
      console.log(res)
      this.Links = res;
    });  
  }

}
