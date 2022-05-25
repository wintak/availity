import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'registration';
  registerUser: FormGroup;

  constructor(private fb: FormBuilder){
    this.registerUser = fb.group({
      firstName : ['', Validators.required],
      lastName : ['', Validators.required],
      npiNumber : ['', Validators.required],
      phoneNumber : ['', Validators.required],
      email : ['', Validators.required]
      
    })
  }

  onSubmit(){

    //do something with form data

  }
}
