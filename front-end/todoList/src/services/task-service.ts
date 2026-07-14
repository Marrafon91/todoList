import { api } from '../utils/api';

export function findAllTasks() {
  return api.get('/api/tasks');
}

export function findTaskById(id: number) {
  return api.get(`/api/tasks/${id}`);
}

export function insertTask(body: any) {
  return api.post('/api/tasks', body);
}

export function updateTask(id: number, body: any) {
  return api.put(`/api/tasks/${id}`, body);
}

export function deleteTask(id: number) {
  return api.delete(`/api/tasks/${id}`);
}
