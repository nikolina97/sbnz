import { TestBed } from '@angular/core/testing';

import { Interceptor } from './intercept.service';

describe('Interceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Interceptor = TestBed.get(Interceptor);
    expect(service).toBeTruthy();
  });
});
