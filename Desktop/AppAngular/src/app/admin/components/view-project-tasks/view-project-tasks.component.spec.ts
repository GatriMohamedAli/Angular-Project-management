import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProjectTasksComponent } from './view-project-tasks.component';

describe('ViewProjectTasksComponent', () => {
  let component: ViewProjectTasksComponent;
  let fixture: ComponentFixture<ViewProjectTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProjectTasksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProjectTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
