import { Component, OnInit } from '@angular/core';
import { CrudService } from './../service/crud.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  links:any = [];

  constructor(private crudService: CrudService) { }

  getAll() {
    this.crudService.GetLinks().subscribe((results: any[]) => {
      console.log('Data is received - Result - ', results);
      this.links = results;
    })
  }

}
