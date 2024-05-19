import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMobileComponent } from './add-mobile.component';

describe('AddMobileComponent', () => {
  let component: AddMobileComponent;
  let fixture: ComponentFixture<AddMobileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddMobileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddMobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
