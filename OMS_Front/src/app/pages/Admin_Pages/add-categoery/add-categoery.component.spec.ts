import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoeryComponent } from './add-categoery.component';

describe('AddCategoeryComponent', () => {
  let component: AddCategoeryComponent;
  let fixture: ComponentFixture<AddCategoeryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddCategoeryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddCategoeryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
