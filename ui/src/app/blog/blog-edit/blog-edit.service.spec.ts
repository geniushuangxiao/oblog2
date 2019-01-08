import { TestBed } from '@angular/core/testing';

import { BlogEditService } from './blog-edit.service';

describe('BlogEditService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BlogEditService = TestBed.get(BlogEditService);
    expect(service).toBeTruthy();
  });
});
