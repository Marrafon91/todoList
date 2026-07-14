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
