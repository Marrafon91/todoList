import { api } from '../utils/api';
import type { CategoryDTO } from '../models/category';

export function findAllCategories() {
  return api.get<CategoryDTO[]>('/api/categories');
}
