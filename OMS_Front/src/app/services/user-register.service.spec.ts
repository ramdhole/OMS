import { TestBed } from '@angular/core/testing';
import { UserRegisterService } from './user-register.service';

describe('UserLoginService', () => {
  let service: UserRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

