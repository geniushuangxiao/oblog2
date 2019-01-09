import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopNavFlyoutComponent } from './top-nav-flyout.component';

describe('TopNavFlyoutComponent', () => {
  let component: TopNavFlyoutComponent;
  let fixture: ComponentFixture<TopNavFlyoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopNavFlyoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopNavFlyoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
