import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

fdescribe('AuthenticationService', () => {

  let service: AuthenticationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthenticationService],
      imports: [HttpClientTestingModule]
    });
    service = TestBed.get(AuthenticationService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('login should return error for username', () => {
    const auth: any = {
      username : "WrongUsername",
      password : "123"
    };
    service.login(auth).subscribe(data => {
      expect(JSON.parse(data).status).toEqual(406);
      expect(JSON.parse(data).statusText).toEqual(`User with username ${auth.username} not found`);
      
    });
    const loginUrl =  "http://localhost:8080/auth/login";
    const req = httpMock.expectOne(loginUrl);
    expect(req.request.method).toBe('POST');
    req.flush({
      status: 406,
      statusText: `User with username ${auth.username} not found`
    });
    httpMock.verify();
  });

  it('login should return error for password', () => {
    const auth: any = {
      username : "user",
      password : "123aaaa"
    };
    service.login(auth).subscribe(data => {
      expect(JSON.parse(data).status).toEqual(406);
      expect(JSON.parse(data).statusText).toEqual(`Wrong password`);
      
    });
    const loginUrl =  "http://localhost:8080/auth/login";
    const req = httpMock.expectOne(loginUrl);
    expect(req.request.method).toBe('POST');
    req.flush({
      status: 406,
      statusText: `Wrong password`
    });
    httpMock.verify();
  });

  it('login should return jwt token', () => {
    const auth: any = {
      username : "user",
      password : "123"
    };
    service.login(auth).subscribe(data => {
      expect(data).toEqual("jwttoken");
    });
    const loginUrl =  "http://localhost:8080/auth/login";
    const req = httpMock.expectOne(loginUrl);
    expect(req.request.method).toBe('POST');
    req.flush("jwttoken");
    httpMock.verify();
  });

  it('registration should return the user info', () => {
    const userInfo = {
      username: 'user123',
      password: "1234",
      repeatedPassword: "1234",
      firstName: "Marko",
      lastName: "Markovic",
      email: "user123marko@gmail.com"
    };

    service.register(userInfo).subscribe(data => {
      expect(data.username).toEqual(userInfo.username);
      expect(data.email).toEqual(userInfo.email);
      expect(data.firstName).toEqual(userInfo.firstName);
      expect(data.lastName).toEqual(userInfo.lastName);
    });
    const req = httpMock.expectOne(`http://localhost:8080/auth/registration`, 'call to api');
    expect(req.request.method).toBe('POST');
    req.flush(userInfo);
    httpMock.verify();
  });

  it('registration should return an error when the username is taken', () => {
    const userInfo = {
      username: 'user1',
      password: "1234",
      repeatedPassword: "1234",
      firstName: "Marko",
      lastName: "Markovic",
      email: "user123marko@gmail.com"
    };

    service.register(userInfo).subscribe(data => {
      expect(data.status).toEqual(406);
      expect(data.statusText).toEqual("Username already taken!");
    });
    const req = httpMock.expectOne(`http://localhost:8080/auth/registration`, 'call to api');
    expect(req.request.method).toBe('POST');
    req.flush({
      status: 406,
      statusText: "Username already taken!"
    });
    httpMock.verify();
  });

  it('registration should return an error when the email is taken', () => {
    const userInfo = {
      username: 'user123',
      password: "1234",
      repeatedPassword: "1234",
      firstName: "Marko",
      lastName: "Markovic",
      email: "user1@gmail.com"
    };

    service.register(userInfo).subscribe(data => {
      expect(data.status).toEqual(406);
      expect(data.statusText).toEqual("Email already taken!");
    });
    const req = httpMock.expectOne(`http://localhost:8080/auth/registration`, 'call to api');
    expect(req.request.method).toBe('POST');
    req.flush({
      status: 406,
      statusText: "Email already taken!"
    });
    httpMock.verify();
  });

  it('registration should return an error when the email is incorrect', () => {
    const userInfo = {
      username: 'user123',
      password: "1234",
      repeatedPassword: "1234",
      firstName: "Marko",
      lastName: "Markovic",
      email: "abcabcabacaba@gmail.com"
    };

    service.register(userInfo).subscribe(data => {
      expect(data.status).toEqual(400);
      expect(data.statusText).toEqual("Error while sending e-mail. Check to see if you entered it right!");
    });
    const req = httpMock.expectOne(`http://localhost:8080/auth/registration`, 'call to api');
    expect(req.request.method).toBe('POST');
    req.flush({
      status: 400,
      statusText: "Error while sending e-mail. Check to see if you entered it right!"
    });
    httpMock.verify();
  });
})

