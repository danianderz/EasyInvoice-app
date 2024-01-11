import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GtnEstablecimientosPage } from './gtn-establecimientos.page';

describe('GtnEstablecimientosPage', () => {
  let component: GtnEstablecimientosPage;
  let fixture: ComponentFixture<GtnEstablecimientosPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(GtnEstablecimientosPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
