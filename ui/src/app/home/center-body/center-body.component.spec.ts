import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterBodyComponent } from './center-body.component';

describe('CenterBodyComponent', () => {
  let component: CenterBodyComponent;
  let fixture: ComponentFixture<CenterBodyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CenterBodyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
