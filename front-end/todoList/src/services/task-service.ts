import { api } from '../utils/api';
import type { TaskDTO, TaskInsertDTO, TaskUpdateDTO } from '../models/task'

export function findAllTasks() {
  return api.get<TaskDTO[]>('/api/tasks');
}

export function findTaskById(id: number) {
  return api.get<TaskDTO>(`/api/tasks/${id}`);
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
