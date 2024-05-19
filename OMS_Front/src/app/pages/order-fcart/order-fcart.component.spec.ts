import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderFCartComponent } from './order-fcart.component';

describe('OrderFCartComponent', () => {
  let component: OrderFCartComponent;
  let fixture: ComponentFixture<OrderFCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OrderFCartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderFCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
