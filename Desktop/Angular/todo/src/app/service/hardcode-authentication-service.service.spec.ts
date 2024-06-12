import { TestBed } from '@angular/core/testing';

import { HardcodeAuthenticationServiceService } from './hardcode-authentication-service.service';

describe('HardcodeAuthenticationServiceService', () => {
  let service: HardcodeAuthenticationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HardcodeAuthenticationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
