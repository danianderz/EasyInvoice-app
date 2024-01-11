import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GtnClientPage } from './gtn-client.page';

describe('GtnClientPage', () => {
  let component: GtnClientPage;
  let fixture: ComponentFixture<GtnClientPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(GtnClientPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
