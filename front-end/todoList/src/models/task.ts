import type { CategoryDTO } from './category';

export type Priority = 'LOW' | 'MEDIUM' | 'HIGH';

export type TaskDTO = {
  id: number;
  title: string;
  description: string;
  done: boolean;
  createdAt: string;
  dueDate: string;
  priority: Priority;
  category: CategoryDTO;
};

export type TaskInsertDTO = {
  title: string;
  description: string;
  priority: Priority;
  categoryId: number;
  dueDate: string;
};

export type TaskFilterDTO = {
  title?: string;
  done?: boolean;
  priority?: string;
  categoryId?: number;
};

export type TaskUpdateDTO = TaskInsertDTO;
