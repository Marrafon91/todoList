import type { CategoryDTO } from "./category";

export type TaskDTO = {
  id: number;
  title: string;
  description: string;
  done: boolean;
  createdAt: string;
  dueDate: string;
  priority: string;
  category: CategoryDTO;
};
export type TaskInsertDTO = {
  title: string;
  description: string;
  priority: string;
  categoryId: number;
  dueDate: string;
};

export type TaskUpdateDTO = {
  title: string;
  description: string;
  priority: string;
  categoryId: number;
  dueDate: string;
};
