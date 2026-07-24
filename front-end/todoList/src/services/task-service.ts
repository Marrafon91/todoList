import { api } from '../utils/api';
import type {
  TaskDTO,
  TaskFilterDTO,
  TaskInsertDTO,
  TaskUpdateDTO,
} from '../models/task';

export function findAllTasks(filters: TaskFilterDTO) {
  return api.get<TaskDTO[]>('/api/tasks', {
    params: filters,
  });
}

export function findTaskById(id: number) {
  return api.get<TaskDTO>(`/api/tasks/${id}`);
}

export function toggleDone(id: number) {
  return api.patch<TaskDTO>(`/api/tasks/${id}/done`);
}

export function insertTask(body: TaskInsertDTO) {
  return api.post<TaskDTO>('/api/tasks', body);
}

export function updateTask(id: number, body: TaskUpdateDTO) {
  return api.put<TaskDTO>(`/api/tasks/${id}`, body);
}

export function deleteTask(id: number) {
  return api.delete(`/api/tasks/${id}`);
}

export function deleteAllTasks(){
  return api.delete("/api/tasks/delete-all");
}
