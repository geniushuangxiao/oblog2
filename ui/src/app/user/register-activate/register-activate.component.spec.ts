import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterActivateComponent } from './register-activate.component';

describe('RegisterActivateComponent', () => {
  let component: RegisterActivateComponent;
  let fixture: ComponentFixture<RegisterActivateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterActivateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterActivateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
