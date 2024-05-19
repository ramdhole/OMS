import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartToOrderComponent } from './cart-to-order.component';

describe('CartToOrderComponent', () => {
  let component: CartToOrderComponent;
  let fixture: ComponentFixture<CartToOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartToOrderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CartToOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
