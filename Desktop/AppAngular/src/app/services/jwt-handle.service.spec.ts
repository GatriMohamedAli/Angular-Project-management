import { TestBed } from '@angular/core/testing';

import { JwtHandleService } from './jwt-handle.service';

describe('JwtHandleService', () => {
  let service: JwtHandleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JwtHandleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
