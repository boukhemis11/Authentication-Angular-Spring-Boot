import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  authService: any;
  constructor(private router: Router, private authservice : AuthService) { }

  ngOnInit() {
    this.isloginn();
  }

  isloginn(){
    if (localStorage.getItem('token')){
      return true;
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
