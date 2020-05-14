import { TestBed } from '@angular/core/testing';

import { LoginGuard } from './login.service';

describe('LoginGuard', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoginGuard = TestBed.get(LoginGuard);
    expect(service).toBeTruthy();
  });
});
