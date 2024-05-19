import { TestBed } from '@angular/core/testing';

import { OMSServiceService } from './omsservice.service';

describe('OMSServiceService', () => {
  let service: OMSServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OMSServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
