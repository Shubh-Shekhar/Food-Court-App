import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserSignUp } from '../model/user/user-sign-up';
import { AppModule } from '../app.module';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  isLoggedIn:boolean = false;

  constructor(private httpclient:HttpClient) { }

  userAuthBaseUrl = 'http://localhost:9000/app/v1';

  userRegistration(userSignUp:UserSignUp)
  {
      return this.httpclient.post("http://localhost:9000/app/v1/register",userSignUp);
  }

  userLogIn(user:any){
      return this.httpclient.post("http://localhost:9000/app/v1/login",user);
  }

  loggedIn(){
    this.isLoggedIn = true;
  }

  getUserImage(user:any){
    return this.httpclient.get("http://localhost:9000/app/v1/file/"+user)
  }

  uploadUserImage(image:any){
    return this.httpclient.post("http://localhost:9000/app/v1/file",image)
  }


}
