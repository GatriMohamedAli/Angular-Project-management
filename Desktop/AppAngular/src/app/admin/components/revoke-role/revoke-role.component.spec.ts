import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevokeRoleComponent } from './revoke-role.component';

describe('RevokeRoleComponent', () => {
  let component: RevokeRoleComponent;
  let fixture: ComponentFixture<RevokeRoleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevokeRoleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevokeRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
