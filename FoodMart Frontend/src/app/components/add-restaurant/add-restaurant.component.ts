import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';

import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import {RestaurantService} from "../../service/restaurant.service";

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent {

  constructor(private restaurantService:RestaurantService,private httpClient: HttpClient, private fb:FormBuilder,private snackBar:MatSnackBar){}

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  restaurantForm = this.fb.group({
    'restaurantName':[''],
    'resImage':[],
    'location':['']
  })

  get restaurantName(){
    return this.restaurantForm.get('restaurantName')
  }

  get location(){
    return this.restaurantForm.get('location')
  }

  get resImage(){
    return this.restaurantForm.get('resImage')
  }

  emailId = localStorage.getItem("resOwnerEmail");
// emailId:string="admin@gmail.com";

  d:any = {}

  registerRestuarant(){
    // const formData = { ...this.restaurantForm.value }; // create a copy of the form data
    // const body = JSON.stringify(this.restaurantForm.value);
    console.log(this.restaurantForm.value);
    this.imageUploadAction();
    this.d.resImage=this.dbImage;
    this.d.location=this.restaurantForm.value.location;
    this.d.restaurantName=this.restaurantForm.value.restaurantName;
    console.log(this.d);
    console.log(this.emailId)
    this.restaurantService.addRestaurant(this.emailId,this.d).subscribe(
      response=>{
        console.log(response);
        this.snackBar.open("Your Restaurant added Successfully..","Ok",{
          horizontalPosition:this.horizontalPosition,
          verticalPosition:this.verticalPosition,
        });
      }
    )
  }

  uploadedImage:any;

  public onImageUpload(event:any) {
    this.uploadedImage = event.target.files[0];
  }


  dbImage: any;
  postResponse: any;
  successResponse?: string;

  imageUploadAction() {
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);
    this.dbImage=this.uploadedImage.name;

    // this.service.propfilephoto="http://localhost:8082/images/"+this.dbImage;
    console.log(this.dbImage);


    this.httpClient.post('http://localhost:9000/restaurant-service/upload', imageFormData, { observe: 'response' })
      .subscribe((response) => {
          if (response.status === 200) {

            this.postResponse = response;
            this.successResponse = this.postResponse.body.message;
          } else {
            this.successResponse = 'Image not uploaded due to some error!';
          }
        }
      );
  }

}
