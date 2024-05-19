import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMobilesComponent } from './all-mobiles.component';

describe('AllMobilesComponent', () => {
  let component: AllMobilesComponent;
  let fixture: ComponentFixture<AllMobilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AllMobilesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllMobilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
